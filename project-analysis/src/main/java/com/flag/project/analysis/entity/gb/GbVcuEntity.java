package com.flag.project.analysis.entity.gb;

/**
 * 新国标vcu数据封装类
 *
 * @author xuj
 * @since 2017-03-03-16:59
 */
public class GbVcuEntity extends GbBaseEntity {

    /**
     * 整车数据
     */

    //车速 2
    private Float carS;
    //累计里程 4
    private Float mile;
    //总电压值 2
    private Float tV;
    //总电流值 2
    private Float tC;
    //soc 1
    private Integer soc;
    //DCDC温度 1
    private Integer dcdcT;
    //加速踏板行程值 1
    private Integer res;
    //制动踏板状态 1
    private Integer braS;

//    刹车踏板开度
//    private Integer braK;
//    加速踏板开度
//    private Integer resK;

    //车辆状态 1
    private Integer carStatus;
    //运行状态
    private Integer runStatus;
    //DCDC状态 1
    private Integer dcdcStatus;
    //档位驱动状态
    private Integer driverStatus;
    //档位制动状态
    private Integer brakingStatus;
    //档位状态
    private Integer gearStatus;
    //运行模式 1
    private Integer runModel;
    //充电状态 1
    private Integer chargeStatus;
    //绝缘电阻 2
    private Integer ir;


    public Float getCarS() {
        return carS;
    }

    public void setCarS(Float carS) {
        this.carS = carS;
    }

    public Float getMile() {
        return mile;
    }

    public void setMile(Float mile) {
        this.mile = mile;
    }

    public Float gettV() {
        return tV;
    }

    public void settV(Float tV) {
        this.tV = tV;
    }

    public Float gettC() {
        return tC;
    }

    public void settC(Float tC) {
        this.tC = tC;
    }

    public Integer getSoc() {
        return soc;
    }

    public void setSoc(Integer soc) {
        this.soc = soc;
    }

    public Integer getDcdcT() {
        return dcdcT;
    }

    public void setDcdcT(Integer dcdcT) {
        this.dcdcT = dcdcT;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }

    public Integer getBraS() {
        return braS;
    }

    public void setBraS(Integer braS) {
        this.braS = braS;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public Integer getDcdcStatus() {
        return dcdcStatus;
    }

    public void setDcdcStatus(Integer dcdcStatus) {
        this.dcdcStatus = dcdcStatus;
    }

    public Integer getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(Integer driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Integer getBrakingStatus() {
        return brakingStatus;
    }

    public void setBrakingStatus(Integer brakingStatus) {
        this.brakingStatus = brakingStatus;
    }

    public Integer getGearStatus() {
        return gearStatus;
    }

    public void setGearStatus(Integer gearStatus) {
        this.gearStatus = gearStatus;
    }

    public Integer getRunModel() {
        return runModel;
    }

    public void setRunModel(Integer runModel) {
        this.runModel = runModel;
    }

    public Integer getChargeStatus() {
        return chargeStatus;
    }

    public void setChargeStatus(Integer chargeStatus) {
        this.chargeStatus = chargeStatus;
    }

    public Integer getIr() {
        return ir;
    }

    public void setIr(Integer ir) {
        this.ir = ir;
    }

    @Override
    public String toString() {
        return "车辆状态：" + carStatus + "\n"
                + "充电状态：" + chargeStatus + "\n"
                + "运行模式：" + runModel + "\n"
                + "车速：" + carS + "\n"
                + "累计里程：" + mile + "\n"
                + "总电压：" + tV + "\n"
                + "总电流：" + tC + "\n"
                + "SOC：" + soc + "\n"
                + "DC/DC状态：" + dcdcStatus + "\n"
                + "档位驱动状态：" + driverStatus + "\n"
                + "档位制动状态：" + brakingStatus + "\n"
                + "档位状态：" + gearStatus + "\n"
                + "绝缘电阻：" + ir + "\n"
                + "加速踏板行程值：" + res + "\n"
                + "制动踏板状态：" + braS + "\n";
    }
}
