package dev.agaber.dsa

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ArraysKtTest {

  @BeforeEach
  fun setUp() {
  }

  @Test
  fun testContainsDuplicate() {
    assertTrue(containsDuplicate(intArrayOf(1, 2, 3, 1)))
    assertFalse(containsDuplicate(intArrayOf(1, 2, 3, 4)))
    assertTrue(containsDuplicate(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2)))
  }

  @Test
  fun testIsAnagram() {
    assertTrue(isAnagram("anagram", "nagaram"))
    assertFalse(isAnagram("rat", "car"))
  }

  @Test
  fun testTwoSum() {
    assertArrayEquals(twoSum(intArrayOf(2, 7, 11, 15), 9), intArrayOf(0, 1))
    assertArrayEquals(twoSum(intArrayOf(3, 2, 4), 6), intArrayOf(1, 2))
    assertArrayEquals(twoSum(intArrayOf(3, 3), 6), intArrayOf(0, 1))
  }

  @Test
  fun testGroupAnagrams() {
    assertEquals(
      groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")),
      arrayListOf(
        arrayListOf("eat", "tea", "ate"),
        arrayListOf("tan", "nat"),
        arrayListOf("bat")))
    assertEquals(groupAnagrams(arrayOf("")), arrayListOf(arrayListOf("")))
    assertEquals(groupAnagrams(arrayOf("a")), arrayListOf(arrayListOf("a")))
  }

  @Test
  fun testTopKFrequent() {
    assertArrayEquals(topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2), intArrayOf(1, 2))
    assertArrayEquals(topKFrequent(intArrayOf(1), 1), intArrayOf(1))
  }
}