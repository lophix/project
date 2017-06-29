package com.flag.test.singleton;

import com.flag.project.core.utils.PathUtil;

import java.io.*;
import java.nio.file.Files;

/**
 * @author xuj
 * @since 2017-06-29-11:26
 */
public class SingletonTestMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SingletonClass singletonClass = SingletonClass.getInstance();
        singletonClass.setFlag("hello");
        serialObj(singletonClass);
        SingletonClass object = (SingletonClass) deserialObj();
        System.out.println(object.getFlag() + "\t" + singletonClass.getFlag());
    }

    private static void serialObj(Object obj) throws IOException {

        try (OutputStream fos = Files.newOutputStream(PathUtil.getPath(SingletonClass.class, "test.txt"));
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        }
    }

    private static Object deserialObj() throws IOException, ClassNotFoundException {
        try (InputStream fis = Files.newInputStream(PathUtil.getPath(SingletonClass.class, "test.txt"));
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return ois.readObject();
        }
    }
}
