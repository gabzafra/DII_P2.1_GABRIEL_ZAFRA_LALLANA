package dam2.dii.p21.model;

public class User {

  private int id;
  private String name;
  private String surnames;
  private String email;
  private String phone;
  private String password;
  private boolean isAdmin;

  public User() {
    this("", "", "", "", "", false);
  }

  public User(String name, String surnames, String email, String phone, String password,
      boolean isAdmin) {
    this(-1, name, surnames, email, phone, password, isAdmin);
  }

  public User(int id, String name, String surnames, String email, String phone, String password,
      boolean isAdmin) {
    this.id = id;
    this.name = name;
    this.surnames = surnames;
    this.email = email;
    this.phone = phone;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurnames() {
    return surnames;
  }

  public void setSurnames(String surnames) {
    this.surnames = surnames;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

}
