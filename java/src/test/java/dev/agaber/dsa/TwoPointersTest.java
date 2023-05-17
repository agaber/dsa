package dev.agaber.dsa;

import static dev.agaber.dsa.TwoPointers.*;
import static org.assertj.core.api.Assertions.assertThat;

import dev.agaber.dsa.TwoPointers.*;
import org.junit.jupiter.api.Test;

public final class TwoPointersTest {
  @Test
  public void testTwoSumII() throws Exception {
    assertThat(twoSumII(new int [] {2, 7, 11, 15}, 9)).isEqualTo(new int[] {1, 2});
    assertThat(twoSumII(new int [] {2, 3, 4}, 6)).isEqualTo(new int[] {1, 3});
    assertThat(twoSumII(new int [] {-1, 0}, -1)).isEqualTo(new int[] {1, 2});
  }
}
