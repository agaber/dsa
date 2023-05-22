package dev.agaber.dsa

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
 * - List: Bline 75
 * - Level: Easy
 * - Link: https://leetcode.com/problems/contains-duplicate/
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