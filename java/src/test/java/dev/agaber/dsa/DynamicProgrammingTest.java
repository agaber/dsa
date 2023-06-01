package dev.agaber.dsa;

import static org.assertj.core.api.Assertions.assertThat;
import static dev.agaber.dsa.DynamicProgramming.*;

import org.junit.jupiter.api.Test;

public class DynamicProgrammingTest {
  @Test
  public void fibonacciTabulation() {
    long[] expected = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377,};
    for (int i = 0; i < expected.length; i++) {
      assertThat(fibonacci(i)).isEqualTo(expected[i]);
    }
  }

  @Test
  public void fibonacciTabulationBigNumbers() {
    assertThat(fibonacci(50)).isEqualTo(12586269025L);
  }
}
