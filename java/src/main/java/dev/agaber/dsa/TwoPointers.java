package dev.agaber.dsa;

import java.util.*;

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

  /**
   * 3 Sum
   *
   * <p>Given an integer array nums, return all the triplets
   * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and
   * nums[i] + nums[j] + nums[k] == 0.
   *
   * <p>Notice that the solution set must not contain duplicate triplets.
   *
   * <ul> Constraints:
   *   <li>3 <= nums.length <= 3000
   *   <li>-105 <= nums[i] <= 105
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/3sum/
   *   <li>Time complexity: O(nlogn + n^2): asymptotically equivalent to O(n^2).
   *   <li>Space complexity: O(n)
   * </ul>
   */
  public static final class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
      Arrays.sort(nums);
      var threeSums = new ArrayList<List<Integer>>();
      for (int i = 0; i < nums.length - 2; i++) {
        int num = nums[i];
        if (i > 0 && num == nums[i - 1]) {
          continue;
        }
        var twoSums = twoSumII(nums, i + 1, -num);
        twoSums.forEach(sums -> threeSums.add(Arrays.asList(num, sums.get(0), sums.get(1))));
      }
      return threeSums;
    }

    private List<List<Integer>> twoSumII(int[] nums, int startIndex, int target) {
      List<List<Integer>> twosums = new ArrayList<>();
      int l = startIndex;
      int r = nums.length - 1;
      while (l < r) {
        int sum = nums[l] + nums[r];
        if (sum == target) {
          var sums = new LinkedList<Integer>();
          twosums.add(Arrays.asList(nums[l], nums[r]));
          int left = nums[l];
          l++;
          while (l < r && nums[l] == left) {
            l++;
          }
        } else if (sum > target) {
          r--;
        } else {
          l++;
        }
      }
      return twosums;
    }
  }

  /**
   * Valid Palindrome
   *
   * <p>A phrase is a palindrome if, after converting all uppercase letters into
   * lowercase letters and removing all non-alphanumeric characters, it reads
   * the same forward and backward. Alphanumeric characters include letters and
   * numbers.
   *
   * <p>Given a string s, return true if it is a palindrome, or false otherwise.
   *
   * <ul> Constraints:
   *   <li>1 <= s.length <= 2 * 105
   *   <li>s consists only of printable ASCII characters.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li>https://leetcode.com/problems/valid-palindrome/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(1)
   * </ul>
   */
  public static boolean isPalindrome(String s) {
    if (s.isBlank() || s.length() == 1) {
      return true;
    }
    int l = 0;
    int r = s.length() - 1;
    while (l < r) {
      char left = Character.toLowerCase(s.charAt(l));
      char right = Character.toLowerCase(s.charAt(r));
      if (!Character.isLetter(left)) {
        l++;
      } else if (!Character.isLetter(right)) {
        r--;
      } else if (left != right) {
        return false;
      } else {
        l++;
        r--;
      }
    }
    return true;
  }

  private TwoPointers() {}
}
