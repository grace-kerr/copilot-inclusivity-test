import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SubstringTasksTest {

  private final SubstringTasks substringTasks = new SubstringTasks();

  @Test
  @DisplayName("Test empty string")
  public void testEmptyString() {
    assertEquals(0, substringTasks.lengthOfLongestSubstring(""));
  }

  @Test
  @DisplayName("Test single character")
  public void testSingleCharacter() {
    assertEquals(1, substringTasks.lengthOfLongestSubstring("a"));
  }

  @Test
  @DisplayName("Test all unique characters")
  public void testAllUniqueCharacters() {
    assertEquals(5, substringTasks.lengthOfLongestSubstring("abcde"));
  }

  @Test
  @DisplayName("Test all same characters")
  public void testAllSameCharacters() {
    assertEquals(1, substringTasks.lengthOfLongestSubstring("aaaaa"));
  }

  @Test
  @DisplayName("Test example case 1: abcabcbb")
  public void testExampleCase1() {
    assertEquals(3, substringTasks.lengthOfLongestSubstring("abcabcbb"));
  }

  @Test
  @DisplayName("Test example case 2: bbbbb")
  public void testExampleCase2() {
    assertEquals(1, substringTasks.lengthOfLongestSubstring("bbbbb"));
  }

  @Test
  @DisplayName("Test example case 3: pwwkew")
  public void testExampleCase3() {
    assertEquals(3, substringTasks.lengthOfLongestSubstring("pwwkew"));
  }

  @Test
  @DisplayName("Test string with spaces")
  public void testStringWithSpaces() {
    assertEquals(3, substringTasks.lengthOfLongestSubstring("a b c"));
  }

  @Test
  @DisplayName("Test string with numbers")
  public void testStringWithNumbers() {
    assertEquals(4, substringTasks.lengthOfLongestSubstring("12321"));
  }

  @Test
  @DisplayName("Test string with special characters")
  public void testStringWithSpecialCharacters() {
    assertEquals(3, substringTasks.lengthOfLongestSubstring("!@#!@#"));
  }

  @ParameterizedTest
  @DisplayName("Test multiple cases with expected results")
  @CsvSource({
    "'', 0",
    "'a', 1",
    "'ab', 2",
    "'aa', 1",
    "'abc', 3",
    "'abba', 2",
    "'abcdef', 6",
    "'dvdf', 3",
    "'anviaj', 5",
    "'abcabcbb', 3",
    "'bbbbb', 1",
    "'pwwkew', 3"
  })
  public void testParameterizedCases(String input, int expected) {
    assertEquals(expected, substringTasks.lengthOfLongestSubstring(input));
  }

  @Test
  @DisplayName("Test long string performance")
  public void testLongString() {
    // Create a string with a pattern that has longest substring of 26 (all lowercase letters)
    String longString = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
    assertEquals(26, substringTasks.lengthOfLongestSubstring(longString));
  }

  @Test
  @DisplayName("Test mixed case sensitivity")
  public void testMixedCase() {
    // 'A' and 'a' are different characters
    assertEquals(4, substringTasks.lengthOfLongestSubstring("aAaA"));
  }

  @Test
  @DisplayName("Test Unicode characters")
  public void testUnicodeCharacters() {
    assertEquals(3, substringTasks.lengthOfLongestSubstring("αβγαβγ"));
  }
}
