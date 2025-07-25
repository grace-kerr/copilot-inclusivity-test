public class User {
  private String id;
  private String name;
  private String email;

  public User(String id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    User user = (User) obj;
    return id.equals(user.id) && name.equals(user.name) && email.equals(user.email);
  }

  @Override
  public String toString() {
    return "User{id='" + id + "', name='" + name + "', email='" + email + "'}";
  }
}
