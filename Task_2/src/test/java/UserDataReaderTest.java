import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class UserDataReaderTest {

  private UserDataReader userDataReader;

  @BeforeEach
  public void setUp() {
    userDataReader = new UserDataReader();
  }

  @Test
  @DisplayName("Test reading valid user data from JSON file")
  public void testReadUserDataSuccess() {
    // This test expects the method to work correctly when bugs are fixed
    List<User> users = userDataReader.readUserData();

    // Should read 3 users from the provided userdata.json file
    assertEquals(3, users.size(), "Should read exactly 3 users from the file");

    // Verify first user
    User firstUser = users.get(0);
    assertEquals("001", firstUser.getId(), "First user ID should be '001'");
    assertEquals("Alice Johnson", firstUser.getName(), "First user name should be 'Alice Johnson'");
    assertEquals(
        "alice.johnson@example.com", firstUser.getEmail(), "First user email should match");

    // Verify second user
    User secondUser = users.get(1);
    assertEquals("002", secondUser.getId(), "Second user ID should be '002'");
    assertEquals("Bob Smith", secondUser.getName(), "Second user name should be 'Bob Smith'");
    assertEquals("bob.smith@example.com", secondUser.getEmail(), "Second user email should match");

    // Verify third user
    User thirdUser = users.get(2);
    assertEquals("003", thirdUser.getId(), "Third user ID should be '003'");
    assertEquals("Charlie Brown", thirdUser.getName(), "Third user name should be 'Charlie Brown'");
    assertEquals(
        "charlie.brown@example.com", thirdUser.getEmail(), "Third user email should match");
  }

  @Test
  @DisplayName("Test that method handles missing file gracefully")
  public void testReadUserDataMissingFile() {
    // Create a UserDataReader that will try to read from a non-existent file
    // This tests the error handling (which is currently buggy)

    // First, temporarily rename the real file so it can't be found
    Path realFile = Paths.get("./userdata.json");
    Path tempFile = Paths.get("./userdata_temp.json");

    try {
      if (Files.exists(realFile)) {
        Files.move(realFile, tempFile);
      }

      List<User> users = userDataReader.readUserData();

      // When properly implemented, should return empty list or handle error appropriately
      // Currently this will fail because of poor error handling
      assertNotNull(users, "Should return a non-null list even when file is missing");
      assertTrue(users.isEmpty(), "Should return empty list when file cannot be read");

    } catch (IOException e) {
      fail("Should handle file not found gracefully, not throw exception to caller");
    } finally {
      // Restore the original file
      try {
        if (Files.exists(tempFile)) {
          Files.move(tempFile, realFile);
        }
      } catch (IOException e) {
        // Ignore cleanup errors in test
      }
    }
  }

  @Test
  @DisplayName("Test with custom JSON data")
  public void testReadUserDataCustom(@TempDir Path tempDir) throws IOException {
    // Create a custom JSON file for testing
    String customJson =
        "[\n"
            + "  {\n"
            + "    \"user_id\": \"100\",\n"
            + "    \"user_name\": \"Test User\",\n"
            + "    \"email\": \"test@test.com\"\n"
            + "  }\n"
            + "]";

    Path customFile = tempDir.resolve("userdata.json");
    Files.writeString(customFile, customJson);

    // This test would require modifying UserDataReader to accept a file path parameter
    // For now, it documents the expected behavior when working with different data

    // Note: This test may not pass until UserDataReader is made more flexible
    // But it shows the expected structure for users
    User expectedUser = new User("100", "Test User", "test@test.com");
    assertNotNull(expectedUser);
    assertEquals("100", expectedUser.getId());
    assertEquals("Test User", expectedUser.getName());
    assertEquals("test@test.com", expectedUser.getEmail());
  }

  @Test
  @DisplayName("Test User class functionality")
  public void testUserClass() {
    // Test the User class works correctly
    User user = new User("123", "John Doe", "john@example.com");

    assertEquals("123", user.getId());
    assertEquals("John Doe", user.getName());
    assertEquals("john@example.com", user.getEmail());

    // Test equality
    User sameUser = new User("123", "John Doe", "john@example.com");
    assertEquals(user, sameUser);

    // Test toString
    String userString = user.toString();
    assertTrue(userString.contains("123"));
    assertTrue(userString.contains("John Doe"));
    assertTrue(userString.contains("john@example.com"));
  }
}
