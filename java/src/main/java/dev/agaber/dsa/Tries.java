package dev.agaber.dsa;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

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
   *   <li><a href="https://leetcode.com/problems/implement-trie-prefix-tree/">LeetCode</a>
   *   <li>Time complexity: O(m) for both add and search.
   *   <li>Space complexity: Add: O(m) where m is the number of distinct characters
   *       in the string. Search: O(1).
   * </ul>
   */
  static final class Trie {
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
        if (node.isEmpty()) {
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

  /**
   * Design Add and Search Words Data Structure
   *
   * <p>Design a data structure that supports adding new words and finding if a
   * string matches any previously added string.
   *
   * <p>Implement the WordDictionary class:
   * <ul>
   *   <li>WordDictionary() Initializes the object.
   *   <li>void addWord(word) Adds word to the data structure, it can be matched
   *       later.
   *   <li>bool search(word) Returns true if there is any string in the data
   *       structure that matches word or false otherwise. word may contain dots
   *       '.' where dots can be matched with any letter.
   * </ul>
   *
   * <ul>Constraints:
   *   <li>1 <= word.length <= 25
   *   <li>word in addWord consists of lowercase English letters.
   *   <li>word in search consist of '.' or lowercase English letters.
   *   <li>There will be at most 2 dots in word for search queries.
   *   <li>At most 104 calls will be made to addWord and search.
   * </ul>
   *
   * <ul>
   *   <li>List: Blind 75
   *   <li>Level: Medium
   *   <li><a href="https://leetcode.com/problems/design-add-and-search-words-data-structure">Leetcode</a>
   *   <li>Time complexity: Adding a word is O(m) where m is the number of
   *       letters in each word. Search might be O(m) when it doesn't have a
   *       "." and O(N * 26^m) when it does, where N is the number of keys.
   *   <li>Space complexity: For add, it's O(m) where m is the number of distinct characters
   *       in the string. For search it might be O(1).
   * </ul>
   */
  static final class WordDictionary {
    private final TrieNode root;

    public WordDictionary() {
      this.root = new TrieNode();
    }

    public void addWord(String word) {
      var node = root;
      for (char letter : word.toCharArray()) {
        node = node.add(letter);
      }
      node.setEnd(true);
    }

    public boolean search(String word) {
      List<TrieNode> roots = List.of(root);
      for (char letter : word.toCharArray()) {
        if (letter == '.') {
          roots = roots.stream()
              .map(n -> n.getNeighbors().values())
              .flatMap(Collection::stream)
              .collect(Collectors.toList());
        } else {
          roots = roots.stream()
              .map(n -> n.get(letter))
              .flatMap(Optional::stream)
              .collect(Collectors.toList());
        }
      }
      return roots.stream().anyMatch(TrieNode::isEnd);
    }
  }

  private Tries() {}
}
