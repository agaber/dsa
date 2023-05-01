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
    let digit = Number(binary[i]);
    number <<= 1;
    number += digit;
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
};


// TODO: Add numbers without using +.
export function sum(num1: number, num2: number): number {
  return num1 + num2;
}

