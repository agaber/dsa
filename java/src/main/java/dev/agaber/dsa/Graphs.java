package dev.agaber.dsa;

import static com.google.common.collect.ImmutableList.toImmutableList;
import static java.util.function.Predicate.not;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class Graphs {
  /**
   * Number of Islands
   *
   * <p>Given an m x n 2D binary grid grid which represents a map of '1's
   * (land) and '0's (water), return the number of islands.
   *
   * <p>An island is surrounded by water and is formed by connecting adjacent
   * lands horizontally or vertically. You may assume all four edges of the grid
   * are all surrounded by water.
   *
   * <ul>
   * Constraints:
   * <li>m == grid.length
   * <li>n == grid[i].length
   * <li>1 <= m, n <= 300
   * <li>grid[i][j] is '0' or '1'.
   * </ul>
   *
   * <ul>
   * <li>List: Blind 75
   * <li>Level: Medium
   * <li>https://leetcode.com/problems/number-of-islands/
   * <li>Time complexity: O(n * m)
   * <li>Space complexity: O(1)
   * </ul>
   */
  static final class NumberOfIslands {
    private static final int[][] DIRECTIONS = new int[][] {
        { -1, 0 }, // Up
        { 0, 1 }, // Right
        { 1, 0 }, // Down
        { 0, -1 }, // Left
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
   * <p>According to Wikipedia's article: "The Game of Life, also known simply
   * as Life, is a cellular automaton devised by the British mathematician
   * John Horton Conway in 1970."
   *
   * <p>The board is made up of an m x n grid of cells, where each cell has an
   * initial state: live (represented by a 1) or dead (represented by a 0). Each
   * cell interacts with its eight neighbors (horizontal, vertical, diagonal)
   * using the following four rules (taken from the above Wikipedia article):
   *
   * <ul>
   *   <li>Any live cell with fewer than two live neighbors dies as if caused by
   *       under-population.
   *   <li>Any live cell with two or three live neighbors lives on to the next
   *       generation.
   *   <li>Any live cell with more than three live neighbors dies, as if by
   *       over-population.
   *   <li>Any dead cell with exactly three live neighbors becomes a live cell,
   *       as if by reproduction.
   *   <li>The next state is created by applying the above rules simultaneously
   *       to every cell in the current state, where births and deaths occur
   *       simultaneously. Given the current state of the m x n grid board, return
   *       the next state.
   * </ul>
   *
   * <ul>Constraints:
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
   * Discussion: They want this in O(1) space complexity.
   */
  static final class GameOfLife {
    private static final int[][] DIRECTIONS = new int[][] {
        { -1, 0 }, // Up
        { -1, 1 }, // Up right
        { 0, 1 }, // Right
        { 1, 1 }, // Down right
        { 1, 0 }, // Down
        { 1, -1 }, // Down left
        { 0, -1 }, // Left
        { -1, -1 }, // Up left
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

  /**
   * Pacific Atlantic Water Flow
   *
   * <p>There is an m x n rectangular island that borders both the Pacific Ocean
   * and Atlantic Ocean. The Pacific Ocean touches the island's left and top
   * edges, and the Atlantic Ocean touches the island's right and bottom edges.
   *
   * <p>The island is partitioned into a grid of square cells. You are given an
   * m x n integer matrix heights where heights[r][c] represents the height
   * above sea level of the cell at coordinate (r, c).
   *
   * <p>The island receives a lot of rain, and the rain water can flow to
   * neighboring cells directly north, south, east, and west if the neighboring
   * cell's height is less than or equal to the current cell's height. Water can
   * flow from any cell adjacent to an ocean into the ocean.
   *
   * <p>Return a 2D list of grid coordinates result where result[i] = [ri, ci]
   * denotes that rain water can flow from cell (ri, ci) to both the Pacific and
   * Atlantic oceans.
   *
   * <ul>Constraints:
   *   <li>m == heights.length
   *   <li>n == heights[r].length
   *   <li>1 <= m, n <= 200
   *   <li>0 <= heights[r][c] <= 105
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/game-of-life/
   *   <li>Time complexity: O(m * n), I think
   *   <li>Space complexity: O(m * n)
   * </ul>
   *
   * Discussion: The trick here (and I didn't figure this out on my own
   * originally) was to go backwards -- start at the oceans and explore inland.
   * Do that for both oceans and then return the points they have in common.
   * I'm not aware of any optimization that allows us to avoid exploring the
   * same point multiple times.
   */
  static final class PacificAtanticWaterFlow {
    private static final int[][] DIRECTIONS = new int[][] {
        { -1, 0 }, // Up
        { 0,  1 }, // Right
        { 1,  0 }, // Down
        { 0, -1 }, // Left
    };

    private int[][] grid;
    private int m;
    private int n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
      this.grid = heights;
      this.m = heights.length;
      this.n = heights[0].length;

      var atlanticPoints = new HashSet<Point>();
      var pacificPoints = new HashSet<Point>();

      // Explore top and bottom rows.
      for (int col = 0; col < n; col++) {
        explore(0, col, pacificPoints);
        explore(m - 1, col, atlanticPoints);
      }

      // Explore left and right columns.
      for (int row = 0; row < n; row++) {
        explore(row, 0, pacificPoints);
        explore(row, n - 1, atlanticPoints);
      }

      // Return union of points.
      pacificPoints.retainAll(atlanticPoints);
      return pacificPoints.stream().map(Point::toList).collect(toImmutableList());
    }

    private void explore(int r, int c, Set<Point> visited) {
      Point p = new Point(r, c);
      visited.add(p);
      for (int[] delta : DIRECTIONS) {
        var next = new Point(p.x() + delta[0], p.y() + delta[1]);
        if (isInBounds(next) && isValidMove(p, next) && !visited.contains(next)) {
          explore(next.x(), next.y(), visited);
        }
      }
    }

    private boolean isInBounds(Point p) {
      return p.x() >= 0 && p.x() < m && p.y() >= 0 && p.y() < n;
    }

    private boolean isValidMove(Point current, Point next) {
      return valueAt(current) <= valueAt(next);
    }

    private int valueAt(Point p) {
      return grid[p.x()][p.y()];
    }

    private record Point(int x, int y) {
      public List<Integer> toList() {
        return Arrays.asList(x, y);
      }
    }
  }

  /**
   * Identify the Origin-to-Final Destination itinerary of the traveler from
   * the list of tickets
   *
   * <p>Example:
   *
   * <pre>
   * Input:
   *   "New York" -> "Dallas"
   *   "Chicago" -> "Houston"
   *   "Las vegas" -> "New York"
   *   "Houston" -> "Las Vegas"
   * Output:
   * Chicago->Houston, Houston->Las Vegas, Las Vegas->New York, New York->Dallas
   * </pre>
   *
   * <p>It may be assumed that the input list of tickets is not cyclic and there
   * is one ticket from every city except the final destination.
   */
  static String printItinerary(String[][] tickets) {
    var graph = new HashMap<String, String>();
    for (String[] ticket : tickets) {
      graph.put(ticket[0], ticket[1]);
      if (!graph.containsKey(ticket[1])) {
        graph.put(ticket[1], "");
      }
    }

    Set<String> destinations = new HashSet<>(graph.values());
    String origin = graph.keySet().stream().filter(not(destinations::contains)).findFirst().orElseThrow();

    StringBuilder result = new StringBuilder();
    while (!origin.isEmpty()) {
      String destination = graph.get(origin);
      if (!destination.isEmpty()) {
        result.append(origin).append("->").append(destination).append(", ");
      }
      origin = destination;
    }

    if (result.charAt(result.length() - 2) == ',') {
      result.delete(result.length() - 2, result.length());
    }

    return result.toString();
  }
}
