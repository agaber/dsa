package dev.agaber.dsa;

import static dev.agaber.dsa.TwoPointers.ThreeSum;
import static dev.agaber.dsa.TwoPointers.isPalindrome;
import static dev.agaber.dsa.TwoPointers.maxArea;
import static dev.agaber.dsa.TwoPointers.twoSumII;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public final class TwoPointersTest {
  private ThreeSum ts;

  @BeforeEach
  public void beforeEach() throws Exception {
    ts = new ThreeSum();
  }

  @Test
  public void testTwoSumII() throws Exception {
    assertThat(twoSumII(new int[]{2, 7, 11, 15}, 9)).isEqualTo(new int[]{1, 2});
    assertThat(twoSumII(new int[]{2, 3, 4}, 6)).isEqualTo(new int[]{1, 3});
    assertThat(twoSumII(new int[]{-1, 0}, -1)).isEqualTo(new int[]{1, 2});
  }

  @Test
  public void testIsPalindrome() throws Exception {
    assertThat(isPalindrome("A man, a plan, a canal: Panama")).isTrue();
    assertThat(isPalindrome("race a car")).isFalse();
    assertThat(isPalindrome(" ")).isTrue();
  }

  @Test
  public void threeSumTestCase1() throws Exception {
    assertThat(ts.threeSum(new int[]{-1, 0, 1, 2, -1, -4}))
        .containsExactly(
            Arrays.asList(-1, -1, 2),
            Arrays.asList(-1, 0, 1));
  }

  @Test
  public void threeSumTestCase2() throws Exception {
    assertThat(ts.threeSum(new int[]{0, 1, 1})).isEmpty();
  }

  @Test
  public void threeSumTestCase3() throws Exception {
    assertThat(ts.threeSum(new int[]{0, 0, 0})).containsExactly(Arrays.asList(0, 0, 0));
  }

  @Test
  public void testMaxArea() throws Exception {
    assertThat(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7})).isEqualTo(49);
    assertThat(maxArea(new int[]{1, 1})).isEqualTo(1);
  }
}
