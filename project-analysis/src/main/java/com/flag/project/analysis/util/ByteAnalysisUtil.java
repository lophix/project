package com.flag.project.analysis.util;


/**
 * bytes analysis
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 17:20
 */
public class ByteAnalysisUtil {

    public static String bytes2String(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        int length = bytes.length;
        for (byte b : bytes) {
            builder.append((char) (b & 0xff));
        }
        return builder.toString().trim();
    }
}
