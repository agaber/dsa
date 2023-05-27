package dev.agaber.dsa;

import static dev.agaber.dsa.Trees.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public final class TreesTest {
  @Test
  public void createTreeFromArray() {
    var root = createTree(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5);
    assertThat(inOrderTraversal(root)).containsExactly(0, 2, 3, 4, 5, 6, 7, 8, 9);
  }

  @Test
  public void createTreeFromString() {
    assertThat(inOrderTraversal(createTree("[6,2,8,0,4,7,9,null,null,3,5]")))
        .containsExactly(0, 2, 3, 4, 5, 6, 7, 8, 9);
    assertThat(inOrderTraversal(createTree("[4,2,7,1,3,6,9]"))).containsExactly(1, 2, 3, 4, 6, 7, 9);
  }

  @Test
  public void testTreeToString() {
    // assertThat(treeToString(createTree("[4,2,7,1,3,6,9]"))).isEqualTo("[4,2,7,1,3,6,9]");
    assertThat(treeToString(createTree("[6,2,8,0,4,7,9,null,null,3,5]")))
        .isEqualTo("[6,2,8,0,4,7,9,null,null,3,5]");
  }

  @Test
  public void testInvertTree() {
    assertThat(invertTree(createTree("[4,2,7,1,3,6,9]"))).isEqualTo(createTree("[4,7,2,9,6,3,1]"));
    assertThat(invertTree(createTree("[2,1,3]"))).isEqualTo(createTree("[2,3,1]"));
    assertThat(invertTree(createTree("[]"))).isEqualTo(createTree("[]"));
  }

  @Test
  public void testMaxDepth() {
    assertThat(maxDepth(createTree("[3,9,20,null,null,15,7]"))).isEqualTo(3);
    assertThat(maxDepth(createTree("[1,null,2]"))).isEqualTo(2);
  }

  @Test
  public void testIsSameTree() {
    assertThat(isSameTree(createTree("[1,2,3]"), createTree("[1,2,3]"))).isTrue();
    assertThat(isSameTree(createTree("[1,2]"), createTree("[1,null,2]"))).isFalse();
    assertThat(isSameTree(createTree("[1,2,1]"), createTree("[1,1,2]"))).isFalse();
  }
}
