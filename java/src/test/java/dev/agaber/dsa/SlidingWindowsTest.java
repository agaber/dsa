package dev.agaber.dsa;

import static dev.agaber.dsa.SlidingWindows.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public final class SlidingWindowsTest {
  @Test
  public void slidingWindow() throws Exception {
    assertThat(maxProfit(new int[]{7, 1, 5, 3, 6, 4})).isEqualTo(5);
    assertThat(maxProfit(new int[]{7, 6, 4, 3, 1})).isEqualTo(0);
  }

  @Test
  public void testLengthOfLongestSubstring() throws Exception {
    assertThat(lengthOfLongestSubstring("abcabcbb")).isEqualTo(3);
    assertThat(lengthOfLongestSubstring("bbbbb")).isEqualTo(1);
    assertThat(lengthOfLongestSubstring("pwwkew")).isEqualTo(3);
    assertThat(lengthOfLongestSubstring(" ")).isEqualTo(1);
  }

  @Test
  public void testCharacterReplacement() throws Exception {
    assertThat(characterReplacement("ABAB", 2)).isEqualTo(4);
    assertThat(characterReplacement("AABABBA", 1)).isEqualTo(4);
  }

  @Test
  public void minWindowTestCase1() throws Exception {
    assertThat(minWindow("ADOBECODEBANC", "ABC")).isEqualTo("BANC");
  }

  @Test
  public void minWindowTestCase2() throws Exception {
    assertThat(minWindow("a", "a")).isEqualTo("a");
  }

  @Test
  public void minWindowTestCase3() throws Exception {
    assertThat(minWindow("a", "aa")).isEqualTo("");
  }

  @Test
  public void minWindowTestCase4() throws Exception {
    assertThat(minWindow("ADOBECODEBANCDDD", "ABC")).isEqualTo("BANC");
  }

  @Test
  public void minWindowTestCase5() throws Exception {
    assertThat(minWindow("KADOBECODEBANCDDDEI", "ABCEKI")).isEqualTo("KADOBECODEBANCDDDEI");
  }

  @Test
  public void minWindowTestCase6() throws Exception {
    assertThat(minWindow("aa", "aa")).isEqualTo("aa");
  }
}
