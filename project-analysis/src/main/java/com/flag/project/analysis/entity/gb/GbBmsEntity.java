package com.flag.project.analysis.entity.gb;

import java.util.List;

/**
 * BMS组件
 *
 * @author xuj
 * @version V1.0
 * @since 2017-03-06-9:37
 */
public class GbBmsEntity extends GbBaseEntity {
    /**
     * 燃料电池数据
     */
    //燃料电池电压 2
    private Float fv;
    //燃料电池电流 2
    private Float fc;
    //燃料消耗率 2
    private Float fur;
    //燃料电池温度探针总数 2
    private Integer ftpn;
    //探针温度值
    private List<Integer> probTempValue;
    //氢系统中最高温度 2
    private Float hsht;
    //氢系统中最高温度探针代号 1
    private Integer hshtpc;
    //氢气最高浓度 2
    private Integer hhc;
    //氢气最高浓度传感器代号 1
    private Integer hhcsc;
    //氢气最高压力 2
    private Float hhp;
    //氢气最高压力传感器代号 1
    private Integer hhpsc;
    //高压DC/DC状态 1
    private Integer hpdcdcs;

    /**
     * 可充电储能装置电压数据
     */
    //可充电储能子系统个数 1
    private Integer cesssn;
    //可充电储能子系统电压信息列表
    private List<GbCesvEntity> cesvList;

    /**
     * 可充电储能子系统温度数据
     private Integer cesssn;
     */
    //可充电储能子系统温度信息列表
    private List<GbCestEntity> cestList;

    public Float getFv() {
        return fv;
    }

    public void setFv(Float fv) {
        this.fv = fv;
    }

    public Float getFc() {
        return fc;
    }

    public void setFc(Float fc) {
        this.fc = fc;
    }

    public Float getFur() {
        return fur;
    }

    public void setFur(Float fur) {
        this.fur = fur;
    }

    public Integer getFtpn() {
        return ftpn;
    }

    public void setFtpn(Integer ftpn) {
        this.ftpn = ftpn;
    }

    public List<Integer> getProbTempValue() {
        return probTempValue;
    }

    public void setProbTempValue(List<Integer> probTempValue) {
        this.probTempValue = probTempValue;
    }

    public Float getHsht() {
        return hsht;
    }

    public void setHsht(Float hsht) {
        this.hsht = hsht;
    }

    public Integer getHshtpc() {
        return hshtpc;
    }

    public void setHshtpc(Integer hshtpc) {
        this.hshtpc = hshtpc;
    }

    public Integer getHhc() {
        return hhc;
    }

    public void setHhc(Integer hhc) {
        this.hhc = hhc;
    }

    public Integer getHhcsc() {
        return hhcsc;
    }

    public void setHhcsc(Integer hhcsc) {
        this.hhcsc = hhcsc;
    }

    public Float getHhp() {
        return hhp;
    }

    public void setHhp(Float hhp) {
        this.hhp = hhp;
    }

    public Integer getHhpsc() {
        return hhpsc;
    }

    public void setHhpsc(Integer hhpsc) {
        this.hhpsc = hhpsc;
    }

    public Integer getHpdcdcs() {
        return hpdcdcs;
    }

    public void setHpdcdcs(Integer hpdcdcs) {
        this.hpdcdcs = hpdcdcs;
    }

    public Integer getCesssn() {
        return cesssn;
    }

    public void setCesssn(Integer cesssn) {
        this.cesssn = cesssn;
    }

    public List<GbCesvEntity> getCesvList() {
        return cesvList;
    }

    public void setCesvList(List<GbCesvEntity> cesvList) {
        this.cesvList = cesvList;
    }

    public List<GbCestEntity> getCestList() {
        return cestList;
    }

    public void setCestList(List<GbCestEntity> cestList) {
        this.cestList = cestList;
    }

    @Override
    public String toString() {
        StringBuilder ptv = new StringBuilder();
        if (probTempValue != null) {
            probTempValue.forEach(pt -> ptv.append(String.valueOf(pt)).append(","));
        }
        StringBuilder cesvs = new StringBuilder();
        if (cesvList != null) {
            cesvList.forEach(cesv -> cesvs.append(cesv.toString()));
        }
        StringBuilder cests = new StringBuilder();
        if (cestList != null) {
            cestList.forEach(cest -> cests.append(cest.toString()));
        }
        return "燃料电池电压：" + fv + "\n"
                + "燃料电池电流：" + fc + "\n"
                + "燃料消耗率：" + fur + "\n"
                + "燃料电池温度探针总数：" + ftpn + "\n"
                + "探针温度值：" + ptv.toString() + "\n"
                + "氢系统中最高温度：" + hshtpc + "\n"
                + "氢系统中最高温度探针代号：" + hshtpc + "\n"
                + "氢气最高浓度：" + hhc + "\n"
                + "氢气最高浓度传感器代号：" + hhcsc + "\n"
                + "氢气最高压力：" + hhp + "\n"
                + "氢气最高压力传感器代号：" + hhpsc + "\n"
                + "高压DC/DC状态：" + hpdcdcs + "\n"
                + "可充电储能子系统个数：" + cesssn + "\n"
                + "可充电储能子系统电压信息列表：\n" + cesvs.toString() + "\n"
                + "可充电储能子系统温度信息列表：\n" + cests.toString() + "\n";
    }
}
