package dev.agaber.dsa;

import java.util.*;

public final class SlidingWindows {
  /**
   * Best Time to Buy and Sell Stock
   *
   * <p>You are given an array prices where prices[i] is the price of a given
   * stock on the ith day.
   *
   * <p>You want to maximize your profit by choosing a single day to buy one
   * stock and choosing a different day in the future to sell that stock.
   *
   * <p>Return the maximum profit you can achieve from this transaction. If you
   * cannot achieve any profit, return 0.
   *
   * <ul>Constraints:
   *   <li>1 <= prices.length <= 105
   *   <li>0 <= prices[i] <= 104
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li>https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(1)
   * </ul>
   */
  public static int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }
    int maxProfit = 0;
    int lowestPrice = prices[0];
    for (int i = 1; i < prices.length; i++) {
      maxProfit = Math.max(prices[i] - lowestPrice, maxProfit);
      lowestPrice = Math.min(lowestPrice, prices[i]);
    }
    return maxProfit;
  }

  /**
   * Longest Substring Without Repeating Characters
   *
   * <p>Given a string s, find the length of the longest substring without
   * repeating characters.
   *
   * <ul>Constraints:
   *   <li>0 <= s.length <= 5 * 104
   *   <li>s consists of English letters, digits, symbols and spaces.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/longest-substring-without-repeating-characters/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   */
  public static int lengthOfLongestSubstring(String s) {
    int l = 0, r = 0, maxSubstringSize = 0;
    var seen = new HashSet<Character>(s.length());
    while (l <= r && r < s.length()) {
      if (!seen.contains(s.charAt(r))) {
        seen.add(s.charAt(r));
        r++;
        maxSubstringSize = Math.max(maxSubstringSize, r - l);
      } else {
        seen.remove(s.charAt(l));
        l++;
      }
    }
    return maxSubstringSize;
  }

  /**
   * Longest Repeating Character Replacement
   *
   * <p>You are given a string s and an integer k. You can choose any character
   * of the string and change it to any other uppercase English character. You
   * can perform this operation at most k times.
   *
   * <p>Return the length of the longest substring containing the same letter
   * you can get after performing the above operations.
   *
   * <ul>Constraints:
   *   <li>1 <= s.length <= 105
   *   <li>s consists of only uppercase English letters.
   *   <li>0 <= k <= s.length
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/longest-repeating-character-replacement/
   *   <li>Time complexity:
   *   <li>Space complexity:
   * </ul>
   */
  public static int characterReplacement(String s, int k) {
    int answer = 0;
    int l = 0;
    int[] counter = new int[26];
    for (int r = 0; r < s.length(); r++) {
      counter[s.charAt(r) - 'A']++;
      while (windowLength(l, r) - maxCharCount(counter) > k) {
        counter[s.charAt(l) - 'A']--;
        l++;
      }
      answer = Math.max(answer, windowLength(l, r));
    }
    return answer;
  }

  private static int windowLength(int l, int r) {
    return r - l + 1;
  }

  private static int maxCharCount(int[] counter) {
    return Arrays.stream(counter).max().orElse(0);
  }

  /**
   * Minimum Window Substring
   *
   * <p>Given two strings s and t of lengths m and n respectively, return the
   * minimum window substring of s such that every character in t (including
   * duplicates) is included in the window. If there is no such substring,
   * return the empty string "".
   *
   * <p>The testcases will be generated such that the answer is unique.
   *
   * <ul>Constraints:
   *   <li>m == s.length
   *   <li>n == t.length
   *   <li>1 <= m, n <= 105
   *   <li>s and t consist of uppercase and lowercase English letters.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Hard
   *   <li>https://leetcode.com/problems/minimum-window-substring/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   */
  public static String minWindow(String s, String t) {
    int need = t.length();
    var tcounter = new HashMap<Character, Integer>();
    for (char c : t.toCharArray()) {
      tcounter.put(c, tcounter.getOrDefault(c, 0) + 1);
    }

    int have = 0;
    var scounter = new HashMap<Character, Integer>();
    var result = "";
    int l = 0;
    for (int r = 0; r < s.length(); r++) {
      char rightChar = s.charAt(r);
      if (tcounter.containsKey(rightChar)) {
        scounter.put(rightChar, scounter.getOrDefault(rightChar, 0) + 1);
        if (scounter.get(rightChar) <= tcounter.get(rightChar)) {
          have++;
        }
      }

      while (have == need) {
        if (result.isEmpty() || (r - l) < result.length()) {
          result = s.substring(l, r + 1);
        }
        char leftChar = s.charAt(l);
        if (tcounter.containsKey(leftChar)) {
          scounter.put(leftChar, scounter.getOrDefault(leftChar, 1) - 1);
          if (scounter.get(leftChar) < tcounter.get(leftChar)) {
            have--;
          }
        }
        l++;
      }
    }
    return result;
  }
}
