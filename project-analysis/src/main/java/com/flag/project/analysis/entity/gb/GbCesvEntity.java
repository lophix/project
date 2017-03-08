package com.flag.project.analysis.entity.gb;

import java.util.List;

/**
 * 可充电储能装置电压数据
 *
 * @author xuj
 * @since 2017-03-06-13:31
 */
public class GbCesvEntity {
    //可充电储能装置子系统号 1
    private Integer cesssc;
    //可充电储能装置电压 2
    private Float cesdv;
    //可充电储能装置电流 2
    private Float cesdc;
    //单体电池总数 2
    private Integer scn;
    //本帧起始电池序号 2
    private Integer tfcn;
    //本帧单体电池总数 1
    private Integer tfscn;
    //单体电池电压
    private List<Float> scvList;

    public Integer getCesssc() {
        return cesssc;
    }

    public void setCesssc(Integer cesssc) {
        this.cesssc = cesssc;
    }

    public Float getCesdv() {
        return cesdv;
    }

    public void setCesdv(Float cesdv) {
        this.cesdv = cesdv;
    }

    public Float getCesdc() {
        return cesdc;
    }

    public void setCesdc(Float cesdc) {
        this.cesdc = cesdc;
    }

    public Integer getScn() {
        return scn;
    }

    public void setScn(Integer scn) {
        this.scn = scn;
    }

    public Integer getTfcn() {
        return tfcn;
    }

    public void setTfcn(Integer tfcn) {
        this.tfcn = tfcn;
    }

    public Integer getTfscn() {
        return tfscn;
    }

    public void setTfscn(Integer tfscn) {
        this.tfscn = tfscn;
    }

    public List<Float> getScvList() {
        return scvList;
    }

    public void setScvList(List<Float> scvList) {
        this.scvList = scvList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (scvList != null) {
            scvList.forEach(scv -> sb.append(String.valueOf(scv)).append(","));
        }
        return "\n可充电储能装置子系统号：" + cesssc + "\n"
                + "可充电储能装置电压：" + cesdv + "\n"
                + "可充电储能装置电流：" + cesdc + "\n"
                + "单体电池总数：" + scn + "\n"
                + "本帧起始电池序号：" + tfcn + "\n"
                + "本帧单体电池总数：" + tfscn + "\n"
                + "单体电池电压：" + sb.toString() + "\n";
    }
}
