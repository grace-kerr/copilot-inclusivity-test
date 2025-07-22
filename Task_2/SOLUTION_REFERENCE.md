# Reference Solution - For Interviewer Only

**Note**: This file contains the bugs and their fixes. Do not share with participants.

## 🐛 Bugs Planted in the Code

### Bug 1: Incorrect File Path
**Location**: Line 24 in `UserDataReader.java`
```java
String path = "./userdata"; // BUG: Missing .json extension
```
**Fix**: Should be `"./userdata.json"`

### Bug 2: Wrong JSON Key
**Location**: Line 31 in `UserDataReader.java`
```java
String name = obj.getString("username"); // BUG: Wrong key name
```
**Fix**: Should be `obj.getString("user_name")` to match the JSON structure

### Bug 3: Silent Exception Handling
**Location**: Lines 34-36 in `UserDataReader.java`
```java
} catch (IOException e) {
    // BUG: Exception is caught but ignored - no error handling
}
```
**Fix**: Should log error or handle appropriately, e.g.:
```java
} catch (IOException e) {
    System.err.println("Error reading user data file: " + e.getMessage());
    // Could also throw a runtime exception or return empty list with logging
}
```

## ✅ Complete Fixed Code

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserDataReader {
    
    public List<User> readUserData() {
        String path = "./userdata.json"; // FIXED: Added .json extension
        List<User> users = new ArrayList<>();
        
        try {
            String json = Files.readString(Paths.get(path));
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String id = obj.getString("user_id");
                String name = obj.getString("user_name"); // FIXED: Correct key name
                String email = obj.getString("email");
                
                users.add(new User(id, name, email));
            }
        } catch (IOException e) {
            // FIXED: Proper error handling
            System.err.println("Error reading user data file: " + e.getMessage());
            return new ArrayList<>(); // Return empty list instead of partial data
        } catch (Exception e) {
            // Handle JSON parsing errors
            System.err.println("Error parsing JSON data: " + e.getMessage());
            return new ArrayList<>();
        }
        
        return users;
    }
}
```

## 🧪 Expected Test Results

After fixes:
- **testReadUserDataSuccess**: ✅ Should pass - reads 3 users correctly
- **testReadUserDataMissingFile**: ✅ Should pass - handles missing file gracefully  
- **testReadUserDataCustom**: ✅ Should pass - tests User class functionality
- **testUserClass**: ✅ Should pass - User class works correctly

## 🎯 Learning Objectives

This task teaches participants to:
1. **Debug file I/O issues** - Incorrect file paths
2. **Debug JSON parsing** - Wrong key names in data structures
3. **Implement proper error handling** - Don't silently ignore exceptions
4. **Use tests for debugging** - Tests show expected vs actual behavior
5. **Read existing code** - Understanding unfamiliar codebase structure
