package com.flag.project.analysis.entity.gb;

/**
 * 极值数据
 *
 * @author xuj
 * @version V1.0
 * @since 2017-03-06-11:12
 */
public class GbExtEntity {
    //最高电压电池子系统号 1
    private Integer hvcssn;
    //最高电压电池单体代号 1
    private Integer hvcsc;
    //电池单体电压最高值 2
    private Float csvhv;
    //最低电压电池子系统号 1
    private Integer lvcssn;
    //最低电压电池单体代号 1
    private Integer lvcsc;
    //电池单体电压最低值 2
    private Float csvlv;
    //最高温度子系统号 1
    private Integer htssn;
    //最高温度探针序号 1
    private Integer htpn;
    //最高温度值 1
    private Integer htv;
    //最低温度子系统号 1
    private Integer ltssn;
    //最低温度探针序号 1
    private Integer ltpn;
    //最低温度值 1
    private Integer ltv;

    public Integer getHvcssn() {
        return hvcssn;
    }

    public void setHvcssn(Integer hvcssn) {
        this.hvcssn = hvcssn;
    }

    public Integer getHvcsc() {
        return hvcsc;
    }

    public void setHvcsc(Integer hvcsc) {
        this.hvcsc = hvcsc;
    }

    public Float getCsvhv() {
        return csvhv;
    }

    public void setCsvhv(Float csvhv) {
        this.csvhv = csvhv;
    }

    public Integer getLvcssn() {
        return lvcssn;
    }

    public void setLvcssn(Integer lvcssn) {
        this.lvcssn = lvcssn;
    }

    public Integer getLvcsc() {
        return lvcsc;
    }

    public void setLvcsc(Integer lvcsc) {
        this.lvcsc = lvcsc;
    }

    public Float getCsvlv() {
        return csvlv;
    }

    public void setCsvlv(Float csvlv) {
        this.csvlv = csvlv;
    }

    public Integer getHtssn() {
        return htssn;
    }

    public void setHtssn(Integer htssn) {
        this.htssn = htssn;
    }

    public Integer getHtpn() {
        return htpn;
    }

    public void setHtpn(Integer htpn) {
        this.htpn = htpn;
    }

    public Integer getHtv() {
        return htv;
    }

    public void setHtv(Integer htv) {
        this.htv = htv;
    }

    public Integer getLtssn() {
        return ltssn;
    }

    public void setLtssn(Integer ltssn) {
        this.ltssn = ltssn;
    }

    public Integer getLtpn() {
        return ltpn;
    }

    public void setLtpn(Integer ltpn) {
        this.ltpn = ltpn;
    }

    public Integer getLtv() {
        return ltv;
    }

    public void setLtv(Integer ltv) {
        this.ltv = ltv;
    }

    @Override
    public String toString() {
        return "最高电压电池子系统号：" + hvcssn + "\n"
                + "最高电压电池单体代号：" + hvcsc + "\n"
                + "电池单体电压最高值：" + csvhv + "\n"
                + "最低电压电池子系统号：" + lvcssn + "\n"
                + "最低电压电池单体代号：" + lvcsc + "\n"
                + "电池单体电压最低值：" + csvlv + "\n"
                + "最高温度子系统号：" + htssn + "\n"
                + "最高温度探针序号：" + htpn + "\n"
                + "最高温度值：" + htv + "\n"
                + "最低温度子系统号：" + ltssn + "\n"
                + "最低温度探针序号：" + ltpn + "\n"
                + "最低温度值：" + ltv + "\n";
    }
}
