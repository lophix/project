package com.flag.project.analysis.entity.gb;

/**
 * 新国标can数据封装类
 *
 * @author xuj
 * @version V1.0
 * @since 2017-03-03-16:59
 */
public class GbCanEntity {

    private GbVcuEntity vcu;
    private GbMcuEntity mcu;
    private GbBmsEntity bms;
    private GbLocEntity loc;
    private GbExtEntity ext;


    public GbVcuEntity getVcu() {
        return vcu;
    }

    public void setVcu(GbVcuEntity vcu) {
        this.vcu = vcu;
    }

    public GbMcuEntity getMcu() {
        return mcu;
    }

    public void setMcu(GbMcuEntity mcu) {
        this.mcu = mcu;
    }

    public GbBmsEntity getBms() {
        return bms;
    }

    public void setBms(GbBmsEntity bms) {
        this.bms = bms;
    }

    public GbLocEntity getLoc() {
        return loc;
    }

    public void setLoc(GbLocEntity loc) {
        this.loc = loc;
    }

    public GbExtEntity getExt() {
        return ext;
    }

    public void setExt(GbExtEntity ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "vcu：\n" + vcu + "\n"
                + "mcu: \n" + mcu + "\n"
                + "bms: \n" + bms + "\n"
                + "loc: \n" + loc + "\n"
                + "ext: \n" + ext + "\n";
    }
}
