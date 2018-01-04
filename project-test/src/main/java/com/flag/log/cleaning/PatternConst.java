package com.flag.log.cleaning;

import java.util.regex.Pattern;

/**
 * Created by xu on 2018/1/4.
 */
public interface PatternConst {
    Pattern THREAD_NAME_PATTERN = Pattern.compile("\\[\\S+-\\d+-\\d+]");
    Pattern DATE_PATTERN = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
}
