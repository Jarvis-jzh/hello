package person.jzh.hello.mybatis.entity;

/**
 * @author jzh
 * @version 1.0.0
 * @title Test
 * @date 2020/1/7 15:37
 * @description：
 */
public class Test {
    private Integer id;

    private String name;

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

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
