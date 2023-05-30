package dev.agaber.dsa;

import static com.google.common.collect.ImmutableList.toImmutableList;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import java.util.*;

final class LinkedLists {
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
  static class ListNode {
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
      var list = splitter.splitToList(string.replaceAll("\\[|]", ""));
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
   *   <li><a href="https://leetcode.com/problems/reverse-linked-list/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(1) (iterative)
   * </ul>
   *
   * <p>Follow up: A linked list can be reversed either iteratively or
   * recursively. Could you implement both?
   */
  static ListNode reverseListI(ListNode head) {
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

  static ListNode reverseListR(ListNode head) {
    return reverseListR(head, null);
  }

  static ListNode reverseListR(ListNode head, ListNode prev) {
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
   *   <li><a href="https://leetcode.com/problems/merge-two-sorted-lists/">LeetCode</a>
   *   <li>Time complexity: O(n + m)
   *   <li>Space complexity: O(1)
   * </ul>
   *
   * <p>Discussion: Like most things with linked lists, trees and graphs, this
   * can also be done recursively and I think the space complexity would be
   * O(n + m).
   */
  static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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

  /**
   * Reorder List
   *
   * <p>You are given the head of a singly linked-list. The list can be
   * represented as:
   *
   * <pre>L0 → L1 → … → Ln - 1 → Ln</pre>
   *
   * <p>Reorder the list to be on the following form:
   *
   * <pre>L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → ...</pre>
   *
   * <p>You may not modify the values in the list's nodes. Only nodes themselves
   * may be changed.
   *
   * <ul>Constraints:
   *   <li>The number of nodes in the list is in the range [1, 5 * 104].
   *   <li>1 <= Node.val <= 1000
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li><a href="https://leetcode.com/problems/reorder-list/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(1)
   * </ul>
   *
   * <p>Discussion: This solution combines a few different list techniques:
   * <ul>
   * <li>fast/slow technique to find the middle of a list
   * <li>reverse a list
   * <li>doing annoying pointer stuff - Figuring out this final bit of logic
   *     is really hard IMO.
   * </ul>
   */
  static final class ReorderList {
    public void reorderList(ListNode head) {
      if (head == null) {
        return;
      }
      ListNode first = head;
      ListNode second = reverse(mid(head));
      while (first != null && second != null) {
        var firstNext = first.next;
        var secondNext = second.next;
        first.next = second;
        second.next = firstNext;
        first = firstNext;
        second = secondNext;
      }
    }

    private ListNode mid(ListNode head) {
      var slow = head;
      var fast = head.next;
      while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow;
    }

    private ListNode reverse(ListNode head) {
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
  }

  /**
   * Remove Nth Node From End of List
   *
   * <p>Given the head of a linked list, remove the nth node from the end of the
   * list and return its head.
   *
   * <ul>Constraints:
   *   <li>The number of nodes in the list is sz.
   *   <li>1 <= sz <= 30
   *   <li>0 <= Node.val <= 100
   *   <li>1 <= n <= sz
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li><a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(1)
   * </ul>
   *
   * <p>Discussion: This is a one pass solution and probably the best one can
   * reasonably do with this problem. There is a two pass solution that involves
   * making one pass through to count the size of the list and a second pass to
   * update the list pointers. That solution is far more intuitive to me but
   * this actually isn't so bad but I have to think about it longer.
   */
  static ListNode removeNthFromEnd(ListNode head, int n) {
    var prehead = new ListNode(0, head);
    var first = prehead;
    var last = prehead;
    int size = 0;
    while (last != null) {
      if (size++ > n) {
        first = first.next;
      }
      last = last.next;
    }
    first.next = first.next.next;
    return prehead.next;
  }

  /**
   * Linked List Cycle
   *
   * <p>Given head, the head of a linked list, determine if the linked list has
   * a cycle in it.
   *
   * <p>There is a cycle in a linked list if there is some node in the list that
   * can be reached again by continuously following the next pointer. Internally,
   * pos is used to denote the index of the node that tail's next pointer is
   * connected to. Note that pos is not passed as a parameter.
   *
   * <p>Return true if there is a cycle in the linked list. Otherwise, return
   * false.
   *
   * <ul>Constraints:
   *   <li>The number of the nodes in the list is in the range [0, 10^4].
   *   <li>-10^5 <= Node.val <= 10^5
   *   <li>pos is -1 or a valid index in the linked-list.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li><a href="https://leetcode.com/problems/linked-list-cycle/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(1)
   * </ul>
   *
   * <p>Discussion: The more common sense solution, IMO, is to use a HashMap and
   * be careful about comparing the equality of nodes when doing that btw, but
   * Floyd's Tortoise and Hare algorithm allows us to do it in O(1) space
   * complexity and that is what is used here. There is a mathematical proof for
   * this solution somewhere btw.
   */
  static boolean hasCycle(ListNode head) {
    var slow = head;
    var fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }

  /**
   * Merge k Sorted Lists
   *
   * <p>You are given an array of k linked-lists lists, each linked-list is
   * sorted in ascending order.
   *
   * <p>Merge all the linked-lists into one sorted linked-list and return it.
   *
   * <ul>Constraints:
   *   <li>k == lists.length
   *  <li>0 <= k <= 10^4
   *  <li>0 <= lists[i].length <= 500
   *  <li>-10^4 <= lists[i][j] <= 10^4
   *  <li>lists[i] is sorted in ascending order.
   *  <li>The sum of lists[i].length will not exceed 10^4.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Hard
   *   <li><a href="https://leetcode.com/problems/merge-k-sorted-lists/">LeetCode</a>
   *   <li>Time complexity: O(nlogk) where n is the max number of elements in a
   *       list and k is the number of lists
   *   <li>Space complexity: O(1)
   * </ul>
   *
   * <p>Discussion: There are a few valid options to solve this. I believe this
   * is the optimal solution but I've also included other solutions that are
   * interesting after this.
   */
  static ListNode mergeKLists(ListNode[] lists) {
    List<ListNode> nodes = Arrays.stream(lists).filter(Objects::nonNull).toList();
    while (nodes.size() > 1) {
      List<ListNode> merged = new ArrayList<>();
      for (int i = 0; i < nodes.size(); i += 2) {
        var l1 = nodes.get(i);
        var l2 = i + 1 < nodes.size() ? nodes.get(i + 1) : null;
        merged.add(mergeTwoLists(l1, l2));
      }
      nodes = merged;
    }
    return nodes.isEmpty() ? null : nodes.get(0);
  }

  /**
   * Merges an arbitrary amount of sorted lists using a priority queue.
   *
   * <p>I think the time complexity is also O(n log k).
   */
  static ListNode mergeKListsWithHeap(ListNode[] lists) {
    var pq = new PriorityQueue<ListNode>(Comparator.comparingInt(x -> x.val));
    Arrays.stream(lists).filter(Objects::nonNull).forEach(pq::offer);

    var prehead = new ListNode(0);
    var current = prehead;

    while (!pq.isEmpty()) {
      var next = pq.poll();
      current.next = next;
      current = next;
      if (next.next != null) {
        pq.add(next.next);
      }
    }

    return prehead.next;
  }

  /**
   * Merges an arbitrary amount of sorted lists.
   *
   * <p>I think the time complexity is O(nk). Space complexity is a legit O(1)
   * and it shows in LeetCode's benchmarks, but the first list keeps growing and
   * thus the merge is a bit slower each time. However, this seems simplest to
   * remember.
   */
  static ListNode mergeKListsNaive(ListNode[] lists) {
    ListNode merged = lists.length > 0 ? lists[0] : null;
    for (int i = 1; i < lists.length; i++) {
      merged = mergeTwoLists(merged, lists[i]);
    }
    return merged;
  }
}
