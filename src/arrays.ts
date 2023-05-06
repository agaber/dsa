/**
 * Arrays and Hashing questions.
 * Many of these questions are from the Blink 75 list on LeetCode.
 * 
 * Note: This file also contains solutions that require a "two pointer" 
 * algorithm.
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
 * - Time complexity: O(n)
 * - Space complexity: O(1)
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
};


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
};


/**
 * 3Sum
 * 
 * Given an integer array nums, return all the triplets 
 * [nums[i], nums[j], nums[k]] such that i != j, i != k, 
 * and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * 
 * Notice that the solution set must not contain duplicate triplets.
 * 
 * More two sum fun. This is also a two pointer solution. The twosum solution
 * here is slightly different because the question also makes us worry about
 * ignoring duplicate values. It can be done with a set but there's a more
 * efficient solution (its tricky though).
 * 
 * https://leetcode.com/problems/3sum/
 */
export function threeSum(nums: number[]): number[][] {
  function twoSum3(targetSum: number, startIndex: number): number[][] {
    const twosums: number[][] = [];
    let l = startIndex, r = nums.length - 1;
    while (l < r) {
      if (l > startIndex && nums[l] === nums[l - 1]) {
        l++;
        continue;
      }
      const sum = nums[l] + nums[r];
      if (sum === targetSum) {
        twosums.push([nums[l], nums[r]]);
        l++;
      } else if (sum < targetSum) {
        l++;
      } else {
        r--;
      }
    }
    return twosums;
  }

  nums.sort((x, y) => x - y);
  const threesums: number[][] = [];
  for (let i = 0; i < nums.length; i++) {
    if (i > 0 && nums[i] === nums[i - 1]) {
      continue;
    }
    const twosums = twoSum3(-nums[i], i + 1);
    twosums.forEach(twosum => threesums.push([...[nums[i]], ...twosum]));
  }
  return threesums;
};


/**
 * Given an array of strings strs, group the anagrams together. You can return 
 * the answer in any order.
 * 
 * An Anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, typically using all the original letters exactly
 * once.
 */
export function groupAnagrams(strs: string[]): string[][] {
  // Time: O(m), where m is the length of the string.
  // Space: O(26 * 3), store an array of len 26 and string of len 26 * 2.
  // Use a hash algo a bit better than sorting.
  // I originally tried to do an int hash as if I was hashing for a map
  // but obviously that leads to collisions.
  function hash(str: string): string {
    // return [...str].sort().join('')
    const arr = Array(26).fill(0);
    for (const s of str) {
      arr[(s.codePointAt(0)! - 'a'.codePointAt(0)!)] += 1
    }
    return arr.join('#');
  }

  const map = new Map<string, string[]>();

  // Time: O(n * m), where n is the number of strings 
  //   and m is the length of each string.
  // Space: O(n)
  for (const str of strs) {
    const shash = hash(str);
    const anagrams = map.get(shash) || [];
    map.set(shash, [...anagrams, str]);
  }

  // Time: O(n) where n is the number of strings.
  //       Worst case is that each anagram was unique.
  // Space: O(1) - don't count the output in analysis.
  const result = [];
  for (const arr of map.values()) {
    result.push(arr);
  }
  return result;
};


/**
 * Top K Frequent Elements
 * 
 * Given an integer array nums and an integer k, return the k most frequent
 * elements. You may return the answer in any order.
 * 
 * This is a Blind 75 question.
 * 
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 * 
 * If you have access to it (I think you need to pay for leetcode subscription
 * to see it), check out the editorial section. There are some other pretty
 * cool algorithms to find the Kth element.
 * 
 * https://leetcode.com/problems/top-k-frequent-elements
 */
export function topKFrequent(nums: number[], k: number): number[] {
  const counts = new Map<number, number>();
  nums.forEach(num => counts.set(num, (counts.get(num) || 0) + 1));
  const results: number[] = [...counts.keys()]
    .sort((x, y) => counts.get(y)! - counts.get(x)!);
  const answer: number[] = [];
  for (let i = 0; i < k; i++) {
    answer.push(results[i]);
  }
  return answer;
};


/**
 * Product of Array Except Self
 * 
 * Given an integer array nums, return an array answer such that answer[i] is
 * equal to the product of all the elements of nums except nums[i].
 * 
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
 * integer.
 * 
 * You must write an algorithm that runs in O(n) time and without using the 
 * division operation.
 * 
 * A Blind 75 question. 
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 * See my work in leetcode. There is a way to do this in O(1) space complexity
 * but it confuses me and I am not including it here.
 * 
 * https://leetcode.com/problems/product-of-array-except-self
 */
export function productExceptSelf(nums: number[]): number[] {
  const prefix = Array(nums.length);
  const postfix = Array(nums.length);

  for (let i = 0; i < nums.length; i++) {
    const prev = i === 0 ? 1 : prefix[i - 1];
    prefix[i] = nums[i] * prev;

    const j = nums.length - i - 1;
    const post = j === nums.length - 1 ? 1 : postfix[j + 1];
    postfix[j] = nums[j] * post;
  }

  const answer = Array(nums.length);
  for (let i = 0; i < nums.length; i++) {
    const prev = i === 0 ? 1 : prefix[i - 1];
    const post = i === nums.length - 1 ? 1 : postfix[i + 1];
    answer[i] = prev * post;
    // Something weird on nodejs (it doesn't happen on leetcode)
    if (answer[i] === -0) answer[i] = 0;
  }
  return answer;
};


