package dev.agaber.dsa;

import java.util.*;

final class MathAndGeometry {
  /**
   * Spiral Matrix
   *
   * <p>Given an m x n matrix, return all elements of the matrix in spiral
   * order.
   *
   * <ul>Constraints:
   *   <li>m == matrix.length
   *   <li>n == matrix[i].length
   *   <li>1 <= m, n <= 10
   *   <li>-100 <= matrix[i][j] <= 100
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li><a href="https://leetcode.com/problems/spiral-matrix/">LeetCode</a>
   *   <li>Time complexity: O(m * n)
   *   <li>Space complexity: O(1)
   * </ul>
   *
   * <p>Discussion: There's a couple of ways to solve this and this isn't my
   * most clever attempt but it performs optimally. It takes heavy advantage of
   * the restriction that the values in the matrix are within -100 to 100.
   */
  static final class SpiralMatrix {
    private static final int VISITED = 101;

    public List<Integer> spiralOrder(int[][] matrix) {
      int m = matrix.length;
      int n = matrix[0].length;
      int size = m * n;
      List<Integer> result = new ArrayList<>(size);

      int row = 0;
      int col = 0;
      visit(row, col, matrix, result);
      int i = 1;
      while (i < size) {
        while (isValidMove(row, col + 1, m, n, matrix)) {
          visit(row, ++col, matrix, result);
          i++;
        }

        while (isValidMove(row + 1, col, m, n, matrix)) {
          visit(++row, col, matrix, result);
          i++;
        }

        while (isValidMove(row, col - 1, m, n, matrix)) {
          visit(row, --col, matrix, result);
          i++;
        }

        while (isValidMove(row - 1, col, m, n, matrix)) {
          visit(--row, col, matrix, result);
          i++;
        }
      }

      return result;
    }

    private boolean isValidMove(int x, int y, int m, int n, int[][] matrix) {
      return x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] != VISITED;
    }

    private void visit(int x, int y, int[][] matrix, List<Integer> result) {
      result.add(matrix[x][y]);
      matrix[x][y] = VISITED;
    }
  }
}
