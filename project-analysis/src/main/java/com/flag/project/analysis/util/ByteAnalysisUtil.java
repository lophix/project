package com.flag.project.analysis.util;


/**
 * bytes analysis
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 17:20
 */
public class ByteAnalysisUtil {

    /**
     * convert byte array to string, if there have 0xff 0xfc bytes
     * it will be converted to 0x23 0x23 bytes
     *
     * @param bytes byte array
     * @return string result
     */
    public static String bytes2String(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        int length = bytes.length;

        for (int i = 0; i < length - 1; i++) {
            if (bytes[i] == (byte) 0xff && bytes[i + 1] == (byte) 0xfc) {
                bytes[i] = 0x23;
                bytes[++i] = 0x23;
            }
        }

        for (byte b : bytes) {
            builder.append((char) (b & 0xff));
        }
        return builder.toString().trim();
    }

    /**
     * convert byte array to int, this method for big endian
     *
     * @param bytes  byte array
     * @param offset offset
     * @return int result
     */
    public static int bytes2Int(byte[] bytes, int offset) {
        int value = 0;
        int index = bytes.length - 1;
        for (int i = 0; i < Integer.BYTES; i++) {
            value |= (bytes[offset + i] & 0xff) << ((index - i) * 8);
        }
        return value;
    }

    /**
     * convert byte array to short, this method for big endian
     *
     * @param bytes  byte array
     * @param offset offset
     * @return short result
     */
    public static short bytes2Short(byte[] bytes, int offset) {
        short value = 0;
        int index = bytes.length - 1;
        for (int i = 0; i < Short.BYTES; i++) {
            value |= (short) (bytes[offset + i] & 0xff) << ((index - i) * 8);
        }
        return value;
    }

    /**
     * convert byte array to long, this method for big endian
     *
     * @param bytes  byte array
     * @param offset offset
     * @return long result
     */
    public static long bytes2Long(byte[] bytes, int offset) {
        long value = 0L;
        for (int i = 0; i < Long.BYTES; i++) {
            value |= (long) (bytes[offset + i] & 0xff) << ((7 - i) * 8);
        }
        return value;
    }
}
