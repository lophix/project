package com.flag.project.analysis.entity;

import java.util.Arrays;

/**
 * @Autor sunbg
 * @Date 2016/11/24
 * @Decription
 */
public class ProtocolObject {
    /**
     * 绑定码
     */
    private String uqBusinessId;

    private int key ; //1 808透传的ＣＡＮ信息 ； 2~10
    private int  protocolType; //标识JTT8080和新能汽车通信标准  0 808 ，1，GM06 2，GT06

    private Integer carType; //电动车的型号 1，EQ. 2，比亚迪E5
    //private long receiveTimeStamp ; //CAN 总线数据接收时间
    private long  currTimeStamp; //当前系统时间
    /**
     * 报文体字节数组
     */
    private byte[] attrBytes;

    //业务扩展key
    private Integer extKey;

    /**
     * topicId 对应业务和topic
     */
    /*private MQKeyIdentify mqKeyIdentify;*/

    public String getUqBusinessId() {
        return uqBusinessId;
    }

    public void setUqBusinessId(String uqBusinessId) {
        this.uqBusinessId = uqBusinessId;
    }

   /* public MQKeyIdentify getMqKeyIdentify() {
        return mqKeyIdentify;
    }

    public void setMqKeyIdentify(MQKeyIdentify mqKeyIdentify) {
        this.mqKeyIdentify = mqKeyIdentify;
    }*/

    public byte[] getAttrBytes() {
        return attrBytes;
    }

    public void setAttrBytes(byte[] attrBytes) {
        this.attrBytes = attrBytes;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public long getCurrTimeStamp() {
        return currTimeStamp;
    }

    public void setCurrTimeStamp(long currTimeStamp) {
        this.currTimeStamp = currTimeStamp;
    }

    public Integer getExtKey() {
        return extKey;
    }

    public void setExtKey(Integer extKey) {
        this.extKey = extKey;
    }

    @Override
    public String toString() {
        return "ProtocolObject{" +
                "uqBusinessId='" + uqBusinessId + '\'' +
                ", key=" + key +
                ", protocolType=" + protocolType +
                ", carType=" + carType +
                ", currTimeStamp=" + currTimeStamp +
                ", attrBytes=" + Arrays.toString(attrBytes) +
                '}';
    }
}
