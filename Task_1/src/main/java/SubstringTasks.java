import java.util.*;

public class SubstringTasks {

  /**
   * Finds the length of the longest substring without repeating characters.
   *
   * @param s the input string
   * @return the length of the longest substring without repeating characters
   */
  public int lengthOfLongestSubstring(String s) {
    Set<Character> seen = new HashSet<>();
    int left = 0, maxLen = 0;
    for (int right = 0; right < s.length(); right++) {
      while (seen.contains(s.charAt(right))) {
        seen.remove(s.charAt(left));
        left++;
      }
      seen.add(s.charAt(right));
      maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
  }
}
