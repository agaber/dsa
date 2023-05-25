package dev.agaber.dsa;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import java.util.*;

public final class LinkedLists {
  /**
   * LeetCode's definition for singly-linked list.
   * public class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode() {}
   *     ListNode(int val) { this.val = val; }
   *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   * }
   */
  public static class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
      this.val = val;
    }

    public ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    // Other stuff in this class is for testing.

    @Override
    public boolean equals(Object other) {
      return other instanceof ListNode
          && toList().equals(((ListNode) other).toList());
    }

    @Override
    public int hashCode() {
      return toList().hashCode();
    }

    @Override
    public String toString() {
      return toList().toString().replace(" ", "");
    }

    static ListNode fromList(List<Integer> list) {
      var prehead = new ListNode();
      var node = prehead;
      for (int value : list) {
        var n = new ListNode(value);
        node.next = n;
        node = n;
      }
      return prehead.next;
    }

    static ListNode fromString(String string) {
      var splitter = Splitter.on(',').trimResults().omitEmptyStrings();
      var list = splitter.splitToList(string.replaceAll("\\[|\\]", ""));
      return fromList(list.stream().map(Integer::parseInt).collect(toImmutableList()));
    }

    ImmutableList<Integer> toList() {
      var list = ImmutableList.<Integer>builder();
      list.add(val);
      var node = next;
      while (node != null) {
        list.add(node.val);
        node = node.next;
      }
      return list.build();
    }
  }

  /**
   * Reverse Linked List
   *
   * <p>Given the head of a singly linked list, reverse the list, and return the
   * reversed list.
   *
   * <ul>Constraints:
   *   <li>The number of nodes in the list is the range [0, 5000].
   *   <li>-5000 <= Node.val <= 5000
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li>https://leetcode.com/problems/reverse-linked-list/
   *   <li>Time complexity:
   *   <li>Space complexity:
   * </ul>
   *
   * <p>Follow up: A linked list can be reversed either iteratively or
   * recursively. Could you implement both?
   */
  public static ListNode reverseListI(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode prev = null;
    ListNode node = head;
    while (node != null) {
      var temp = node.next;
      node.next = prev;
      prev = node;
      node = temp;
    }
    return prev;
  }

  public static ListNode reverseListR(ListNode head) {
    return reverseListR(head, null);
  }

  private static ListNode reverseListR(ListNode head, ListNode prev) {
    if (head == null) {
      return null;
    }
    var next = head.next;
    head.next = prev;
    return next == null ? head : reverseListR(next, head);
  }

  /**
   * Merge Two Sorted Lists
   *
   * <p>You are given the heads of two sorted linked lists list1 and list2.
   *
   * <p>Merge the two lists into one sorted list. The list should be made by
   * splicing together the nodes of the first two lists.
   *
   * <p>Return the head of the merged linked list.
   *
   * <ul>Constraints:
   *   <li>The number of nodes in both lists is in the range [0, 50].
   *   <li>-100 <= Node.val <= 100
   *   <li>Both list1 and list2 are sorted in non-decreasing order.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li>https://leetcode.com/problems/merge-two-sorted-lists/
   *   <li>Time complexity: O(n + m)
   *   <li>Space complexity: O(1)
   * </ul>
   *
   * <p>Discussion: Like most things with linked lists, trees and graphs, this
   * can also be done recursively and I think the space complexity would be
   * O(n + m).
   */
  public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    var prehead = new ListNode(0);
    var tail = prehead;
    while (list1 != null && list2 != null) {
      if (list1.val <= list2.val) {
        tail.next = list1;
        tail = list1;
        list1 = list1.next;
      } else {
        tail.next = list2;
        tail = list2;
        list2 = list2.next;
      }
    }
    tail.next = list1 != null ? list1 : list2;
    return prehead.next;
  }
}
