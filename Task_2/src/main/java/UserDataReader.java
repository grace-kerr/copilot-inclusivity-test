import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserDataReader {

  /**
   * Reads user data from a JSON file and returns a list of User objects. The JSON file should
   * contain an array of user objects with the following structure: [ { "user_id": "123",
   * "user_name": "John Doe", "email": "john@example.com" } ]
   *
   * @return List of User objects read from the file
   */
  public List<User> readUserData() {
    String path = "./userdata"; // BUG 1: should be "./userdata.json"
    List<User> users = new ArrayList<>();

    try {
      String json = Files.readString(Paths.get(path));
      JSONArray arr = new JSONArray(json);
      for (int i = 0; i < arr.length(); i++) {
        JSONObject obj = arr.getJSONObject(i);
        String id = obj.getString("user_id");
        String name = obj.getString("username"); // BUG 2: should be "user_name"
        String email = obj.getString("email");

        users.add(new User(id, name, email));
      }
    } catch (IOException e) {
      // BUG 3: No error handling - exception is caught but ignored
    }

    return users;
  }
}
