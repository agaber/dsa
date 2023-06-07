package dev.agaber.dsa;

import static dev.agaber.dsa.MathAndGeometry.SpiralMatrix;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class MathAndGeometryTest {
  private SpiralMatrix sm;

  @BeforeEach
  void beforeEach() {
    sm = new SpiralMatrix();
  }

  @Test
  void spiralMatrixTestCase1() {
    int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    assertThat(sm.spiralOrder(matrix))
        .containsExactly(1, 2, 3, 6, 9, 8, 7, 4, 5);
  }

  @Test
  void spiralMatrixTestCase2() {
    int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
    assertThat(sm.spiralOrder(matrix))
        .containsExactly(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
  }
}
