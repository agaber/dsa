package dev.agaber.dsa.challenges;

public class PatternMatcher {
  public static boolean isMatch(String pattern, String input) {
    int inputIndex = 0;
    int patternIndex = 0;
    while (patternIndex < pattern.length()) {
      if (inputIndex > input.length() - 1) {
        return false;
      }
      if (Character.isDigit(pattern.charAt(patternIndex))) {
        int[] numberParse = readNumber(pattern, patternIndex);
        inputIndex += numberParse[0];
        patternIndex+= numberParse[1];
      } else if (pattern.charAt(patternIndex) != input.charAt(inputIndex)) {
        return false;
      } else {
        inputIndex++;
        patternIndex++;
      }
    }

    return inputIndex == input.length();
  }

  private static int[] readNumber(String pattern, int startIndex) {
    StringBuilder numberString = new StringBuilder();
    for (int i = startIndex; i < pattern.length() && Character.isDigit(pattern.charAt(i)); i++) {
      numberString.append(pattern.charAt(i));
    }
    System.out.println(numberString.length());
    int number = Integer.parseInt(numberString.toString());
    return new int[] {number, numberString.length()};
  }
}
