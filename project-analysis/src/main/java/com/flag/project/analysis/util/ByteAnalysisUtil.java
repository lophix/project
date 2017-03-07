package com.flag.project.analysis.util;


import java.util.Arrays;

/**
 * bytes analysis
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 17:20
 */
public class ByteAnalysisUtil {

    /**
     * convert byte array to string
     *
     * @param bytes byte array
     * @return string result
     */
    public static String bytes2String(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append((char) (b & 0xff));
        }
        return builder.toString().trim();
    }

    /**
     * convert byte array to bit string
     *
     * @param bytes  byte array
     * @param offset offset, byte array start index
     * @param length convert length
     * @return bit string
     */
    public static String bytes2BitStr(byte[] bytes, int offset, int length) {
        StringBuilder sb = new StringBuilder(8 * length);
        for (int i = 0; i < length; i++) {
            sb.append(byte2BitStr(bytes[offset + i]));
        }
        return sb.toString();
    }

    /**
     * convert byte to bit string
     *
     * @param b source byte
     * @return bit string
     */
    public static String byte2BitStr(byte b) {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append((b >> (7 - i)) & 0x01);
        }
        return sb.toString();
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
        for (int i = 0; i < bytes.length; i++) {
            value |= (bytes[offset + i] & 0xff) << ((index - i) * 8);
        }
        return value;
    }

    /**
     * convert byte array to int
     *
     * @param bytes  byte array
     * @param offset offset, byte array start index
     * @param length covert length
     * @return int result
     */
    public static int bytes2Int(byte[] bytes, int offset, int length) {
        byte[] bytes1 = Arrays.copyOfRange(bytes, offset, offset + length);
        return bytes2Int(bytes1, 0);
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
        for (int i = 0; i < bytes.length; i++) {
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
        for (int i = 0; i < bytes.length; i++) {
            value |= (long) (bytes[offset + i] & 0xff) << ((7 - i) * 8);
        }
        return value;
    }

    /**
     * convert byte to int, this method only convert one bit
     *
     * @param b          source byte
     * @param startIndex start index
     * @return result int
     */
    public static int byte2BitInt(byte b, int startIndex) {
        if (startIndex < 0 || startIndex > 7) {
            throw new RuntimeException("this startIndex not in (0-7)");
        }

        return (b >> startIndex) & 0x01;
    }

    /**
     * reverse convert byte to int, this method only convert one bit
     *
     * @param b          source byte
     * @param startIndex start index
     * @return result int
     */
    public static int byte2BitIntReverse(byte b, int startIndex) {

        startIndex = 7 - startIndex;
        return byte2BitInt(b, startIndex);
    }

    /**
     * convert bit to int
     *
     * @param bit bit string 0/1
     * @return int result
     */
    public static int bit2Int(String bit) {
        return Integer.parseUnsignedInt(bit, 2);
    }
}
