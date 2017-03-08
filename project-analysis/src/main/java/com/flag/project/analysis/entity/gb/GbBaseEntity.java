package com.flag.project.analysis.entity.gb;

/**
 * 新国标基础实例
 *
 * @author xuj
 * @since 2017-03-02-15:11
 */
public class GbBaseEntity {
    /**
     * 服务器时间
     */
    private Long sTime;
    /**
     * 采集时间
     */
    private Long time;

    public Long getsTime() {
        return sTime;
    }

    public void setsTime(Long sTime) {
        this.sTime = sTime;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
