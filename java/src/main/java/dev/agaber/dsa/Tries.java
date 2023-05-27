package dev.agaber.dsa;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

final class Tries {
  /**
   * Implement Trie (Prefix Tree)
   *
   * <p>A trie (pronounced as "try") or prefix tree is a tree data structure
   * used to efficiently store and retrieve keys in a dataset of strings. There
   * are various applications of this data structure, such as autocomplete and
   * spellchecker.
   *
   * <p>Implement the Trie class:
   *
   * <ul>
   *   <li>Trie() Initializes the trie object.
   *   <li>void insert(String word) Inserts the string word into the trie.
   *   <li>boolean search(String word) Returns true if the string word is in the
   *       trie (i.e., was inserted before), and false otherwise.
   *   <li>boolean startsWith(String prefix) Returns true if there is a
   *       previously inserted string word that has the prefix prefix, and false
   *       otherwise.
   * </ul>
   *
   * <ul>Constraints:
   *   <li>1 <= word.length, prefix.length <= 2000
   *   <li>word and prefix consist only of lowercase English letters.
   *   <li>At most 3 * 104 calls in total will be made to insert, search, and
   *       startsWith.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li>https://leetcode.com/problems/implement-trie-prefix-tree/
   *   <li>Time complexity: O(n log n)
   *   <li>Space complexity: O(m) where m is the number of distinct characters
   *       in the string.
   * </ul>
   */
  static class Trie {
    private final TrieNode root;

    public Trie() {
      root = new TrieNode();
    }

    public void insert(String word) {
      TrieNode node = root;
      for (char letter : word.toCharArray()) {
        node = node.add(letter);
      }
      node.setEnd(true);
    }

    public boolean search(String word) {
      return find(word).map(TrieNode::isEnd).orElse(false);
    }

    public boolean startsWith(String prefix) {
      return find(prefix).isPresent();
    }

    private Optional<TrieNode> find(String wordOrPrefix) {
      var node = Optional.of(root);
      for (char letter : wordOrPrefix.toCharArray()) {
        if (!node.isPresent()) {
          return Optional.empty();
        } else {
          node = node.get().get(letter);
        }
      }
      return node;
    }
  }

  @Data
  private static class TrieNode {
    private final Map<Character, TrieNode> neighbors;
    private boolean isEnd;

    public TrieNode() {
      this.neighbors = new HashMap<>(26);
      this.isEnd = false;
    }

    public TrieNode add(char letter) {
      return neighbors.computeIfAbsent(letter, l -> new TrieNode());
    }

    public Optional<TrieNode> get(char letter) {
      return Optional.ofNullable(neighbors.get(letter));
    }
  }

  private Tries() {}
}
