package com.flag.project.analysis.entity.gb;

import java.util.List;

/**
 * 可充电储能爽直温度数据
 *
 * @author xuj
 * @version V1.0
 * @since 2017-03-06-13:57
 */
public class GbCestEntity {

    //可充电储能子系统号 1
    private Integer cesssc;
    //可充电储能温度探针个数 2
    private Integer cestpn;
    //可充电储能子系统各温度探针检测到的温度值
    private List<Integer> tpcvList;

    public Integer getCesssc() {
        return cesssc;
    }

    public void setCesssc(Integer cesssc) {
        this.cesssc = cesssc;
    }

    public Integer getCestpn() {
        return cestpn;
    }

    public void setCestpn(Integer cestpn) {
        this.cestpn = cestpn;
    }

    public List<Integer> getTpcvList() {
        return tpcvList;
    }

    public void setTpcvList(List<Integer> tpcvList) {
        this.tpcvList = tpcvList;
    }

    @Override
    public String toString() {
        StringBuilder tpcv = new StringBuilder();
        if (tpcvList != null) {
            tpcvList.forEach(tcp -> tpcv.append(String.valueOf(tcp)).append(","));
        }
        return "\n可充电储能子系统号：" + cesssc + "\n"
                + "可充电储能温度探针个数：" + cestpn + "\n"
                + "可充电储能子系统个温度探针检测到的温度值：" + tpcv.toString();
    }
}
