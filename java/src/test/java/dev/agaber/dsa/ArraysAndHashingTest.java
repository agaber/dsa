package dev.agaber.dsa;

import static dev.agaber.dsa.ArraysAndHashing.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public final class ArraysAndHashingTest {
  @Test
  public void testContainsDuplicate() throws Exception {
    assertThat(containsDuplicate(new int[]{1, 2, 3, 1})).isTrue();
    assertThat(containsDuplicate(new int[]{1, 2, 3, 4})).isFalse();
    assertThat(containsDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2})).isTrue();
  }

  @Test
  public void testIsAnagram() throws Exception {
    assertThat(isAnagram("anagram", "nagaram")).isTrue();
    assertThat(isAnagram("rat", "car")).isFalse();
  }

  @Test
  public void testTwoSum() throws Exception {
    assertThat(twoSum(new int [] {2, 7, 11, 15}, 9)).containsExactly(0, 1);
    assertThat(twoSum(new int [] {3, 2, 4}, 6)).containsExactly(1, 2);
    assertThat(twoSum(new int [] {3, 3}, 6)).containsExactly(0, 1);
  }

  @Test
  public void testGroupAnagrams() throws Exception {
    assertThat(groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"}))
        .containsExactlyInAnyOrder(
            Arrays.asList("bat"),
            Arrays.asList("tan","nat"),
            Arrays.asList("eat","tea","ate"));
    assertThat(groupAnagrams(new String[] {""})).containsExactlyInAnyOrder(Arrays.asList(""));
    assertThat(groupAnagrams(new String[] {"a"})).containsExactlyInAnyOrder(Arrays.asList("a"));
  }

  @Test
  public void testTopKFrequent() throws Exception {
    assertThat(topKFrequent(new int [] {1,1,1,2,2,3}, 2))
        .containsExactlyInAnyOrder(1, 2);
    assertThat(topKFrequent(new int [] {1}, 1)).containsExactlyInAnyOrder(1);
  }
}
