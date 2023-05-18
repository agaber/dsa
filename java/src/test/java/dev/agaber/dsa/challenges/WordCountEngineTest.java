package dev.agaber.dsa.challenges;

import static dev.agaber.dsa.challenges.WordCountEngine.count;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public final class WordCountEngineTest {
  @Test
  public void wordCountEngine1() throws Exception {
    long start = System.currentTimeMillis();

    String document = "Practice makes perfect. you'll only get Perfect by practice. just practice!";
    assertThat(stringify(count(document)))
        .isEqualTo(
            "[ [practice, 3], [perfect, 2], [makes, 1], [youll, 1], [only, 1], " +
                "[get, 1], [by, 1], [just, 1] ]");

    long end = System.currentTimeMillis();
    assertThat(end - start).isLessThan(5000L);
  }

  @Test
  public void wordCountEngine2() throws Exception {
    long start = System.currentTimeMillis();

    String document = "Every book is a quotation; and every     house is a quotation out of all "
        + "forests, and mines, and stone quarries; and every man is a quotation from all his "
        + "ancestors. ";
    assertThat(stringify(count(document))).isEqualTo(
        "[ [and, 4], [every, 3], [is, 3], [a, 3], [quotation, 3], [all, 2], [book, 1], " +
            "[house, 1], [out, 1], [of, 1], [forests, 1], [mines, 1], [stone, 1], [quarries, 1], " +
            "[man, 1], [from, 1], [his, 1], [ancestors, 1] ]");

    long end = System.currentTimeMillis();
    assertThat(end - start).isLessThan(5000L);
  }

  @Test
  public void wordCountEngine3() throws Exception {
    long start = System.currentTimeMillis();

    String document = "Cause I'm Slim Shady, yes I'm the real Shady, All you other Slim Shadys " +
        "are just imitating So won't the real Slim Shady, please stand up, Please stand up, " +
        "Please stand up";
    assertThat(stringify(count(document))).isEqualTo(
        "[ [slim, 3], [shady, 3], [please, 3], [stand, 3], [up, 3], [im, 2], [the, 2], " +
            "[real, 2], [cause, 1], [yes, 1], [all, 1], [you, 1], [other, 1], [shadys, 1], " +
            "[are, 1], [just, 1], [imitating, 1], [so, 1], [wont, 1] ]");

    long end = System.currentTimeMillis();
    assertThat(end - start).isLessThan(5000L);
  }

  private static String stringify(String[][] result) {
    StringBuilder output = new StringBuilder();
    output.append("[");
    for (int i = 0; i < result.length; i++) {
      output.append(String.format(" [%s, %s]", result[i][0], result[i][1]));
      if (i < result.length - 1) {
        output.append(",");
      }
    }
    output.append(" ]");
    return output.toString();
  }
}
