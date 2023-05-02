import * as arrays from './arrays';

describe('Contains Duplicates', () => {
  it('should detect duplicates', () => {
    expect(arrays.containsDuplicate([1, 2, 3, 1])).toEqual(true);
    expect(arrays.containsDuplicate([1, 1, 1, 3, 3, 4, 3, 2, 4, 2]))
      .toEqual(true);
    expect(arrays.containsDuplicate([1, 2, 3, 4])).toEqual(false);
  });
});

describe('anagrams', () => {
  it('should determine if strings are anagrams', () => {
    expect(arrays.isAnagram("anagram", "nagaram")).toEqual(true);
    expect(arrays.isAnagram("rat", "car")).toEqual(false);
    expect(arrays.isAnagram("monkey", "monk")).toEqual(false);
  });

  it('should group anagram', () => {
    expect(arrays.groupAnagrams(['eat', 'tea', 'tan', 'ate', 'nat', 'bat']))
      .toEqual([['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']]);
    expect(arrays.groupAnagrams([''])).toEqual([['']]);
    expect(arrays.groupAnagrams(['a'])).toEqual([['a']]);
    expect(arrays.groupAnagrams(['bdddddddddd', 'bbbbbbbbbbc']))
      .toEqual([['bdddddddddd'], ['bbbbbbbbbbc']]);

  });
});

describe('Two Sum', () => {
  it('should return two sum', () => {
    expect(arrays.twoSum([2, 7, 11, 15], 9)).toEqual([0, 1]);
    expect(arrays.twoSum([3, 2, 4], 6)).toEqual([1, 2]);
    expect(arrays.twoSum([3, 3], 6)).toEqual([0, 1]);
  });

  it('should return two sum 2', () => {
    expect(arrays.twoSumII([2, 7, 11, 15], 9)).toEqual([1, 2]);
    expect(arrays.twoSumII([2, 3, 4], 6)).toEqual([1, 3]);
    expect(arrays.twoSumII([3, 3], 6)).toEqual([1, 2]);
  });

  it('should count the number of pairs that sum to a value', () => {
    expect(arrays.countNumPairs([0, 3, 2, 5], 5)).toEqual(2);
    expect(arrays.countNumPairs([1, 2, 1, 1], 3)).toEqual(3);
    expect(arrays.countNumPairs([1, 2, 1, 1, 2], 3)).toEqual(6);
  });

  it('should find the number of pairs that sum to a value', () => {
    expect(arrays.findNumPairs([0, 3, 2, 5], 5)).toEqual([[3, 2], [0, 5]]);
    expect(arrays.findNumPairs([1, 2, 1, 1], 3)).toEqual([[1, 2], [2, 1], [2, 1]]);
    expect(arrays.findNumPairs([1, 2, 1, 1, 2], 3))
      .toEqual([[1, 2], [2, 1], [2, 1], [1, 2], [1, 2], [1, 2]]);
  });
});

describe('Top K Frequent Numbers', () => {
  it('should return top k frequent numbers', () => {
    expect(arrays.topKFrequent([1, 1, 1, 2, 2, 3], 2)).toEqual([1, 2]);
    expect(arrays.topKFrequent([1], 1)).toEqual([1]);
    expect(arrays.topKFrequent([3, 0, 1, 0], 1)).toEqual([0]);
  });
});

describe('Product of Array Except Self', () => {
  it('should return array of products', () => {
    expect(arrays.productExceptSelf([1, 2, 3, 4])).toEqual([24, 12, 8, 6]);
    expect(arrays.productExceptSelf([-1, 1, 0, -3, 3])).toEqual([0, 0, 9, 0, 0]);
  });
});