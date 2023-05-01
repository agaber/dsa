/**
 * Arrays and Hashing questions. 
 * 
 * Many of these questions are from the Blink 75 list on LeetCode. These types of 
 * questions are very popular in interviews, especially phone screens.
 */

/**
 * Given an integer array nums, return true if any value appears at least twice 
 * in the array, and return false if every element is distinct. From the Blind 75
 * list.
 * 
 * As with most of these types of questions, there is a naive O(n^2) solution.
 * There is also an O(n log n) solution if you choose to sort the array.
 * 
 * This solution:
 * - Time complexity: O(n)
 * - Space complexity: O(n)
 * 
 * https://leetcode.com/problems/contains-duplicate/
 */
export function containsDuplicate(nums: number[]): boolean {
  const set = new Set<number>();
  for (const num of nums) {
    if (set.has(num)) {
      return true;
    }
    set.add(num);
  }
  return false;
};


/**
 * Given two strings s and t, return true if t is an anagram of s, and false
 * otherwise.
 * 
 * An Anagram is a word or phrase formed by rearranging the letters of a 
 * different word or phrase typically using all the original letters exactly
 * once.
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * https://leetcode.com/problems/valid-anagram/
 */
export function isAnagram(s: string, t: string): boolean {
  if (s.length !== t.length) {
    return false;
  }

  const counter = new Map<string, number>();
  for (let i = 0; i < s.length; i++) {
    counter.set(s.charAt(i), (counter.get(s.charAt(i)) || 0) + 1);
    counter.set(t.charAt(i), (counter.get(t.charAt(i)) || 0) - 1);
  }

  return [...counter.values()].filter(count => count > 0).length === 0;
};

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
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * https://leetcode.com/problems/two-sum
 */
export function twoSum(nums: number[], target: number): number[] {
  const seen = new Map<number, number>();
  for (let i = 0; i < nums.length; i++) {
    const compliment = target - nums[i];
    if (seen.has(compliment)) {
      return [seen.get(compliment)!, i];
    }
    seen.set(nums[i], i);
  }
  return [];
};

/**
 * Two Sum II - Input Array Is Sorted
 * 
 * Given a 1-indexed array of integers numbers that is already sorted in 
 * non-decreasing order, find two numbers such that they add up to a specific
 * target number. Let these two numbers be numbers[index1] and numbers[index2] 
 * where 1 <= index1 < index2 <= numbers.length.
 * 
 * Return the indices of the two numbers, index1 and index2, added by one as an
 * integer array [index1, index2] of length 2.
 * 
 * The tests are generated such that there is exactly one solution. You may not
 * use the same element twice.
 * 
 * Your solution must use only constant extra space.
 * 
 * This is not part of the Blind 75 btw but it is important. The infamous
 * Google Interview YouTube video covers this. Note: this is really part of the
 * two pointer family of solutions.
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 * 
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
export function twoSumII(numbers: number[], target: number): number[] {
  let l = 0;
  let r = numbers.length - 1;
  while (l < r) {
    const sum = numbers[l] + numbers[r];
    if (sum == target) {
      return [l + 1, r + 1];
    } else if (sum > target) {
      r--;
    } else {
      l++;
    }
  }
  return [];
};

/**
  * Given an array A of integers and a target value. Find out how many
  * pairs(A[i], A[j]) sum up to the value, where i is less than j.
  *
  * [0, 3, 2, 5], 5  -> 2,
  * Explanation: (3, 2), (0, 5)
  *
  * [1, 2, 1, 1], 3  -> 3, 
  * Explanation: (1, 2), (2, 1), (2, 1)
  *
  * [1, 2, 1, 1, 2], 3 -> 6, 
  * Explanation: (1, 2), (2, 1), (2, 1), (1, 2), (1, 2), (1, 2)
  * 
  * This is NOT part of the Blind 75 but I thought this question was another
  * extension of the classic two sum problem.
  */
export function countNumPairs(numbers: number[], target: number): number {
  let result = 0;
  const counts = new Map<number, number>();
  for (let i = 0; i < numbers.length; i++) {
    const compliment = target - numbers[i];
    if (counts.has(compliment)) {
      result += counts.get(compliment) || 0;
    }
    counts.set(numbers[i], (counts.get(numbers[i]) || 0) + 1);
  }
  return result;
}

/** Same as countNumPairs except actually return the pairs. */
export function findNumPairs(numbers: number[], target: number): number[][] {
  let result: number[][] = [];
  const counts = new Map<number, number>();
  for (let i = 0; i < numbers.length; i++) {
    const number = numbers[i];
    const compliment = target - number;
    const count = counts.get(compliment) || 0;
    for (let j = 0; j < count; j++) {
      result.push([compliment, number]);
    }
    counts.set(number, (counts.get(number) || 0) + 1);
  }
  return result;
}