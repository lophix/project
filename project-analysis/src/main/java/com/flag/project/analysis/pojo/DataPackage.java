package com.flag.project.analysis.pojo;

/**
 * this is a data package
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 15:18
 */
public class DataPackage {
    /**
     * 命令标识
     */
    private byte cmm;
    /**
     * 应答标识
     */
    private byte reply;
    /**
     * 车辆识别码
     */
    private String carId;
    /**
     * 数据加密方式,byte 0x01 不加密， 0x02 RSA, 0x03 AES128
     */
    private byte encryptType;
    /**
     * 数据单元长度 0~66531
     */
    private short dataLength;
    /**
     * 数据单元
     */
    private byte[] data;
    /**
     * 效验码
     */
    private byte checkCode;
    /**
     * 是否为脏数据
     */
    private boolean isDirtyData = false;

    public byte getCmm() {
        return cmm;
    }

    public void setCmm(byte cmm) {
        this.cmm = cmm;
    }

    public byte getReply() {
        return reply;
    }

    public void setReply(byte reply) {
        this.reply = reply;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public byte getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(byte encryptType) {
        this.encryptType = encryptType;
    }

    public short getDataLength() {
        return dataLength;
    }

    public void setDataLength(short dataLength) {
        this.dataLength = dataLength;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(byte checkCode) {
        this.checkCode = checkCode;
    }

    public boolean isDirtyData() {
        return isDirtyData;
    }

    public void setDirtyData(boolean dirtyData) {
        isDirtyData = dirtyData;
    }

    @Override
    public String toString() {
        return "command is " + cmm + "\n"
                + "reply is " + reply + "\n"
                + "car id is " + carId + "\n"
                + "encrypt type is " + encryptType + "\n"
                + "data length is " + dataLength + "\n"
                + "data is " + data + "\n"
                + "check code is " + checkCode + "\n"
                + "is dirty data " + isDirtyData;
    }
}
