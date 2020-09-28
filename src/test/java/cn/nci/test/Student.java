package cn.nci.test;

/**
 * @program: centispacesg
 * @description:
 * @author: xiejianfeng
 * @create: 2020-09-28 16:08
 */
public class Student {
    private String name;
    private Integer age;
    private String gender;

    public Student(String name, Integer age, String gender) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
