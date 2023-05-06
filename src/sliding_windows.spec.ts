import * as sw from './sliding_windows';

describe('Best Time to Buy and Sell Stock', () => {
  it('should return the best time to buy and sell stock', () => {
    expect(sw.maxProfit([7, 1, 5, 3, 6, 4])).toEqual(5);
    expect(sw.maxProfit([7, 6, 4, 3, 1])).toEqual(0);
  });
});

describe('Longest Substring Without Repeating Characters', () => {
  it('should return the longest substring no repeats', () => {
    expect(sw.lengthOfLongestSubstring('abcabcbb')).toEqual(3);
    expect(sw.lengthOfLongestSubstring('bbbbb')).toEqual(1);
    expect(sw.lengthOfLongestSubstring('pwwkew')).toEqual(3);
  });
});


describe('Longest Repeating Character Replacement', () => {
  it('should return the longest repeating char replacements', () => {
    expect(sw.characterReplacement('ABAB', 2)).toEqual(4);
    expect(sw.characterReplacement('AABABBA', 1)).toEqual(4);
    expect(sw.characterReplacement('ABACD', 2)).toEqual(4);
  });
});

describe('Minimum Window Substring', () => {
  it('should pass test case 1', () => {
    expect(sw.minWindow('ADOBECODEBANC', 'ABC')).toEqual('BANC');
  });

  it('should pass test case 2', () => {
    expect(sw.minWindow('a', 'a')).toEqual('a');
  });

  it('should pass test case 3', () => {
    expect(sw.minWindow('a', 'aa')).toEqual('');
  });

  it('should pass test case 4', () => {
    expect(sw.minWindow('ADOBECODEBANCDDD', 'ABC')).toEqual('BANC');
  });

  it('should pass test case 5', () => {
    expect(sw.minWindow('KADOBECODEBANCDDDEI', 'ABCEKI'))
      .toEqual('KADOBECODEBANCDDDEI');
  });
});
