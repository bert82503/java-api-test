package jdk8.tutorial;

/**
 * 用户领域模型。
 *
 * @author dannong
 * @since 2017年03月30日
 */
public class UserDO {
    private String name;

    private int age;

    private String introduction;


    public UserDO() {}

    public UserDO(String name, int age, String introduction) {
        this.name = name;
        this.age = age;
        this.introduction = introduction;
    }


    public String getName() {
        return name;
    }

    public UserDO setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserDO setAge(int age) {
        this.age = age;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public UserDO setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }
}
