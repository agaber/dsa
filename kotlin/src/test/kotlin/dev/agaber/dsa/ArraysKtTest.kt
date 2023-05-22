package dev.agaber.dsa

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
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
}