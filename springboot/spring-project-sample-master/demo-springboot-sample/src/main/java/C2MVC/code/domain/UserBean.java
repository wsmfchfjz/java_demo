package C2MVC.code.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserBean {
    Integer id;
    String name;

    public UserBean() {
    }

    public UserBean(Integer id, String name) {
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
