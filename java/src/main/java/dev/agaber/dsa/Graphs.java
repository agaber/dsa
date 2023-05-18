package dev.agaber.dsa;

public class Graphs {
  /**
   * 200. Number of Islands
   *
   * <p>Given an m x n 2D binary grid grid which represents a map of '1's
   * (land) and '0's (water), return the number of islands.
   *
   * <p>An island is surrounded by water and is formed by connecting adjacent
   * lands horizontally or vertically. You may assume all four edges of the grid
   * are all surrounded by water.
   *
   * <ul> Constraints:
   *   <li>m == grid.length
   *   <li>n == grid[i].length
   *   <li>1 <= m, n <= 300
   *   <li>grid[i][j] is '0' or '1'.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/number-of-islands/
   *   <li>Time complexity: O(n * m)
   *   <li>Space complexity: O(1)
   * </ul>
   */
  public static final class NumberOfIslands {
    private static final int[][] DIRECTIONS = new int[][] {
        {-1,  0}, // Up
        { 0,  1}, // Right
        { 1,  0}, // Down
        { 0, -1}, // Left
    };

    private char[][] grid;
    private int m;
    private int n;

    public int numIslands(char[][] grid) {
      this.grid = grid;
      this.m = grid.length;
      this.n = grid[0].length;
      int count = 0;
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (grid[i][j] == '1') {
            count++;
            explore(i, j);
          }
        }
      }
      return count;
    }

    private void explore(int x, int y) {
      grid[x][y] = 'X';
      for (int[] delta : DIRECTIONS) {
        int xx = x + delta[0];
        int yy = y + delta[1];
        if (isInBounds(xx, yy) && grid[xx][yy] == '1') {
          explore(xx, yy);
        }
      }
    }

    private boolean isInBounds(int x, int y) {
      return x >= 0 && x < m && y >= 0 && y < n;
    }
  }

  /**
   * Game of Life
   *
   * <p>According to Wikipedia's article: "The Game of Life, also known simply as
   * Life, is a cellular automaton devised by the British mathematician
   * John Horton Conway in 1970."
   *
   * <p>The board is made up of an m x n grid of cells, where each cell has an
   * initial state: live (represented by a 1) or dead (represented by a 0). Each
   * cell interacts with its eight neighbors (horizontal, vertical, diagonal)
   * using the following four rules (taken from the above Wikipedia article):
   *
   * <ul>
   *   <li>Any live cell with fewer than two live neighbors dies as if caused by
   *   under-population.
   *   <li>Any live cell with two or three live neighbors lives on to the next
   *   generation.
   *   <li>Any live cell with more than three live neighbors dies, as if by
   *   over-population.
   *   <li>Any dead cell with exactly three live neighbors becomes a live cell,
   *   as if by reproduction.
   *   <li>The next state is created by applying the above rules simultaneously
   *   to every cell in the current state, where births and deaths occur
   *   simultaneously. Given the current state of the m x n grid board, return
   *   the next state.
   * </ul>
   *
   * <ul> Constraints:
   *   <li>m == board.length
   *   <li>n == board[i].length
   *   <li>1 <= m, n <= 25
   *   <li>board[i][j] is 0 or 1.
   * </ul>
   *
   * <ul>
   *   <li>List: None
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/game-of-life/
   *   <li>Time complexity: O(2(m * n)) = O(m * n)
   *   <li>Space complexity: O(1)
   * </ul>
   *
   * Discussion: They want this in O(1) space complexity (could be a follow-up
   * question).
   */
  public static final class GameOfLife {
    private static int[][] DIRECTIONS = new int[][] {
        {-1,  0}, // Up
        {-1,  1}, // Up right
        { 0,  1}, // Right
        { 1,  1}, // Down right
        { 1,  0}, // Down
        { 1, -1}, // Down left
        { 0, -1}, // Left
        {-1, -1}, // Up left
    };

    // previously 0, currently 0 = 0
    // previously 0, currently 1 = 2
    // previously 1, currently 0 = 3
    // previously 1, currently 1 = 1

    public void gameOfLife(int[][] board) {
      int m = board.length;
      int n = board[0].length;

      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          explore(board, i, j);
        }
      }

      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          if (board[i][j] == 2) {
            board[i][j] = 1;
          } else if (board[i][j] == 3) {
            board[i][j] = 0;
          }
        }
      }
    }

    private void explore(int[][] board, int x, int y) {
      int liveCellCount = 0;
      for (int[] delta : DIRECTIONS) {
        int xx = x + delta[0];
        int yy = y + delta[1];
        if (isInBounds(board, xx, yy) && (board[xx][yy] == 1 || board[xx][yy] == 3)) {
          liveCellCount++;
        }
      }

      int currentCellValue = board[x][y];
      if (currentCellValue == 1) {
        if (liveCellCount < 2) {
          board[x][y] = 3;
        } else if (liveCellCount == 2 || liveCellCount == 3) {
          board[x][y] = 1;
        } else { // liveCellCount > 3
          board[x][y] = 3;
        }
      } else if (liveCellCount == 3) {
        board[x][y] = 2;
      }
    }

    private boolean isInBounds(int[][] board, int x, int y) {
      int m = board.length;
      int n = board[0].length;
      return x >= 0 && x < m && y >= 0 && y < n;
    }
  }
}
