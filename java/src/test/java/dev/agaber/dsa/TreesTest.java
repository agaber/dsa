package dev.agaber.dsa;

import static dev.agaber.dsa.Trees.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

  @Test
  public void isSubtreeTestCase1() {
    assertThat(isSubtree(createTree("[3,4,5,1,2]"), createTree("[4,1,2]"))).isTrue();
  }

  @Test
  public void isSubtreeTestCase2() {
    var root = createTree("[3,4,5,1,2,null,null,null,null,0]");
    var subroot = createTree("[4,1,2]");
    assertThat(isSubtree(root, subroot)).isFalse();
  }

  @Test
  public void isSubtreeTestCase3() {
    assertThat(isSubtree(createTree("[1,1]"), createTree("[1]"))).isTrue();
  }

  @Test
  public void isSubtreeTestCase4() {
    var root = createTree(
        "[1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,null,1,2]");
    var subroot = createTree(
        "[1,null,1,null,1,null,1,null,1,null,1,2]");
    assertThat(isSubtree(root, subroot)).isTrue();
  }

  @Test
  public void isSubtreeTestCase5() {
    var root = createTree("[-1,-4,8,-6,-2,3,9,null,-5,null,null,0,7]");
    var subroot = createTree("[3,0,5848]");
    assertThat(isSubtree(root, subroot)).isFalse();
  }

  @Test
  public void lowestCommonAncestorTestCase1() {
    var root = createTree("[6,2,8,0,4,7,9,null,null,3,5]");
    assertThat(lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8)).val)
        .isEqualTo(6);
  }

  @Test
  public void lowestCommonAncestorTestCase2() {
    var root = createTree("[6,2,8,0,4,7,9,null,null,3,5]");
    assertThat(lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8)).val)
        .isEqualTo(6);
  }

  @Test
  public void lowestCommonAncestorTestCase3() {
    var root = createTree("[2,1]");
    assertThat(lowestCommonAncestor(root, new TreeNode(2), new TreeNode(1)).val)
        .isEqualTo(2);
  }

  @Test
  public void testLevelOrder() {
    assertThat(levelOrder(createTree("[3,9,20,null,null,15,7]")))
        .containsExactly(
            Arrays.asList(3),
            Arrays.asList(9, 20),
            Arrays.asList(15, 7));
    assertThat(levelOrder(createTree("[1]")))
        .containsExactly(Arrays.asList(1));
    assertThat(levelOrder(createTree("[]"))).isEmpty();
  }

  @Test
  public void testIsValidBST() {
    assertThat(isValidBST(createTree("[2,1,3]"))).isTrue();
    assertThat(isValidBST(createTree("[5,1,4,null,null,3,6]"))).isFalse();
    assertThat(isValidBST(createTree("[2,2,2]"))).isFalse();
    assertThat(isValidBST(createTree("[1,null,1]"))).isFalse();
    assertThat(isValidBST(createTree("[5,4,6,null,null,3,7]"))).isFalse();
  }
}
