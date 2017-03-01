package com.flag.project.analysis.test;

import org.junit.Test;

/**
 * @author xuj
 * @since 2017-03-01 17:04
 */
public class IntegerAndByte {
    @Test
    public void test(){
        byte b = (byte) 0xfe;
        int i = b;
        System.out.println(i);
    }
}
