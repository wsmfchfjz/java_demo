package C3Boot.code.beans;

/**
 * @author edliao on 2017/6/21.
 * @description TODO
 */
public class User {
  Integer id;
  String name;

  public User(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}