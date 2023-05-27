package dev.agaber.dsa;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;

import java.util.*;

final class Trees {
  /**
   * LeetCode's definition for a binary tree node.
   * public class TreeNode {
   *     int val;
   *     TreeNode left;
   *     TreeNode right;
   *     TreeNode() {}
   *     TreeNode(int val) { this.val = val; }
   *     TreeNode(int val, TreeNode left, TreeNode right) {
   *         this.val = val;
   *         this.left = left;
   *         this.right = right;
   *     }
   * }
   */
  public static final class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
      this(0, null,  null);
    }

    TreeNode(int val) {
      this(val, null, null);
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    /**
     * Returns true if the tree starting at this root is logically equivalent to
     * another root node's subtree. This is needed for unit testing.
     */
    @Override
    public boolean equals(Object o) {
      if (o == null) {
        return false;
      }
      // Note: It is also assigning TreeNode other here.
      // This is called "pattern matching for instanceof" and was introduced in
      // Java 14. See https://tinyurl.com/49583wya.
      if (!(o instanceof TreeNode other)) {
        return false;
      }
      if (val != other.val) {
        return false;
      }
      if (left != null && !left.equals(other.left)) {
        return false;
      }
      if (right != null && !right.equals(other.right)) {
        return false;
      }
      return true;
    }

    /**
     * Returns the hashcode value for a tree that has this node as its root.
     * I don't need this but you're supposed to do it if you override equals.
     */
    @Override
    public int hashCode() {
      int hash = 31 * val;
      if (left != null) {
        hash *= 7 * left.hashCode();
      }
      if (right != null) {
        hash *= 7 * right.hashCode();
      }
      return hash;
    }

    /** See treeToString(TreeNode). */
    @Override
    public String toString() {
      return treeToString(this);
    }
  }

  /**
   * Creates a tree from a string so we can easily replicate LeetCode test
   * cases. LeetCode test cases like to represent a tree as an array constructed
   * from a breath first traversal.
   */
  static TreeNode createTree(String string) {
    var splitter = Splitter.on(',').omitEmptyStrings().trimResults();
    var nums = splitter.splitToStream(string.replaceAll("\\[|]", ""))
        .map(n -> n.equals("null") ? null : Integer.parseInt(n))
        .toArray(Integer[]::new);
    return createTree(nums);
  }

  /**
   * Creates a tree from an array so we can easily replicate LeetCode test
   * cases. LeetCode test cases like to represent a tree as an array constructed
   * from a breath first traversal.
   */
  static TreeNode createTree(Integer... values) {
    if (values.length == 0 || values[0] == null) {
      return null;
    }

    TreeNode root = new TreeNode(values[0]);
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    int i = 1;
    while (!q.isEmpty()) {
      var node = q.remove();

      if (i < values.length) {
        var left = values[i++];
        if (left != null) {
          node.left = new TreeNode(left);
          q.add(node.left);
        }
      }

      if (i < values.length) {
        var right = values[i++];
        if (right != null) {
          node.right = new TreeNode(right);
          q.add(node.right);
        }
      }
    }

    return root;
  }

  /**
   * Converts a string to something resembling an array constructed from a
   * breath first traversal.
   *
   * <p>This is how LeetCode represents graphs and it's helpful for debugging.
   * And also maybe just a good exercise although this implementation is
   * probably a bit lazy.
   */
  static String treeToString(TreeNode root) {
    StringBuilder result = new StringBuilder("[");
    if (root == null) {
      return result.append("]").toString();
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      var node = q.remove();
      result.append(node == null ? "null" : node.val).append(",");
      if (node != null) {
        q.add(node.left);
        q.add(node.right);
      }
    }

    // This is probably dumb but I'm not sure what rule to use for when not to
    // include null in the output. I need to find more examples.
    while (result.lastIndexOf("null,") == result.length() - 5) {
      result.delete(result.length() - 6, result.length() - 1);
    }

    // Remove the trailing comma.
    if (result.charAt(result.length() - 1) == ',') {
      result.deleteCharAt(result.length() - 1);
    }

    return result.append("]").toString();
  }

  static ImmutableList<Integer> inOrderTraversal(TreeNode node) {
    if (node == null) {
      return ImmutableList.of();
    }
    var visited = new ArrayList<Integer>();
    inOrderTraversal(node, visited);
    return ImmutableList.copyOf(visited);
  }

  private static void inOrderTraversal(TreeNode node, List<Integer> visited) {
    if (node == null) {
      return;
    }
    inOrderTraversal(node.left, visited);
    visited.add(node.val);
    inOrderTraversal(node.right, visited);
  }

  /**
   * Invert Binary Tree
   *
   * <p>Given the root of a binary tree, invert the tree, and return its root.
   *
   * <ul>Constraints:
   *   <li>The number of nodes in the tree is in the range [0, 100].
   *   <li>-100 <= Node.val <= 100
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li>https://leetcode.com/problems/invert-binary-tree/
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   */
  static TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    var left = invertTree(root.left);
    var right = invertTree(root.right);
    root.left = right;
    root.right = left;
    return root;
  }
}
