package dev.agaber.dsa;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import java.util.*;
import static dev.agaber.dsa.LinkedLists.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LinkedListsTest {
  @Test
  public void listNodeFromToList() throws Exception {
    var list = ImmutableList.of(1, 2, 3, 4, 5, 6, 7);
    ListNode node = ListNode.fromList(list);
    assertThat(node.toList()).containsExactlyElementsOf(list);
  }

  @Test
  public void listNodeToString() throws Exception {
    var list = ImmutableList.of(1, 2, 3, 4, 5, 6, 7);
    ListNode node = ListNode.fromList(list);
    assertThat(node.toString()).isEqualTo("[1,2,3,4,5,6,7]");
  }

  @Test
  public void listNodeFromString() throws Exception {
    assertThat(ListNode.fromString("[1, 2, 3, 4, 5, 6, 7]").toString())
        .isEqualTo("[1,2,3,4,5,6,7]");

  }

  @Test
  public void emptyListNodeToFromString() throws Exception {
    assertThat(ListNode.fromList(ImmutableList.of())).isNull();
    assertThat(ListNode.fromString("[]")).isNull();
  }

  @Test
  public void reverseListIterative() throws Exception {
    assertThat(reverseListI(ListNode.fromString("[1,2,3,4,5]")))
        .isEqualTo(ListNode.fromString("[5,4,3,2,1]"));
    assertThat(reverseListI(ListNode.fromString("[1,2]")))
        .isEqualTo(ListNode.fromString("[2,1]"));
    assertThat(reverseListI(ListNode.fromString("[]")))
        .isEqualTo(ListNode.fromString("[]"));
  }

  @Test
  public void reverseListRecursive() throws Exception {
    assertThat(reverseListR(ListNode.fromString("[1,2,3,4,5]")))
        .isEqualTo(ListNode.fromString("[5,4,3,2,1]"));
    assertThat(reverseListR(ListNode.fromString("[1,2]")))
        .isEqualTo(ListNode.fromString("[2,1]"));
    assertThat(reverseListR(ListNode.fromString("[]")))
        .isEqualTo(ListNode.fromString("[]"));
  }
}
