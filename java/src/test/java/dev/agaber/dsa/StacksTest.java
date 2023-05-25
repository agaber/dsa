package dev.agaber.dsa;

import static dev.agaber.dsa.Stacks.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class StacksTest {
  private ValidParenthesis parens;

  @BeforeEach
  public void beforeEach() throws Exception {
    parens = new ValidParenthesis();
  }

  @Test
  public void testIsValidParens() throws Exception {
    assertThat(parens.isValid("()")).isTrue();
    assertThat(parens.isValid("()[]{}")).isTrue();
    assertThat(parens.isValid("(]")).isFalse();
    assertThat(parens.isValid("[")).isFalse();
  }
}
