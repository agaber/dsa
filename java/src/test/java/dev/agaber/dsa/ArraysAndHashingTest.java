package dev.agaber.dsa;

import static dev.agaber.dsa.ArraysAndHashing.Codec;
import static dev.agaber.dsa.ArraysAndHashing.containsDuplicate;
import static dev.agaber.dsa.ArraysAndHashing.groupAnagrams;
import static dev.agaber.dsa.ArraysAndHashing.isAnagram;
import static dev.agaber.dsa.ArraysAndHashing.longestConsecutive;
import static dev.agaber.dsa.ArraysAndHashing.productExceptSelf;
import static dev.agaber.dsa.ArraysAndHashing.topKFrequent;
import static dev.agaber.dsa.ArraysAndHashing.twoSum;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
    assertThat(twoSum(new int[]{2, 7, 11, 15}, 9)).containsExactly(0, 1);
    assertThat(twoSum(new int[]{3, 2, 4}, 6)).containsExactly(1, 2);
    assertThat(twoSum(new int[]{3, 3}, 6)).containsExactly(0, 1);
  }

  @Test
  public void testGroupAnagrams() throws Exception {
    assertThat(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}))
        .containsExactlyInAnyOrder(
            List.of("bat"),
            Arrays.asList("tan", "nat"),
            Arrays.asList("eat", "tea", "ate"));
    assertThat(groupAnagrams(new String[]{""})).containsExactlyInAnyOrder(List.of(""));
    assertThat(groupAnagrams(new String[]{"a"})).containsExactlyInAnyOrder(List.of("a"));
  }

  @Test
  public void testTopKFrequent() throws Exception {
    assertThat(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2))
        .containsExactlyInAnyOrder(1, 2);
    assertThat(topKFrequent(new int[]{1}, 1)).containsExactlyInAnyOrder(1);
  }

  @Test
  public void testProductOfArrayExceptSelf() {
    assertThat(productExceptSelf(new int[]{1, 2, 3, 4})).isEqualTo(new int[]{24, 12, 8, 6});
    assertThat(productExceptSelf(new int[]{-1, 1, 0, -3, 3})).isEqualTo(new int[]{0, 0, 9, 0, 0});
  }

  @Test
  public void testLongestConsecutive() throws Exception {
    assertThat(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2})).isEqualTo(4);
    assertThat(longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})).isEqualTo(9);
  }

  @Test
  public void codecTestCase1() throws Exception {
    Codec codec = new Codec();
    assertThat(codec.decode(codec.encode(Arrays.asList("Hello", "World"))))
        .containsExactly("Hello", "World");
  }

  @Test
  public void codecTestCase2() throws Exception {
    Codec codec = new Codec();
    assertThat(codec.decode(codec.encode(List.of(""))))
        .containsExactly("");
  }
}