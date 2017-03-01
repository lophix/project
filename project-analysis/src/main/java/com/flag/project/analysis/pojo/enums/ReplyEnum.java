package com.flag.project.analysis.pojo.enums;

/**
 * Created by xu on 2017/3/1.
 */
public enum ReplyEnum {

    SUCCESS(0X01),ERROR(0X02),SAME_VIN(0X03),COMMAND(0XFE);

    private int code;

    ReplyEnum(int code){
        this.code = code;
    }
}