/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be 
 * validated according to the following rules:
 * 
 * - Each row must contain the digits 1-9 without repetition.
 * - Each column must contain the digits 1-9 without repetition.
 * - Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 
 *   without repetition.
 * 
 * Note: A Sudoku board (partially filled) could be valid but is not necessarily
 * solvable. Only the filled cells need to be validated according to the
 * mentioned rules.
 * 
 * https://leetcode.com/problems/valid-sudoku/
 */
export function isValidSudoku(board: string[][]): boolean {
  function getQuadrant(row: number, col: number): number {
    row = Math.floor(row / 3);
    col = Math.floor(col / 3);
    return (row * 3) + col;
  }

  // Can use a map of sets or an array of sets too.
  // Bitmask is another option.
  const rows = Array(9);
  const columns = Array(9);
  const quads = Array(9);

  for (let i = 0; i < 9; i++) {
    rows[i] = Array(10).fill(false);
    columns[i] = Array(10).fill(false);
    quads[i] = Array(10).fill(false);
  }

  for (let row = 0; row < 9; row++) {
    for (let col = 0; col < 9; col++) {
      const val = board[row][col];

      if (val === '.') {
        continue;
      }

      // Make sure it's a valid single digit number.
      // This check isn't necessary for LeetCode's tests, I think.
      if (!/^[0-9]$/.test(val)) {
        return false;
      }

      const num = parseInt(val);
      if (rows[row][num]) {
        return false;
      } else {
        rows[row][num] = true;
      }

      if (columns[col][num]) {
        return false;
      } else {
        columns[col][num] = true;
      }

      const quadrant = getQuadrant(row, col);
      if (quads[quadrant][num]) {
        return false;
      } else {
        quads[quadrant][num] = true
      }
    }
  }

  return true;
};


/**
 * Design an algorithm to encode a list of strings to a string. The encoded 
 * string is then sent over the network and is decoded back to the original
 * list of strings.
 * 
 * Machine 1 (sender) has the function:
 * 
 *   string encode(vector<string> strs) {
 *     // ... your code
 *     return encoded_string;
 *   }
 * 
 * Machine 2 (receiver) has the function:
 * 
 *    vector<string> decode(string s) {
 *       //... your code
 *       return strs;
 *     }
 * 
 * So Machine 1 does:
 * 
 *     string encoded_string = encode(strs);
 * 
 * and Machine 2 does:
 * 
 *     vector<string> strs2 = decode(encoded_string);
 * 
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * 
 * Implement the encode and decode methods.
 * 
 * You are not allowed to solve the problem using any serialize methods
 * (such as eval).
 * 
 * Constraints:
 * - 1 <= strs.length <= 200
 * - 0 <= strs[i].length <= 200
 * - strs[i] contains any possible characters out of 256 valid ASCII characters.
 * 
 * https://leetcode.com/problems/encode-and-decode-strings/
 */
export class Codec {
  private readonly DELIMITER = '🤡';

  /** Encodes a list of strings to a single string. */
  encode(strs: string[]): string {
    return strs.map(s => s.replace(/,/g, this.DELIMITER)).join(',');
  }

  /** Decodes a single string to a list of strings. */
  decode(s: string): string[] {
    const re = new RegExp(this.DELIMITER, 'g');
    return s.split(',').map(s => s.replace(re, ','));
  }
};


/**
 * Given an unsorted array of integers nums, return the length of the longest
 * consecutive elements sequence.
 * 
 * You must write an algorithm that runs in O(n) time.
 * 
 * https://leetcode.com/problems/longest-consecutive-sequence
 */
export function longestConsecutive(nums: number[]): number {
  const set = new Set<number>(nums);
  let max = 0;
  for (const num of nums) {
    let curr = num;
    if (set.has(curr - 1)) {
      continue;
    }
    let localMax = 1;
    while (set.has(curr + 1)) {
      set.delete(curr);
      localMax++;
      curr++;
    }
    max = Math.max(localMax, max);
  }
  return max;
};


/**
 * Valid Palindrome
 * 
 * A phrase is a palindrome if, after converting all uppercase letters into
 * lowercase letters and removing all non-alphanumeric characters, it reads the
 * same forward and backward. Alphanumeric characters include letters and
 * numbers.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * https://leetcode.com/problems/valid-palindrome/
 */
export function isValidPalindrome(s: string): boolean {
  function isLetter(s: string) {
    return !!s.match(/[a-z0-9]/i);
  }

  let l = 0, r = s.length - 1;
  while (l <= r) {
    if (!isLetter(s[l])) {
      l++;
    } else if (!isLetter(s[r])) {
      r--;
    } else if (s[l].toLowerCase() !== s[r].toLowerCase()) {
      return false;
    } else {
      l++;
      r--;
    }
  }
  return true;
}
