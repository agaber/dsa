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
    expect(arrays.isAnagram('anagram', 'nagaram')).toEqual(true);
    expect(arrays.isAnagram('rat', 'car')).toEqual(false);
    expect(arrays.isAnagram('monkey', 'monk')).toEqual(false);
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

describe('Valid Sudoku', () => {
  it('should return true when Sudoku is valid', () => {
    const board = [
      ['5', '3', '.', '.', '7', '.', '.', '.', '.'],
      ['6', '.', '.', '1', '9', '5', '.', '.', '.'],
      ['.', '9', '8', '.', '.', '.', '.', '6', '.'],
      ['8', '.', '.', '.', '6', '.', '.', '.', '3'],
      ['4', '.', '.', '8', '.', '3', '.', '.', '1'],
      ['7', '.', '.', '.', '2', '.', '.', '.', '6'],
      ['.', '6', '.', '.', '.', '.', '2', '8', '.'],
      ['.', '.', '.', '4', '1', '9', '.', '.', '5'],
      ['.', '.', '.', '.', '8', '.', '.', '7', '9'],
    ];
    expect(arrays.isValidSudoku(board)).toEqual(true);
  });

  it('should return false when Sudoku is not valid', () => {
    const board = [
      ['8', '3', '.', '.', '7', '.', '.', '.', '.'],
      ['6', '.', '.', '1', '9', '5', '.', '.', '.'],
      ['.', '9', '8', '.', '.', '.', '.', '6', '.'],
      ['8', '.', '.', '.', '6', '.', '.', '.', '3'],
      ['4', '.', '.', '8', '.', '3', '.', '.', '1'],
      ['7', '.', '.', '.', '2', '.', '.', '.', '6'],
      ['.', '6', '.', '.', '.', '.', '2', '8', '.'],
      ['.', '.', '.', '4', '1', '9', '.', '.', '5'],
      ['.', '.', '.', '.', '8', '.', '.', '7', '9'],
    ];
    expect(arrays.isValidSudoku(board)).toEqual(false);
  });

  it('should return false too', () => {
    const board = [
      ['.', '8', '9', '.', '4', '.', '6', '.', '5'],
      ['.', '7', '.', '.', '.', '8', '.', '4', '1'],
      ['5', '6', '.', '9', '.', '.', '.', '.', '8'],
      ['.', '.', '.', '7', '.', '5', '.', '9', '.'],
      ['.', '9', '.', '4', '.', '1', '.', '5', '.'],
      ['.', '3', '.', '9', '.', '6', '.', '1', '.'],
      ['8', '.', '.', '.', '.', '.', '.', '.', '7'],
      ['.', '2', '.', '8', '.', '.', '.', '6', '.'],
      ['.', '.', '6', '.', '7', '.', '.', '8', '.'],
    ];
    expect(arrays.isValidSudoku(board)).toEqual(false);
  });

  // LeetCode says this is valid.
  // Pramp says it is not. :\
  // By the rules they have defined, it looks valid to me.
  it('should still return true', () => {
    const board = [
      ['.', '2', '3', '4', '5', '6', '7', '8', '9'],
      ['1', '.', '.', '.', '.', '.', '.', '.', '.'],
      ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
      ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
      ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
      ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
      ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
      ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
      ['.', '.', '.', '.', '.', '.', '.', '.', '.'],
    ];
    expect(arrays.isValidSudoku(board)).toEqual(true);
  });
});

describe('Encode strings', () => {
  it('should encode and decode strings', () => {
    const original = ['Hello', 'World'];
    const encoder = new arrays.Codec();
    const encoded = encoder.encode(original);

    const decoder = new arrays.Codec();
    const decoded = decoder.decode(encoded);

    expect(decoded).toEqual(original);
  });

  // This doesn't work in Java.
  // Some other languages need to wrap strings in an escape char.
  it('should encode and decode empty strings/arrays', () => {
    const original = [''];
    const encoder = new arrays.Codec();
    const encoded = encoder.encode(original);

    const decoder = new arrays.Codec();
    const decoded = decoder.decode(encoded);

    expect(decoded).toEqual(original);
  });

  it('should encode/decode empty strings with delimeters in it', () => {
    const original = ['To be, or', 'not to be, that', 'is the question'];
    const encoder = new arrays.Codec();
    const encoded = encoder.encode(original);

    const decoder = new arrays.Codec();
    const decoded = decoder.decode(encoded);

    expect(decoded).toEqual(original);
  });
});

describe('Longest Consecutive Sequence', () => {
  it('should pass case 1', () => {
    expect(arrays.longestConsecutive([100, 4, 200, 1, 3, 2])).toEqual(4);
  });

  it('should pass case 2', () => {
    expect(arrays.longestConsecutive([0, 3, 7, 2, 5, 8, 4, 6, 0, 1])).toEqual(9);
  });

  it('should pass case 3', () => {
    expect(arrays.longestConsecutive([1, 0, -1])).toEqual(3);
  });
});