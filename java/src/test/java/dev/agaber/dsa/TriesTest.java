package dev.agaber.dsa;

import static dev.agaber.dsa.Tries.*;
import static org.assertj.core.api.Assertions.assertThat;

import dev.agaber.dsa.Tries.*;
import org.junit.jupiter.api.Test;

public final class TriesTest {
  @Test
  public void trieTest() throws Exception {
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
}
