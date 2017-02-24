package com.flag.util;

import org.junit.Test;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-23-14:52
 * @Version
 */
public class BigEndianDecodeUtil {
    public static int bytes2Int(byte[] ary, int offset) {
        int value = 0;
        for (int i = 0; i < Integer.BYTES; i++) {
            value |= (ary[offset + i] & 0xff) << ((3 - i) * 8);
        }
        return value;
    }

    public static long bytes2Long(byte[] bytes, int offset) {
        long value = 0L;
        for (int i = 0; i < Long.BYTES; i++) {
            value |= (long) (bytes[offset + i] & 0xff) << ((7 - i) * 8);
        }
        return value;
    }

    public static String byte2BitStr(byte b) {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(b >> (7 - i) & 0x1);
        }
        return sb.toString();
    }

    @Test
    public void test_byte2Int() {
        byte[] bytes = new byte[]{0x01, 0x02, 0x03, 0x04};
        System.out.println(BigEndianDecodeUtil.bytes2Int(bytes, 0));
    }

    @Test
    public void test_byte2Long() {
        byte[] bytes = new byte[]{0x01, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04};
        System.out.println(BigEndianDecodeUtil.bytes2Long(bytes, 0));
    }

    @Test
    public void test_byte2BitStr() {
        byte b = 0x31;
        System.out.println(BigEndianDecodeUtil.byte2BitStr(b));
    }
}
