/**
 * Best Time to Buy and Sell Stock
 * 
 * You are given an array prices where prices[i] is the price of a given stock 
 * on the ith day.
 * 
 * You want to maximize your profit by choosing a single day to buy one stock and
 * choosing a different day in the future to sell that stock.
 * 
 * Return the maximum profit you can achieve from this transaction. If you cannot
 * achieve any profit, return 0.
 * 
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
export function maxProfit(prices: number[]): number {
  let l = 0;
  let maxp = 0;
  for (let r = 0; r < prices.length; r++) {
    if (prices[l] < prices[r]) {
      const currentProfit = prices[r] - prices[l];
      maxp = Math.max(maxp, currentProfit);
    } else {
      l = r;
    }
  }
  return maxp;
};


/**
 * Longest Substring Without Repeating Characters
 * 
 * Given a string s, find the length of the longest substring without repeating
 * characters.
 * 
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
export function lengthOfLongestSubstring(s: string): number {
  const seen = new Set<string>();
  let l = 0;
  let r = 0;
  let max = 0;
  while (l <= r && r < s.length) {
    if (!seen.has(s[r])) {
      seen.add(s[r]);
      r++;
    } else {
      seen.delete(s[l]);
      l++;
    }
    max = Math.max(max, r - l);
  }
  return max;
};


/**
 * Longest Repeating Character Replacement
 *
 * You are given a string s and an integer k. You can choose any character of the
 * string and change it to any other uppercase English character. You can perform
 * this operation at most k times.
 * 
 * Return the length of the longest substring containing the same letter you can
 * get after performing the above operations.
 * 
 * https://leetcode.com/problems/longest-repeating-character-replacement
 */
export function characterReplacement(s: string, k: number): number {
  let l = 0;
  let max = 0;
  const charCounts = new Map<string, number>();

  for (let r = 0; r < s.length; r++) {
    charCounts.set(s[r], (charCounts.get(s[r]) || 0) + 1);

    while (windowLength(l, r) - mostCommonCharCounts(charCounts) > k) {
      charCounts.set(s[l], (charCounts.get(s[l]) || 0) - 1);
      l++;
    }

    max = Math.max(max, windowLength(l, r));
  }

  return max;

  function windowLength(l: number, r: number): number {
    return r - l + 1;
  }

  function mostCommonCharCounts(charCounts: Map<string, number>): number {
    return Math.max(...charCounts.values());
  }
};

/**
 * Minimum Window Substring
 * 
 * Given two strings s and t of lengths m and n respectively, return the minimum
 * window substring of s such that every character in t (including duplicates) is
 * included in the window. If there is no such substring, return the empty
 * string "".
 * 
 * The testcases will be generated such that the answer is unique.
 * 
 * https://leetcode.com/problems/minimum-window-substring/
 * https://www.pramp.com/challenge/wqNo9joKG6IJm67B6z34
 */
export function minWindow(s: string, t: string): string {
  const tcount = new Map<string, number>();
  [...t].forEach(str => tcount.set(str, (tcount.get(str) || 0) + 1));
  const need = t.length;

  const scount = new Map<string, number>();
  let have = 0;
  let minSubstring = '';
  let l = 0;

  for (let r = 0; r < s.length; r++) {
    if (tcount.has(s[r])) {
      scount.set(s[r], (scount.get(s[r]) || 0) + 1);
      if (scount.get(s[r])! <= tcount.get(s[r])!) {
        have++;
      }
    }

    while (have === need) {
      const substringLength = r - l + 1;
      if (minSubstring.length === 0 || substringLength < minSubstring.length) {
        minSubstring = s.substring(l, l + substringLength);
      }

      if (scount.has(s[l])) {
        scount.set(s[l], scount.get(s[l])! - 1);
        if (scount.get(s[l])! < tcount.get(s[l])!) {
          have--;
        }
      }
      l++;
    }
  }

  return minSubstring;
};