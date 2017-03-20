package com.flag.project.analysis.entity.gb;

import java.util.List;

/**
 * 报警数据
 *
 * @author xuj
 * @version V1.0
 * @since 2017-03-06-15:15
 */
public class GbAlarmEntity {

    //最高报警等级
    private Integer hal;


    /**通用报警标志 1:报警， 0:正常*/
    //温度差异报警
    private Integer tda;
    //电池高温报警
    private Integer cha;
    //车载储能装置类型过压报警
    private Integer eshp;
    //车载储能装置类型欠压报警
    private Integer eslp;
    //SOC低报警
    private Integer sl;
    //单体电池过压报警
    private Integer schp;
    //单体电池欠压报警
    private Integer sclp;
    //SOC过高报警
    private Integer sh;
    //SOC跳变报警
    private Integer sj;
    //可充电储能系统不匹配报警
    private Integer cesm;
    //电池单体一致性差报警
    private Integer csc;
    //绝缘报警
    private Integer ia;
    //DCDC温度报警
    private Integer dcdct;
    //制动系统报警
    private Integer ba;
    //DCDC状态报警
    private Integer dcdcs;
    //驱动电机控制器温度报警
    private Integer dmct;
    //高压互锁状态报警
    private Integer hpl;
    //驱动电机温度报警
    private Integer dmt;
    //车载储能装置类型过充报警
    private Integer esto;

    /**可充电储能装置故障*/
    //可充电储能装置故障总数
    private Integer cesfn;
    //可充电储能装置故障代码列表
    private List<Integer> cesfcList;

    /**驱动电机故障*/
    //驱动电机故障总数
    private Integer dmfn;
    //驱动电机故障代码列表
    private List<Integer> dmfcList;

    /**发动机故障*/
    //发动机故障总数
    private Integer efn;
    //发动机故障列表
    private List<Integer> efcList;

    /**其他故障*/
    //其他故障总数
    private Integer ofn;
    //其他故障代码列表
    private List<Integer> ofcList;

    public Integer getHal() {
        return hal;
    }

    public void setHal(Integer hal) {
        this.hal = hal;
    }

    public Integer getTda() {
        return tda;
    }

    public void setTda(Integer tda) {
        this.tda = tda;
    }

    public Integer getCha() {
        return cha;
    }

    public void setCha(Integer cha) {
        this.cha = cha;
    }

    public Integer getEshp() {
        return eshp;
    }

    public void setEshp(Integer eshp) {
        this.eshp = eshp;
    }

    public Integer getEslp() {
        return eslp;
    }

    public void setEslp(Integer eslp) {
        this.eslp = eslp;
    }

    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }

    public Integer getSchp() {
        return schp;
    }

    public void setSchp(Integer schp) {
        this.schp = schp;
    }

    public Integer getSclp() {
        return sclp;
    }

    public void setSclp(Integer sclp) {
        this.sclp = sclp;
    }

    public Integer getSh() {
        return sh;
    }

    public void setSh(Integer sh) {
        this.sh = sh;
    }

    public Integer getSj() {
        return sj;
    }

    public void setSj(Integer sj) {
        this.sj = sj;
    }

    public Integer getCesm() {
        return cesm;
    }

    public void setCesm(Integer cesm) {
        this.cesm = cesm;
    }

    public Integer getCsc() {
        return csc;
    }

    public void setCsc(Integer csc) {
        this.csc = csc;
    }

    public Integer getIa() {
        return ia;
    }

    public void setIa(Integer ia) {
        this.ia = ia;
    }

    public Integer getDcdct() {
        return dcdct;
    }

    public void setDcdct(Integer dcdct) {
        this.dcdct = dcdct;
    }

    public Integer getBa() {
        return ba;
    }

    public void setBa(Integer ba) {
        this.ba = ba;
    }

    public Integer getDcdcs() {
        return dcdcs;
    }

    public void setDcdcs(Integer dcdcs) {
        this.dcdcs = dcdcs;
    }

    public Integer getDmct() {
        return dmct;
    }

    public void setDmct(Integer dmct) {
        this.dmct = dmct;
    }

    public Integer getHpl() {
        return hpl;
    }

    public void setHpl(Integer hpl) {
        this.hpl = hpl;
    }

    public Integer getDmt() {
        return dmt;
    }

    public void setDmt(Integer dmt) {
        this.dmt = dmt;
    }

    public Integer getEsto() {
        return esto;
    }

    public void setEsto(Integer esto) {
        this.esto = esto;
    }

    public Integer getCesfn() {
        return cesfn;
    }

    public void setCesfn(Integer cesfn) {
        this.cesfn = cesfn;
    }

    public List<Integer> getCesfcList() {
        return cesfcList;
    }

    public void setCesfcList(List<Integer> cesfcList) {
        this.cesfcList = cesfcList;
    }

    public Integer getDmfn() {
        return dmfn;
    }

    public void setDmfn(Integer dmfn) {
        this.dmfn = dmfn;
    }

    public List<Integer> getDmfcList() {
        return dmfcList;
    }

    public void setDmfcList(List<Integer> dmfcList) {
        this.dmfcList = dmfcList;
    }

    public Integer getEfn() {
        return efn;
    }

    public void setEfn(Integer efn) {
        this.efn = efn;
    }

    public List<Integer> getEfcList() {
        return efcList;
    }

    public void setEfcList(List<Integer> efcList) {
        this.efcList = efcList;
    }

    public Integer getOfn() {
        return ofn;
    }

    public void setOfn(Integer ofn) {
        this.ofn = ofn;
    }

    public List<Integer> getOfcList() {
        return ofcList;
    }

    public void setOfcList(List<Integer> ofcList) {
        this.ofcList = ofcList;
    }
}
