package dev.agaber.dsa;

import java.util.*;

final class DynamicProgramming {
  /**
   * Fibonacci Numbers
   *
   * <p>The Fibonacci numbers, commonly denoted F(n) form a sequence, called the
   * Fibonacci sequence, such that each number is the sum of the two preceding
   * ones, starting from 0 and 1. That is,
   *
   * <pre>
   * F(0) = 0, F(1) = 1
   * F(n) = F(n - 1) + F(n - 2), for n > 1.
   * </pre>
   *
   * <p>Given n, calculate F(n).
   *
   * <ul>
   *   <li>List: None
   *   <li>Level: Easy
   *   <li><a href="https://leetcode.com/problems/fibonacci-number/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(1)
   * </ul>
   *
   * <p>Discussion: A classic DP problem. Like all DP problems, this has a
   * top-down recursive solution with memoization and a bottom up solution using
   * tabulation. I have various solutions on LeetCode but here it's using the
   * optimized solution which doesn't even use a table since we only need the
   * last two values.
   */
  static long fibonacci(int n) {
    if (n <= 1) {
      return n;
    }
    long minus2 = 0;
    long minus1 = 1;
    long fib = 0;
    for (int i = 2; i < n + 1; i++) {
      fib = minus1 + minus2;
      minus2 = minus1;
      minus1 = fib;
    }
    return fib;
  }
}
