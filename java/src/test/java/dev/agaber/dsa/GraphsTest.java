package dev.agaber.dsa;

import static dev.agaber.dsa.Graphs.GameOfLife;
import static dev.agaber.dsa.Graphs.NumberOfIslands;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphsTest {
  private NumberOfIslands numberOfIslands;
  private GameOfLife life;

  @BeforeEach
  public void beforeEach() throws Exception {
    life = new GameOfLife();
    numberOfIslands = new NumberOfIslands();
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
}
