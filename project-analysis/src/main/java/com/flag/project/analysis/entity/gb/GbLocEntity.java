package com.flag.project.analysis.entity.gb;

/**
 * 车辆位置
 *
 * @author xuj
 * @version V1.0
 * @since 2017-03-06-10:28
 */
public class GbLocEntity {

    //定位状态 0:有效  1:无效
    private Integer locStatus;
    //纬度类型 0:北纬  1:南纬
    private Integer latType;
    //经度类型 0:东经  1:西经
    private Integer lonType;
    //经度
    private Float lon;
    //纬度
    private Float lat;

    public Integer getLocStatus() {
        return locStatus;
    }

    public void setLocStatus(Integer locStatus) {
        this.locStatus = locStatus;
    }

    public Integer getLatType() {
        return latType;
    }

    public void setLatType(Integer latType) {
        this.latType = latType;
    }

    public Integer getLonType() {
        return lonType;
    }

    public void setLonType(Integer lonType) {
        this.lonType = lonType;
    }

    public Float getLon() {
        return lon;
    }

    public void setLon(Float lon) {
        this.lon = lon;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "定位状态：" + locStatus + "\n"
                + "纬度类型：" + latType + "\n"
                + "经度类型：" + lonType + "\n"
                + "经度：" + lon + "\n"
                + "纬度：" + lat + "\n";
    }
}
