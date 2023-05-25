package dev.agaber.dsa.challenges;

import static java.util.Comparator.comparing;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Word Count Engine
 *
 * <p>
 * Implement a document scanning function wordCountEngine, which receives a
 * string document and returns a list of all unique words in it and their
 * number of occurrences, sorted by the number of occurrences in descending
 * order. If two or more words have the same count, they should be sorted
 * according to their order in the original sentence. Assume that all letters
 * are in english alphabet. Your function should be case-insensitive,
 * so for instance, the words “Perfect” and “perfect” should be considered
 * the same word.
 *
 * <p>
 * The engine should strip out punctuation (even in the middle of a word)
 * and use whitespaces to separate words.
 *
 * <p>
 * Analyze the time and space complexities of your solution. Try to
 * optimize for time while keeping a polynomial space complexity.
 *
 * <p>
 * Examples:
 *
 * <pre>
 * input:  document = "Practice makes perfect. you'll only
 *                     get Perfect by practice. just practice!"
 *
 * output: [ ["practice", "3"], ["perfect", "2"],
 *           ["makes", "1"], ["youll", "1"], ["only", "1"],
 *           ["get", "1"], ["by", "1"], ["just", "1"] ]
 * </pre>
 *
 * <p>
 * Important: please convert the occurrence integers in the output list to
 * strings (e.g. "3" instead of 3). We ask this because in compiled languages
 * such as C#, Java, C++, C etc., it’s not straightforward to create mixed-type
 * arrays (as it is, for instance, in scripted languages like JavaScript,
 * Python, Ruby etc.). The expected output will simply be an array of string
 * arrays.
 *
 * <p>
 * Constraints:
 *
 * <ul>
 * <li>[time limit] 5000ms
 * <li>[input] string document
 * <li>[output] array.array.string
 * </ul>
 *
 * <p>
 * See https://www.pramp.com/challenge/W5EJq2Jld3t2ny9jyZXG
 * </p>
 */
final class WordCountEngine {
  public static String[][] count(String document) {
    var counter = new HashMap<String, Tuple>();

    int order = 0;
    StringBuilder wordBuilder = new StringBuilder();
    for (int i = 0; i < document.length(); i++) {
      char letter = document.charAt(i);

      if (Character.isLetter(letter)) {
        wordBuilder.append(Character.toLowerCase(letter));
      }

      if (letter == ' ' || i == document.length() - 1) {
        String word = wordBuilder.toString();
        wordBuilder.setLength(0);
        if (!word.isBlank()) {
          order++;
          var tuple = counter.getOrDefault(word, new Tuple(word, order));
          tuple.count++;
          counter.put(word, tuple);
        }
      }
    }

    List<Tuple> tuples = new ArrayList<>(counter.values());
    tuples.sort(comparing(Tuple::getCount).reversed().thenComparing(Tuple::getOrder));

    return tuples.stream()
        .map(t -> new String[] { t.getWord(), String.valueOf(t.getCount()) })
        .toArray(String[][]::new);
  }

  @Data
  private static class Tuple {
    public int count;
    public int order;
    public String word;

    public Tuple(String word, int order) {
      this.count = 0;
      this.order = order;
      this.word = word;
    }
  }
}
