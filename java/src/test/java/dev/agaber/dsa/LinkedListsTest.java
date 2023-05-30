package dev.agaber.dsa;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import java.util.*;
import static dev.agaber.dsa.LinkedLists.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LinkedListsTest {
  @Test
  public void listNodeFromToList() {
    var list = ImmutableList.of(1, 2, 3, 4, 5, 6, 7);
    ListNode node = ListNode.fromList(list);
    assertThat(node.toList()).containsExactlyElementsOf(list);
  }

  @Test
  public void listNodeToString() {
    var list = ImmutableList.of(1, 2, 3, 4, 5, 6, 7);
    ListNode node = ListNode.fromList(list);
    assertThat(node.toString()).isEqualTo("[1,2,3,4,5,6,7]");
  }

  @Test
  public void listNodeFromString() {
    assertThat(ListNode.fromString("[1, 2, 3, 4, 5, 6, 7]").toString())
        .isEqualTo("[1,2,3,4,5,6,7]");

  }

  @Test
  public void emptyListNodeToFromString() {
    assertThat(ListNode.fromList(ImmutableList.of())).isNull();
    assertThat(ListNode.fromString("[]")).isNull();
  }

  @Test
  public void reverseListIterative() {
    assertThat(reverseListI(ListNode.fromString("[1,2,3,4,5]")))
        .isEqualTo(ListNode.fromString("[5,4,3,2,1]"));
    assertThat(reverseListI(ListNode.fromString("[1,2]")))
        .isEqualTo(ListNode.fromString("[2,1]"));
    assertThat(reverseListI(ListNode.fromString("[]")))
        .isNull();
  }

  @Test
  public void reverseListRecursive() {
    assertThat(reverseListR(ListNode.fromString("[1,2,3,4,5]")))
        .isEqualTo(ListNode.fromString("[5,4,3,2,1]"));
    assertThat(reverseListR(ListNode.fromString("[1,2]")))
        .isEqualTo(ListNode.fromString("[2,1]"));
    assertThat(reverseListR(ListNode.fromString("[]")))
        .isNull();
  }

  @Test
  public void testMergeTwoLists() {
    assertThat(mergeTwoLists(ListNode.fromString("[1,2,4]"), ListNode.fromString("[1,3,4]")))
        .isEqualTo(ListNode.fromString("[1,1,2,3,4,4]"));
    assertThat(mergeTwoLists(ListNode.fromString("[]"), ListNode.fromString("[]")))
        .isEqualTo(ListNode.fromString("[]"));
    assertThat(mergeTwoLists(ListNode.fromString("[]"), ListNode.fromString("[0]")))
        .isEqualTo(ListNode.fromString("[0]"));
  }

  @Test
  public void reorderListsTestCase1() {
    ReorderList rl = new ReorderList();
    ListNode list = ListNode.fromString("[1,2,3,4]");
    rl.reorderList(list);
    assertThat(list).isEqualTo(ListNode.fromString("[1,4,2,3]"));
  }

  @Test
  public void reorderListsTestCase2() {
    ReorderList rl = new ReorderList();
    ListNode list = ListNode.fromString("[1,2,3,4,5]");
    rl.reorderList(list);
    assertThat(list).isEqualTo(ListNode.fromString("[1,5,2,4,3]"));
  }

  @Test
  public void testRemoveNthNode() {
    assertThat(removeNthFromEnd(ListNode.fromString("[1,2,3,4,5]"), 2))
        .isEqualTo(ListNode.fromString("[1,2,3,5]"));
    assertThat(removeNthFromEnd(ListNode.fromString("[1]"), 1))
        .isEqualTo(ListNode.fromString("[]"));
    assertThat(removeNthFromEnd(ListNode.fromString("[1,2]"), 1))
        .isEqualTo(ListNode.fromString("[1]"));
    assertThat(removeNthFromEnd(ListNode.fromString("[1,2]"), 2))
        .isEqualTo(ListNode.fromString("[2]"));
    assertThat(removeNthFromEnd(ListNode.fromString("[1,2,3]"), 1))
        .isEqualTo(ListNode.fromString("[1,2]"));
  }

  @Test
  public void hasCycleTestCase1() {
    ListNode head = ListNode.fromString("[3,2,0,-4]");
    createCycle(head, 4, 1);
    assertThat(hasCycle(head)).isTrue();
  }

  @Test
  public void hasCycleTestCase2() {
    ListNode head = ListNode.fromString("[1,2]");
    createCycle(head, 2, 0);
    assertThat(hasCycle(head)).isTrue();
  }

  @Test
  public void hasCycleTestCase3() {
    ListNode head = ListNode.fromString("[1]");
    assertThat(hasCycle(head)).isFalse();
  }

  private static void createCycle(ListNode head, int size, int index) {
    ListNode e1 = null;
    ListNode e4 = null;
    ListNode node = new ListNode(0, head);
    for (int i = 0; i < size; i++) {
      node = node.next;
      if (i == index) {
        e1 = node;
      } else if (i == size - 1) {
        e4 = node;
      }
    }
    if (e4 != null) {
      e4.next = e1;
    }
  }
}
