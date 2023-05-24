package dev.agaber.dsa

import java.util.*

/**
 * Contains Duplicate
 *
 * Given an integer array nums, return true if any value appears at least twice in the array, and
 * return false if every element is distinct.
 *
 * Constraints:
 * - 1 <= nums.length <= 105
 * - -109 <= nums[i] <= 109
 *
 * Attributes
 * - List: Blind 75
 * - Level: Easy
 * - Link: https://leetcode.com/problems/contains-duplicate/
 * - Time: O(n)
 * - Space: O(n)
 */
fun containsDuplicate(nums: IntArray): Boolean {
  val set = mutableSetOf<Int>()
  for (num in nums) {
    if (set.contains(num)) {
      return true
    }
    set.add(num)
  }
  return false
}

/**
 * Valid Anagram
 *
 * Given two strings s and t, return true if t is an anagram of s, and false
 * otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, typically using all the original letters exactly
 * once.
 *
 * Constraints:
 * - 1 <= s.length, t.length <= 5 * 104
 * - s and t consist of lowercase English letters.
 *
 * Attributes
 *  - List: Blind 75
 *  - Level: Easy
 *  - Link: https://leetcode.com/problems/valid-anagram/
 *  - Time: O(n)
 *  - Space: O(n)
 */
fun isAnagram(s: String, t: String): Boolean {
  if (s.length != t.length) {
    return false
  }
  val counter: MutableMap<Char, Int> = mutableMapOf()
  for (i in s.indices) {
    counter[s[i]] = counter.getOrDefault(s[i], 0) + 1
    counter[t[i]] = counter.getOrDefault(t[i], 0) - 1
    if (counter[s[i]] == 0) {
      counter.remove(s[i])
    }
    if (counter[t[i]] == 0) {
      counter.remove(t[i])
    }
  }
  return counter.isEmpty()
}

/**
 * Two Sum
 *
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 *
 * You can return the answer in any order.
 *
 * Constraints
 * - 2 <= nums.length <= 104
 * - -109 <= nums[i] <= 109
 * - -109 <= target <= 109
 * - Only one valid answer exists.
 *
 * Attributes
 *  - List: Blind 75
 *  - Level: Easy
 *  - Link: https://leetcode.com/problems/two-sum/
 *  - Time: O(n)
 *  - Space: O(n)
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
  val map: MutableMap<Int, Int> = mutableMapOf()
  for (i in nums.indices) {
    val compliment = target - nums[i]
    if (map.containsKey(compliment)) {
      return intArrayOf(map[compliment]!!, i)
    }
    map[nums[i]] = i
  }
  return intArrayOf()
}

/**
 * Group Anagrams
 *
 * Given an array of strings strs, group the anagrams together. You can return
 * the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, typically using all the original letters exactly
 * once.
 *
 * Constraints
 * - 1 <= strs.length <= 104
 * - 0 <= strs[i].length <= 100
 * - strs[i] consists of lowercase English letters.
 *
 * Attributes
 *  - List: Blind 75
 *  - Level: Medium
 *  - Link: https://leetcode.com/problems/group-anagrams/
 *  - Time Complexity: O(NK), where N is the length of strs, and K is the
 *    maximum length of a string in strs. Counting each string is linear in the
 *    size of the string, and we count every string.
 *  - Space Complexity: O(NK), the total information content stored in ans.
 */
fun groupAnagrams(strs: Array<String>): List<List<String>> {
  fun hash(str: String): String {
    val arr = IntArray(26)
    for (c in str) {
      arr[c - 'a'] += 1
    }
    return arr.map { c -> "#$c#" }.joinToString("")
  }

  val anagrams: MutableMap<String, MutableList<String>> = mutableMapOf()
  for (str in strs) {
    val key = hash(str)
    anagrams.computeIfAbsent(key) { mutableListOf() }.add(str)
  }
  val result: MutableList<List<String>> = mutableListOf()
  anagrams.values.forEach { lst -> result.add(lst) }
  return result.toList()
}

/**
 * Top K Frequent Elements
 *
 * Given an integer array nums and an integer k, return the k most frequent
 * elements. You may return the answer in any order.
 *
 * Constraints
 * - 1 <= nums.length <= 105
 * - -104 <= nums[i] <= 104
 * - k is in the range [1, the number of unique elements in the array].
 * - It is guaranteed that the answer is unique.
 *
 * Attributes
 *  - List: Blind 75
 *  - Level: Medium
 *  - Link: https://leetcode.com/problems/top-k-frequent-elements/
 *  - Time: O(n log k)
 *  - Space: O(n + k)
 */
fun topKFrequent(nums: IntArray, k: Int): IntArray {
  val counter: MutableMap<Int, Int> = mutableMapOf()
  for (i in nums.indices) {
    counter[nums[i]] = counter.getOrDefault(nums[i], 0) + 1
  }

  val pq: PriorityQueue<IntArray> = PriorityQueue<IntArray>(compareBy { it[1] })
  for (e in counter.entries) {
    pq.add(intArrayOf(e.key, e.value))
    if (pq.size > k) {
      pq.poll()
    }
  }

  val size = if (k > pq.size) pq.size else k
  val answer = IntArray(size)
  for (i in (size - 1) downTo 0) {
    answer[i] = pq.poll()[0]
  }
  return answer
}

