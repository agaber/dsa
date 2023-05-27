package dev.agaber.dsa;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

final class ArraysAndHashing {
  /**
   * Contains Duplicate
   *
   * <p>Given an integer array nums, return true if any value appears at least
   * twice in the array, and return false if every element is distinct.
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li>https://leetcode.com/problems/contains-duplicate/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   *
   * <p>Discussion: Of course there's an O(n^2) solution and there sorting the
   * list is another option but will perform in O(n log n).
   */
  static boolean containsDuplicate(int[] nums) {
    var seen = new HashSet<Integer>();
    for (int num : nums) {
      if (seen.contains(num)) {
        return true;
      }
      seen.add(num);
    }
    return false;
  }

  /**
   * Valid Anagram
   *
   * <p>Given two strings s and t, return true if t is an anagram of s, and
   * false otherwise.
   *
   * <p>An Anagram is a word or phrase formed by rearranging the letters of a
   * different word or phrase, typically using all the original letters exactly
   * once.
   *
   * <ul>Constraints:
   *   <li>1 <= s.length, t.length <= 5 * 104
   *   <li>s and t consist of lowercase English letters.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li>https://leetcode.com/problems/valid-anagram/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   *
   * <p>Discussion: Not much to say here. This solution is probably as clever
   * as you can get with this I'd presume. Arrays tend to perform better than
   * Sets and Maps usually.
   */
  static boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    int[] counter = new int[26];
    for (int i = 0; i < s.length(); i++) {
      counter[s.charAt(i) - 'a']++;
      counter[t.charAt(i) - 'a']--;
    }

