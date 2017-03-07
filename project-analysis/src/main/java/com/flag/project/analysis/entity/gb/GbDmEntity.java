package com.flag.project.analysis.entity.gb;

/**
 * 驱动电机
 *
 * @author xuj
 * @version V1.0
 * @since 2017-03-06-10:09
 */
public class GbDmEntity {
    //电机序号 1
    private Integer mSnum;
    //电机状态 1
    private Integer motorStatus;
    //电机控制器温度 1
    private Integer mCT;
    //电机转速 2
    private Integer mS;
    //电机转矩 2
    private Float mR;
    //电机温度 1
    private Integer mT;
    //电机控制器输入电压 2
    private Float mV;
    //电机控制器直流母线电流 2
    private Float mC;

    public Integer getmSnum() {
        return mSnum;
    }

    public void setmSnum(Integer mSnum) {
        this.mSnum = mSnum;
    }

    public Integer getMotorStatus() {
        return motorStatus;
    }

    public void setMotorStatus(Integer motorStatus) {
        this.motorStatus = motorStatus;
    }

    public Integer getmCT() {
        return mCT;
    }

    public void setmCT(Integer mCT) {
        this.mCT = mCT;
    }

    public Integer getmS() {
        return mS;
    }

    public void setmS(Integer mS) {
        this.mS = mS;
    }

    public Float getmR() {
        return mR;
    }

    public void setmR(Float mR) {
        this.mR = mR;
    }

    public Integer getmT() {
        return mT;
    }

    public void setmT(Integer mT) {
        this.mT = mT;
    }

    public Float getmV() {
        return mV;
    }

    public void setmV(Float mV) {
        this.mV = mV;
    }

    public Float getmC() {
        return mC;
    }

    public void setmC(Float mC) {
        this.mC = mC;
    }

    @Override
    public String toString() {
        return "\n电机序号：" +mSnum + "\n"
                + "电机状态：" + motorStatus + "\n"
                + "电机控制器温度：" + mCT + "\n"
                + "电机转速：" + mS + "\n"
                + "电机转矩：" + mR + "\n"
                + "电机温度：" + mT + "\n"
                + "电机控制器输入电压：" + mV + "\n"
                + "电机控制器直流母线电流：" + mC + "\n";
    }
}
