package com.flag.sys;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * the test of sys
 *
 * @author xuj
 * @version V0.0.1-SNAPSHOT
 * @since 2017-04-07-15:17
 */
public class SysTestMain {
    public static void main(String[] args) {
        CLibrary.INSTANCE.printf("Hello %s \n", "world!");
        for (int i = 0; i < 10; i++) {
            CLibrary.INSTANCE.printf("The %d \n", i);
        }
    }

    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)
                Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"),
                        CLibrary.class);

        void printf(String format, Object... args);
    }
}
