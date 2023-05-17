package dev.agaber.dsa;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.stream.Collectors.joining;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

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
  public static boolean containsDuplicate(int[] nums) {
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
  public static boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    int[] counter = new int[26];
    for (int i = 0; i < s.length(); i++) {
      counter[s.charAt(i) - 'a']++;
      counter[t.charAt(i) - 'a']--;
    }

    return !Arrays.stream(counter).anyMatch(count -> count != 0);
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
  public static int[] twoSum(int[] nums, int target) {
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
  public static List<List<String>> groupAnagrams(String[] strs) {
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
  public static int[] topKFrequent(int[] nums, int k) {
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

  private ArraysAndHashing() {}
}
