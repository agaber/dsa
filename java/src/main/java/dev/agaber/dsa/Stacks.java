package dev.agaber.dsa;

import com.google.common.collect.ImmutableMap;

import java.util.*;

final class Stacks {
  /**
   * Valid Parentheses
   *
   * <p>Given a string s containing just the characters
   * '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
   *
   * <p>An input string is valid if:
   * <ul>
   *   <li>Open brackets must be closed by the same type of brackets.
   *   <li>Open brackets must be closed in the correct order.
   *   <li>Every close bracket has a corresponding open bracket of the same type.
   * </ul>
   *
   * <ul>Constraints:
   *   <li>1 <= s.length <= 104
   *   <li>s consists of parentheses only '()[]{}'.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li>https://leetcode.com/problems/valid-parentheses/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   */
  static final class ValidParenthesis {
    // Doing this with a switch/if conditional will perform better.
    private static final ImmutableMap<Character, Character> MATCHES =
        ImmutableMap.<Character, Character>builder()
            .put(')', '(')
            .put('}', '{')
            .put(']', '[')
            .build();

    public boolean isValid(String s) {
      var stack = new Stack<Character>();
      for (char c : s.toCharArray()) {
        if (MATCHES.containsKey(c)) {
          if (stack.isEmpty() || stack.pop() != MATCHES.get(c)) {
            return false;
          }
        } else {
          stack.push(c);
        }
      }
      return stack.isEmpty();
    }
  }

  private Stacks() {}
}
