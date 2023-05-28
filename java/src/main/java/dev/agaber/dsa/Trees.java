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
      return right == null || right.equals(other.right);
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
   *   <li><a href="https://leetcode.com/problems/invert-binary-tree/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   */
  static TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    var left = invertTree(root.left);
    root.left = invertTree(root.right);
    root.right = left;
    return root;
  }

  /**
   * Maximum Depth of Binary Tree
   *
   * <p>Given the root of a binary tree, return its maximum depth.
   *
   * <p>A binary tree's maximum depth is the number of nodes along the longest
   * path from the root node down to the farthest leaf node.
   *
   * <ul>Constraints:
   *   <li>The number of nodes in the tree is in the range [0, 10^4].
   *   <li>-100 <= Node.val <= 100
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li><a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   */
  static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = 1 + maxDepth(root.left);
    int right = 1 + maxDepth(root.right);
    return Math.max(left, right);
  }

  /**
   * Same Tree
   *
   * <p>Given the roots of two binary trees p and q, write a function to check
   * if they are the same or not.
   *
   * <p>Two binary trees are considered the same if they are structurally
   * identical, and the nodes have the same value.
   *
   * <ul>Constraints:
   *   <li>The number of nodes in both trees is in the range [0, 100].
   *   <li>-104 <= Node.val <= 10^4
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li><a href="https://leetcode.com/problems/same-tree/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   *
   * <p>Discussion: Yes this is the same logic as TreeNode.equals(TreeNode) and
   * no, I'm not going to consolidate anything.
   */
  static boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    }
    if (p == null || q == null) {
      return false;
    }
    if (p.val != q.val) {
      return false;
    }
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

  /**
   * Subtree of Another Tree
   *
   * <p>Given the roots of two binary trees root and subRoot, return true if
   * there is a subtree of root with the same structure and node values of
   * subRoot and false otherwise.
   *
   * <p>A subtree of a binary tree is a tree that consists of a node in tree and
   * all of this node's descendants. The tree could also be considered as a
   * subtree of itself.
   *
   * <ul>Constraints:
   *   <li>The number of nodes in the root tree is in the range [1, 2000].
   *   <li>The number of nodes in the subRoot tree is in the range [1, 1000].
   *   <li>-10^4 <= root.val <= 10^4
   *   <li>-10^4 <= subRoot.val <= 10^4
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Easy
   *   <li><a href="https://leetcode.com/problems/subtree-of-another-tree">LeetCode</a>
   *   <li>Time complexity: O(mn), n = nodes in root, m = nodes in subRoot
   *   <li>Space complexity: O(m + n)
   * </ul>
   *
   * <p>Discussion: This is easier when you've already solved isSameTree, I
   * guess.
   */
  static boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null && subRoot == null) {
      return true;
    }
    if (root == null || subRoot == null) {
      return false;
    }
    if (isSameTree(root, subRoot)) {
      return true;
    }
    return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
  }

  /**
   * Lowest Common Ancestor of a Binary Search Tree
   *
   * <p>Given a binary search tree (BST), find the lowest common ancestor (LCA)
   * node of two given nodes in the BST.
   *
   * <p>According to the definition of LCA on Wikipedia: "The lowest common
   * ancestor is defined between two nodes p and q as the lowest node in T that
   * has both p and q as descendants (where we allow a node to be a descendant
   * of itself)."
   *
   * <ul>Constraints:
   *   <li>The number of nodes in the tree is in the range [2, 10^5].
   *   <li>-109 <= Node.val <= 109
   *   <li>All Node.val are unique.
   *   <li>p != q
   *   <li>p and q will exist in the BST.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li><a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   */
  static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val < root.val && q.val < root.val) {
      return lowestCommonAncestor(root.left, p, q);
    } else if (p.val > root.val && q.val > root.val) {
      return lowestCommonAncestor(root.right, p, q);
    } else {
      return root;
    }
  }

  /**
   * Binary Tree Level Order Traversal
   *
   * <p>Given the root of a binary tree, return the level order traversal of its
   * nodes' values. (i.e., from left to right, level by level).
   *
   * <ul>Constraints:
   *   <li>The number of nodes in the tree is in the range [0, 2000].
   *   <li>-1000 <= Node.val <= 1000
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li><a href="https://leetcode.com/problems/binary-tree-level-order-traversal/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   */
  static List<List<Integer>> levelOrder(TreeNode root) {
    var result = new ArrayList<List<Integer>>();
    if (root == null) {
      return result;
    }
    record Tuple(TreeNode node, int level) {}
    Queue<Tuple> q = new LinkedList<>();
    q.offer(new Tuple(root, 1));
    while (!q.isEmpty()) {
      var tuple = q.poll();
      var node = tuple.node();
      int level = tuple.level();
      while (result.size() < level) {
        result.add(new ArrayList<Integer>());
      }
      result.get(level - 1).add(node.val);
      if (node.left != null) {
        q.offer(new Tuple(node.left, level + 1));
      }
      if (node.right != null) {
        q.offer(new Tuple(node.right, level + 1));
      }
    }
    return result;
  }

  /**
   * Validate Binary Search Tree
   *
   * <p>Given the root of a binary tree, determine if it is a valid binary
   * search tree (BST).
   *
   * <p>A valid BST is defined as follows:
   *
   * <ul>
   *   <li>The left subtree of a node contains only nodes with keys less than
   *       the node's key.
   *   <li>The right subtree of a node contains only nodes with keys greater
   *       than the node's key.
   *   <li>Both the left and right subtrees must also be binary search trees.
   * </ul>
   *
   * <ul>Constraints:
   *   <li>The number of nodes in the tree is in the range [1, 10^4].
   *   <li>-231 <= Node.val <= 231 - 1
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li><a href="https://leetcode.com/problems/validate-binary-search-tree/">LeetCode</a>
   *   <li>Time complexity: O(n)
   *   <li>Space complexity: O(n)
   * </ul>
   *
   * <p>Discussion: There is an alternative solution that is more intuitive to
   * me but suboptimal, which is to build an array of values using an inorder
   * traversal and then check if the array is sorted properly. That runs in
   * O(2n) which is still O(n) but less efficient than this solution. I actually
   * find the recursive solution below to be kind of confusing tbh.
   */
  static boolean isValidBST(TreeNode root) {
    return isValidBST(root, null, null);
  }

  private static boolean isValidBST(TreeNode root, Integer low, Integer high) {
    if (root == null) {
      return true;
    }
    if (low != null && root.val >= low) {
      return false;
    }
    if (high != null && root.val <= high) {
      return false;
    }
    return isValidBST(root.left, root.val, high) && isValidBST(root.right, low, root.val);
  }
}
