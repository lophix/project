package com.flag.test.reflect;

/**
 * @author xuj
 * @since 2017-06-27-16:04
 */
public class RealPrivateClass {
    private String name;
    private String job;
    private RealPrivateClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
