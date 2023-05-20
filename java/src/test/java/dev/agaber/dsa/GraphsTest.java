package dev.agaber.dsa;

import static dev.agaber.dsa.Graphs.GameOfLife;
import static dev.agaber.dsa.Graphs.NumberOfIslands;
import static dev.agaber.dsa.Graphs.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class GraphsTest {
  private GameOfLife life;
  private NumberOfIslands numberOfIslands;
  private Graphs.PacificAtanticWaterFlow pa;

  @BeforeEach
  public void beforeEach() throws Exception {
    life = new GameOfLife();
    numberOfIslands = new NumberOfIslands();
    pa = new Graphs.PacificAtanticWaterFlow();
  }

  @Test
  public void numberOfIslandsTestCase1() throws Exception {
    char[][] grid = new char[][]{
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'},
    };
    assertThat(numberOfIslands.numIslands(grid)).isEqualTo(1);
  }

  @Test
  public void numberOfIslandsTestCase2() throws Exception {
    char[][] grid = new char[][]{
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'},
    };
    assertThat(numberOfIslands.numIslands(grid)).isEqualTo(3);
  }

  @Test
  public void gameOfLifeTestCase1() throws Exception {
    int[][] board = new int[][]{
        {0, 1, 0},
        {0, 0, 1},
        {1, 1, 1},
        {0, 0, 0},
    };
    int[][] expected = new int[][]{
        {0, 0, 0},
        {1, 0, 1},
        {0, 1, 1},
        {0, 1, 0},
    };
    life.gameOfLife(board);
    assertThat(board).isEqualTo(expected);
  }

  @Test
  public void gameOfLifeTestCase2() throws Exception {
    int[][] board = new int[][]{
        {1, 1},
        {1, 0},
    };
    int[][] expected = new int[][]{
        {1, 1},
        {1, 1},
    };
    life.gameOfLife(board);
    assertThat(board).isEqualTo(expected);
  }

  @Test
  public void pacificAtlanticTestCase1() throws Exception {
    int[][] heights = new int[][]{
        {1, 2, 2, 3, 5},
        {3, 2, 3, 4, 4},
        {2, 4, 5, 3, 1},
        {6, 7, 1, 4, 5},
        {5, 1, 1, 2, 4},
    };
    List<List<Integer>> expected = Arrays.asList(
        Arrays.asList(4, 0),
        Arrays.asList(3, 0),
        Arrays.asList(0, 4),
        Arrays.asList(3, 1),
        Arrays.asList(1, 3),
        Arrays.asList(2, 2),
        Arrays.asList(1, 4));
    assertThat(pa.pacificAtlantic(heights)).containsExactlyInAnyOrderElementsOf(expected);
  }

  @Test
  public void pacificAtlanticTestCase2() throws Exception {
    int[][] heights = new int[][]{{1}};
    List<List<Integer>> expected = Arrays.asList(Arrays.asList(0, 0));
    assertThat(pa.pacificAtlantic(heights)).isEqualTo(expected);
  }

  @Test
  public void testPrintItinerary() throws Exception {
    var tickets = new String[][] {
        {"New York", "Dallas"},
        {"Chicago", "Houston"},
        {"Las Vegas", "New York"},
        {"Houston", "Las Vegas"},
    };
    assertThat(printItinerary(tickets))
        .isEqualTo("Chicago->Houston, Houston->Las Vegas, Las Vegas->New York, New York->Dallas");
  }
}
