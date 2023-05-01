export function toBinaryString(number: number, base = 32, addSpaces = true): string {
  let binary: string = '';
  for (let i = 0; i < base; i++) {
    if (addSpaces && i > 0 && i % 4 === 0) {
      binary = " " + binary
    }
    binary = String(number & 1) + binary;
    number = number >> 1;
  }
  return binary;
}

export function fromBinaryString(binary: string): number {
  // binary = binary.replace(/ /g, '');
  let number = binary.startsWith('1') ? -1 : 0;
  for (let i = 0; i < binary.length; i++) {
    if (binary[i] === ' ') {
      continue;
    }
    if (binary[i] !== '0' && binary[i] !== '1') {
      throw new Error('invalid binary string');
    }
    number <<= 1;
    number += Number(binary[i]);
  }
  return number;
}

/**
 * Write a function that takes the binary representation of an unsigned integer
 * and returns the number of '1' bits it has (also known as the Hamming weight).
 * 
 * https://leetcode.com/problems/number-of-1-bits/
 */
export function hammingWeight(n: number): number {
  // For now, I memorized the hamming weight formula.
  let count = 0;
  while (n !== 0) {
    n = n & (n - 1);
    count++;
  }
  return count;

  // Other implementations need to assume the number of bits.
  // Doing this while n != 0 will not work for negative numbers.
  // let count = 0;
  // for (let i = 0; i < 32; i++) {
  //   if ((n & 1) == 1) count++;
  //   n >>= 1;
  // }
  // return count;
};

/**
 * Given an integer n, return an array ans of length n + 1 such that for each i 
 * (0 <= i <= n), ans[i] is * the number of 1's in the binary representation of i.
 * 
 * https://leetcode.com/problems/counting-bits/
 */
function countBits(n: number): number[] {
  let answer = Array(n + 1);
  for (let i = 0; i <= n; i++) {
    answer[i] = hammingWeight(i);
  }
  return answer;
};

// TODO: Add numbers without using +.
export function sum(num1: number, num2: number): number {
  return num1 + num2;
}
