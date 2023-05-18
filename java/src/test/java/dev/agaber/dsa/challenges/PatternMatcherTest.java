package dev.agaber.dsa.challenges;

import static dev.agaber.dsa.challenges.PatternMatcher.isMatch;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public final class PatternMatcherTest {
  @Test
  public void testIsMatchCase1() throws Exception {
    assertThat(isMatch("data3", "datadat")).isTrue();
  }

  @Test
  public void testIsMatchCase2() throws Exception {
    assertThat(isMatch("data3", "dataxyz")).isTrue();
  }

  @Test
  public void testIsMatchCase3() throws Exception {
    assertThat(isMatch("data3xyz1", "datacarxyzZ")).isTrue();
  }

  @Test
  public void testIsMatchCase4() throws Exception {
    assertThat(isMatch("data3xyz1", "datacarxyz")).isFalse();
  }

  @Test
  public void testIsMatchCase5() throws Exception {
    assertThat(isMatch("data3xyz", "datacarxyzZ")).isFalse();
  }

  @Test
  public void testIsMatchCase6() throws Exception {
    assertThat(isMatch("data3", "datacar")).isTrue();
  }

  @Test
  public void testIsMatchCase7() throws Exception {
    assertThat(isMatch("3data3", "   datacar")).isTrue();
  }

  @Test
  public void testIsMatchCase8() throws Exception {
    assertThat(isMatch("data3", "datacar")).isTrue();
  }

  @Test
  public void testIsMatchCase9() throws Exception {
    assertThat(isMatch("3data3", "   datacar")).isTrue();
  }

  @Test
  public void testIsMatchCase10() throws Exception {
    assertThat(isMatch("2data3", "   datacar")).isFalse();
  }

  @Test
  public void testIsMatchWithTwoDigitNumber() throws Exception {
    assertThat(isMatch("data10cool", "datanumber1010cool")).isTrue();
    assertThat(isMatch("data10", "datanumber")).isFalse();
  }
}
