package dev.agaber.dsa;

import static dev.agaber.dsa.Tries.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public final class TriesTest {
  @Test
  public void trieTest() {
    // ["Trie","insert","search","search","startsWith","insert","search"]
    // [[],["apple"],["apple"],["app"],["app"],["app"],["app"]]
    Trie trie = new Trie();

    trie.insert("apple");
    assertThat(trie.search("apple")).isTrue();
    assertThat(trie.search("app")).isFalse();
    assertThat(trie.startsWith("app")).isTrue();

    trie.insert("app");
    assertThat(trie.search("app")).isTrue();
    assertThat(trie.startsWith("app")).isTrue();
  }

  @Test
  public void wordDictionary() {
    // ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
    // [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
    var wordDict = new WordDictionary();
    wordDict.addWord("bad");
    wordDict.addWord("dad");
    wordDict.addWord("mad");
    assertThat(wordDict.search("pad")).isFalse();
    assertThat(wordDict.search("bad")).isTrue();
    assertThat(wordDict.search(".ad")).isTrue();
    assertThat(wordDict.search("b..")).isTrue();
  }
}
