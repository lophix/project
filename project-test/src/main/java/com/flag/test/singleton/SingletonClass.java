package com.flag.test.singleton;

import java.io.Serializable;

/**
 * @author xuj
 * @since 2017-06-29-11:22
 */
public enum SingletonClass implements Serializable {

    SINGLETON_CLASS;

    private String flag;

    SingletonClass() {
    }

    public static SingletonClass getInstance() {
        return SINGLETON_CLASS;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
