package C3Boot.code.controller;

import C3Boot.code.beans.User;
import C3Boot.code.config.AuthorSettings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RestfulController {

  @Autowired
  AuthorSettings authorSettings;

  @RequestMapping("/author")
  public String getAuthorInfo() {
    return "author name is " + authorSettings.getName() + " and qq is " + authorSettings.getQq();
  }

  @RequestMapping("/user")
  public User getUser(String name) {
    return new User(name.hashCode(), name);
  }

  @RequestMapping("/users")
  public User[] getUsers(String... names) {
    return Stream.of(names).map(s -> new User(s.hashCode(), s))
        .collect(Collectors.toList())
        .toArray(new User[0]);
  }

  @RequestMapping("/userList")
  public List<User> getUserList(@RequestBody List<String> names) {
    return names.stream().map(s -> new User(s.hashCode(), s)).collect(Collectors.toList());
  }

  @RequestMapping("/userMap")
  public Map<String, User> getUserMap(String name) {
    Map map = new HashMap();
    map.put(name, new User(name.hashCode(), name));
    return map;
  }

}
