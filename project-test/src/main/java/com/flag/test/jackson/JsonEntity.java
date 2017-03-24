package com.flag.test.jackson;

import java.util.List;

/**
 * entity of json object
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-14-10:50
 */
public class JsonEntity {
    private String name;
    private Integer age;
    private String hobbiesStr;
    private List<TestEntity> hobbies;

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

    public String getHobbiesStr() {
        return hobbiesStr;
    }

    public void setHobbiesStr(String hobbiesStr) {
        this.hobbiesStr = hobbiesStr;
    }

    public List<TestEntity> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<TestEntity> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "JsonEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbiesStr='" + hobbiesStr + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }
}

class TestEntity{
    private String hobby;
    private Integer level;

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "hobby='" + hobby + '\'' +
                ", level=" + level +
                '}';
    }
}
