package com.flag.test.reflect;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-17-17:11
 * @Version
 */
public class MyPrivateObject {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void print(){
        System.out.println("Hello, you received here, congratulation !!!");
    }
}
