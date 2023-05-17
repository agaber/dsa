package dev.agaber.dsa;

final class TwoPointers {
  /**
   * Two Sum II - Input Array Is Sorted
   *
   * <p>Given a 1-indexed array of integers numbers that is already sorted in
   * non-decreasing order, find two numbers such that they add up to a specific
   * target number. Let these two numbers be numbers[index1] and numbers[index2]
   * where 1 <= index1 < index2 <= numbers.length.
   *
   * <p>Return the indices of the two numbers, index1 and index2, added by one as an
   * integer array [index1, index2] of length 2.
   *
   * <p>The tests are generated such that there is exactly one solution. You may not
   * use the same element twice.
   *
   * <p>Your solution must use only constant extra space.
   *
   * <ul>
   *   <li>List: None
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(1)
   * </ul>
   */
  public static int[] twoSumII(int[] numbers, int target) {
    int l = 0;
    int r = numbers.length - 1;
    while (l < r) {
      int sum = numbers[l] + numbers[r];
      if (sum == target) {
        return new int[] {l + 1, r + 1};
      } else if (sum > target) {
        r--;
      } else {
        l++;
      }
    }
    return new int[0];
  }

  private TwoPointers() {}
}
