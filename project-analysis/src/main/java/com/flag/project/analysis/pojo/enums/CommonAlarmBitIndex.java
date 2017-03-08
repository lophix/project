package com.flag.project.analysis.pojo.enums;

/**
 * 通用报警标志枚举类，记录各报警在二进制里的下标位置
 * 所有二进制码中， 1：报警  0：正常
 *
 * @author xuj
 * @version V1.0
 * @since 2017-03-03-10:50
 */
public enum CommonAlarmBitIndex {

    TEMPERATURE(0, "温度差异报警", "tda"),
    CELL_HIGH_TEM(1, "电池高温报警", "cha"),
    ENERGY_STORAGE_HIGH_PRESSURE(2, "车载储能装置类型过压报警", "eshp"),
    ENERGY_STORAGE_LOW_PRESSURE(3, "车载储能装置类型欠压报警", "eslp"),
    SOC_LOW(4, "SOC低报警", "sl"),
    SIMPLE_CELL_HIGH_PRESSURE(5, "单体电池过压报警", "schp"),
    SIMPLE_CELL_LOW_PRESSURE(6, "单体电池欠压报警", "sclp"),
    SOC_HIGH(7, "SOC过高报警", "sh"),
    SOC_JUMP(8, "SOC跳变报警", "sj"),
    CHARGEABLE_ENERGY_STORAGE_MISMATCH(9, "可充电储能系统不匹配", "cesm"),
    CELL_SIMPLE_CONSISTENCY(10, "电池单体一致性差报警", "csc"),
    INSULATION(11, "绝缘报警", "ia"),
    DCDC_TEM(12, "DC/DC温度报警", "dcdct"),
    BRAKING(13, "制动系统报警", "ba"),
    DCDC_STATE(14, "DC/DC状态报警", "dcdcs"),
    DRIVE_MOTOR_CONTROL_TEM(15, "驱动电机控制器温度报警", "dmct"),
    HIGH_PRESSURE_LOCK(16, "高压互锁状态报警", "hpl"),
    DRIVE_MOTOR_TEM(17, "驱动电机温度报警", "dmt"),
    ENERGY_STORAGE_TYPE_OVERCHARGE(18, "车载储能装置类型过充", "esto");

    private int index;
    private String comment;
    private String param;

    CommonAlarmBitIndex(int index, String comment, String param) {
        this.index = index;
        this.comment = comment;
        this.param = param;
    }

    public int getIndex() {
        return index;
    }

    public String getComment() {
        return comment;
    }

    public String getParam() {
        return param;
    }
}
