package com.flag.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

/**
 * @Description
 * @Author xuj
 * @Create 2017-02-23-14:52
 * @Version
 */
public class BigEndianDecodeUtil {
    @Contract(pure = true)
    public static int bytes2Int(byte[] ary, int offset) {
        int value = 0;
        for (int i = 0; i < Integer.BYTES; i++) {
            value |= (ary[offset + i] & 0xff) << ((3 - i) * 8);
        }
        return value;
    }

    @Contract(pure = true)
    public static long bytes2Long(byte[] bytes, int offset) {
        long value = 0L;
        for (int i = 0; i < Long.BYTES; i++) {
            value |= (long) (bytes[offset + i] & 0xff) << ((7 - i) * 8);
        }
        return value;
    }

    @NotNull
    public static String byte2BitStr(byte b) {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append(b >> (7 - i) & 0x1);
        }
        return sb.toString();
    }

    @Test
    public void test_byte2Int() {
        byte[] bytes = new byte[]{0x00, 0x00, (byte) 0xff, (byte) 0xff};
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

    @Test
    public void test_print(){
        byte[] bytes = new byte[]{0x11, 0x02, 0x0c, 0x0b, 0x01, 0x02};
        System.out.println(bytes2Int(bytes, 0));
    }

    public static String bcd2Str(byte[] bytes,int offset,int length) {

        byte[] b = new byte[length];
        //System.out.println(bytes.length+","+length);
        System.arraycopy(bytes, offset, b, 0, length);


        StringBuffer temp = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            temp.append((byte) ((b[i] & 0xf0) >>> 4));
            temp.append((byte) (b[i] & 0x0f));
        }
        String s = temp.toString();
        return s;
    }

    @Test
    public void test(){
        System.out.println(0xff);
    }
}
