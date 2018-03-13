package github.eddy.services;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

  public String hello() {
    return "Hello !";
  }

  public String welcome(String name) {
    return "Welcome ," + name + " !";
  }
}
