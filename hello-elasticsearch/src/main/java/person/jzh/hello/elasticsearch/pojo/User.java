package person.jzh.hello.elasticsearch.pojo;

import java.io.Serializable;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/5/22 11:35
 * @description
 */
public class User implements Serializable {
    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
