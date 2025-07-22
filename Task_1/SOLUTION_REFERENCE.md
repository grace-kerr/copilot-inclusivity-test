# Reference Solution - For Interviewer Only

**Note**: This file contains the reference solution and should not be shared with participants.

## Complete Solution

```java
import java.util.*;

public class SubstringTasks {
    
    /**
     * Finds the length of the longest substring without repeating characters.
     * Uses sliding window technique with HashSet.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(min(m,n)) where m is the size of the character set
     * 
     * @param s the input string
     * @return the length of the longest substring without repeating characters
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, maxLength = 0;
        
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        
        return maxLength;
    }
}
```

## Algorithm Explanation

1. **Sliding Window Approach**: Use two pointers (left and right) to maintain a window
2. **HashSet for Tracking**: Keep track of characters in current window
3. **Expand or Shrink**: 
   - If character at right pointer is not in set, add it and expand window
   - If character is already in set, remove character at left pointer and shrink window
4. **Track Maximum**: Keep track of the maximum window size seen so far

## Alternative Solutions

### Approach 2: Using HashMap with Indices
```java
public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int left = 0, maxLength = 0;
    
    for (int right = 0; right < s.length(); right++) {
        if (map.containsKey(s.charAt(right))) {
            left = Math.max(left, map.get(s.charAt(right)) + 1);
        }
        map.put(s.charAt(right), right);
        maxLength = Math.max(maxLength, right - left + 1);
    }
    
    return maxLength;
}
```

This approach can be more efficient as it can skip characters in one step.
