package com.flag.log.cleaning;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Created by xu on 2018/1/4.
 */
public interface CommonConst {
    Pattern THREAD_NAME_PATTERN = Pattern.compile("\\[\\S+-\\d+-\\d+]");
    Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
