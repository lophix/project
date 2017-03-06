package com.flag.project.analysis.pojo.enums;

/**
 * command enums
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 15:23
 */
public enum  CmmEnum {

    CAR_LOGIN(0X01),REAL_TIME_REPORT(0X02),RE_REPORT(0X04),CAR_LOGOUT(0X05),PLATFORM_LOGIN(0X06),PLATFORM_LOGOUT(0X07);

    private int code;

    CmmEnum(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