    return Arrays.stream(counter).noneMatch(count -> count != 0);
  }

  /**
   * Two Sum
   *
   * <p>Given an array of integers nums and an integer target, return indices of
   * the two numbers such that they add up to target.
   *
   * <p>You may assume that each input would have exactly one solution, and
   * you may not use the same element twice.
   *
   * <p>You can return the answer in any order.
   *
   * <ul>Constraints
   *   <li>2 <= nums.length <= 104
   *   <li>-109 <= nums[i] <= 109
   *   <li>-109 <= target <= 109
   *   <li>Only one valid answer exists.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li>https://leetcode.com/problems/two-sum/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   *
   * <p>Discussion: Cool.
   */
  static int[] twoSum(int[] nums, int target) {
    var seen = new HashMap<Integer, Integer>();
    for (int i = 0; i < nums.length; i++) {
      int compliment = target - nums[i];
      if (seen.containsKey(compliment)) {
        return new int[]{seen.get(compliment), i};
      }
      seen.put(nums[i], i);
    }
    return new int[0];
  }

  /**
   * Group Anagrams
   *
   * <p>Given an array of strings strs, group the anagrams together. You can
   * return the answer in any order.
   *
   * <p>An Anagram is a word or phrase formed by rearranging the letters of a
   * different word or phrase, typically using all the original letters exactly
   * once.
   *
   * <ul>Constraints
   *   <li>1 <= strs.length <= 104
   *   <li>0 <= strs[i].length <= 100
   *   <li>strs[i] consists of lowercase English letters.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/group-anagrams/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   *
   * <p>Discussion: Probably the most clever part of this is choosing a way to
   * hash strings. This solution isn't great and performs in O(s log s) where s
   * is the size of each anagram. Therefore the solution is really O(n * slogs).
   * I copied a better idea on LeetCode.
   */
  static List<List<String>> groupAnagrams(String[] strs) {
    var map = new HashMap<String, List<String>>();
    for (String str : strs) {
      var letters = str.split("");
      Arrays.sort(letters);
      String key = Arrays.stream(letters).collect(joining(""));
      map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
    }
    return map.values().stream().collect(toImmutableList());
  }

  /**
   * Top K Frequent Elements
   *
   * <p>Given an integer array nums and an integer k, return the k most frequent
   * elements. You may return the answer in any order.
   *
   * <ul> Constraints:
   *   <li>1 <= nums.length <= 105
   *   <li>-104 <= nums[i] <= 104
   *   <li>k is in the range [1, the number of unique elements in the array].
   *   <li>It is guaranteed that the answer is unique.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/top-k-frequent-elements/
   *   <li>Time complexity: O(n log k)
   *   <li>Space complexity: O(n)
   * </ul>
   *
   * <p>Discussion: This is slightly optimized and as a consequence perhaps a
   * a bit harder to understand, but it's basically just counting numbers,
   * shoving them into a heap/priority queue and then returning the top k
   * elements. It's an easy level question once you're familiar with heaps &
   * priority queues and honestly you probably could just sort with arguably
   * just as good performance. My original version had a sortable object btw.
   */
  static int[] topKFrequent(int[] nums, int k) {
    if (nums.length == k) {
      return nums;
    }

    var counter = new HashMap<Integer, Integer>();
    Arrays.stream(nums).forEach(num -> counter.merge(num, 1, (x, y) -> x + y));

    var pq = new PriorityQueue<Integer>(Comparator.comparingInt(counter::get));
    for (var number : counter.keySet()) {
      pq.add(number);
      if (pq.size() > k) {
        pq.poll();
      }
    }

    int[] result = new int[k];
    for (int i = 0; i < k && !pq.isEmpty(); i++) {
      result[i] = pq.poll();
    }
    return result;
  }

  /**
   * Product of array except self
   *
   * <p>Given an integer array nums, return an array answer such that answer[i] is equal to the
   * product of all the elements of nums except nums[i].
   *
   * <p>The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
   *
   * <p>You must write an algorithm that runs in O(n) time and without using the division operation.
   *
   * <ul> Constraints:
   *   <li>2 <= nums.length <= 105
   *   <li>-30 <= nums[i] <= 30
   *   <li>The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/product-of-array-except-self/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   *
   * <p>Follow up: Can you solve the problem in O(1) extra space complexity?
   * (The output array does not count as extra space for space complexity
   * analysis.)
   *
   * <p>TODO: Do the O(1) memory solution.
   */
  static int[] productExceptSelf(int[] nums) {
    int[] prefix = new int[nums.length + 2];
    prefix[0] = 1;
    prefix[prefix.length - 1] = 1;

    int[] postfix = new int[nums.length + 2];
    postfix[0] = 1;
    postfix[postfix.length - 1] = 1;

    for (int i = 0; i < nums.length; i++) {
      prefix[i + 1] = prefix[i] * nums[i];
      postfix[postfix.length - i - 2] = postfix[postfix.length - i - 1] * nums[nums.length - i - 1];
    }

    for (int i = 0; i < nums.length; i++) {
      nums[i] = prefix[i] * postfix[i + 2];
    }

    return nums;
  }

  /**
   * Longest Consecutive Sequence
   *
   * <p>Given an unsorted array of integers nums, return the length of the
   * longest consecutive elements sequence.
   *
   * <p>You must write an algorithm that runs in O(n) time.
   *
   * <ul> Constraints:
   *   <li>0 <= nums.length <= 105
   *   <li>-109 <= nums[i] <= 109
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/longest-consecutive-sequence/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   */
  static int longestConsecutive(int[] nums) {
    var numset = Arrays.stream(nums).boxed().collect(toSet());
    int max = 0;
    for (int num : nums) {
      if (numset.contains(num + 1) && !numset.contains(num - 1)) {
        int localmax = 1;
        while (numset.contains(num + 1)) {
          numset.remove(num);
          localmax++;
          num++;
        }
        max = Math.max(max, localmax);
      }
    }
    return max;
  }

  /**
   * Encode and Decode Strings
   *
   * <p>Design an algorithm to encode a list of strings to a string. The encoded
   * string is then sent over the network and is decoded back to the original
   * list of strings.
   *
   * <pre>
   * Machine 1 (sender) has the function:
   *
   * string encode(vector<string> strs) {
   *   // ... your code
   *   return encoded_string;
   * }
   *
   * Machine 2 (receiver) has the function:
   *
   * vector<string> decode(string s) {
   *   //... your code
   *   return strs;
   * }
   *
   * So Machine 1 does:
   *
   * string encoded_string = encode(strs);
   * and Machine 2 does:
   *
   * vector<string> strs2 = decode(encoded_string);
   * strs2 in Machine 2 should be the same as strs in Machine 1.
   * </pre>
   *
   * <p>Implement the encode and decode methods.
   *
   * <p>You are not allowed to solve the problem using any serialize methods
   * (such as eval).
   *
   * <ul> Constraints:
   *   <li>1 <= strs.length <= 200
   *   <li>0 <= strs[i].length <= 200
   *   <li>strs[i] contains any possible characters out of 256 valid ASCII characters.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/encode-and-decode-strings/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   *
   * <p>I'm not really sure why this is a medium level question.
   */
  static final class Codec {
    // Outside the 256 limit that they say their input will contain.
    private static final String DIVIDER = "😂";
    private static final String WRAPPER = "🙃";

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
      return strs.stream()
          .map(s -> WRAPPER + s + WRAPPER)
          .reduce((s1, s2) -> s1 + DIVIDER + s2)
          .orElse("");
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
      return Splitter.on(DIVIDER).omitEmptyStrings().splitToList(s).stream()
          .map(str -> str.replace(WRAPPER, ""))
          .collect(toImmutableList());
    }
  }

  private ArraysAndHashing() {}
}
