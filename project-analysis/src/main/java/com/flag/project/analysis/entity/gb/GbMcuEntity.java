package com.flag.project.analysis.entity.gb;

import java.util.Collections;
import java.util.List;

/**
 * 新国标mcu封装类
 *
 * @author xuj
 * @version V1.0
 * @since 2017-03-03-17:07
 */
public class GbMcuEntity extends GbBaseEntity {
    //电机个数 1
    private Integer mNum;
    //电机
    private List<GbDmEntity> dms;
    //发动机状态 1
    private Integer es;
    //曲轴转速 2
    private Integer cs;
    //燃料消耗率 2
    private Float fcr;

    public Integer getmNum() {
        return mNum;
    }

    public void setmNum(Integer mNum) {
        this.mNum = mNum;
    }

    public List<GbDmEntity> getDms() {
        return dms;
    }

    public void setDms(List<GbDmEntity> dms) {
        this.dms = dms;
    }

    public Integer getEs() {
        return es;
    }

    public void setEs(Integer es) {
        this.es = es;
    }

    public Integer getCs() {
        return cs;
    }

    public void setCs(Integer cs) {
        this.cs = cs;
    }

    public Float getFcr() {
        return fcr;
    }

    public void setFcr(Float fcr) {
        this.fcr = fcr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (dms != null) {
            dms.stream().forEach(dm -> sb.append(dm.toString()));
        }
        return "电机个数：" + mNum + "\n"
                + "电机：\n" + sb.toString()
                + "发动机状态：" + es + "\n"
                + "曲轴转速：" + cs + "\n"
                + "燃料消耗率：" + fcr + "\n";
    }
}
