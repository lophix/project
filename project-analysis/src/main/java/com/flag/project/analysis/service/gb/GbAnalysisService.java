package com.flag.project.analysis.service.gb;

import com.flag.project.analysis.entity.ProtocolObject;
import com.flag.project.analysis.entity.gb.*;
import com.flag.project.analysis.pojo.enums.CommonAlarmBitIndex;
import com.flag.project.analysis.service.IAnalysisGb;
import com.flag.project.analysis.util.ByteAnalysisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 新国标解析服务实现类
 *
 * @author xuj
 * @since 2017-03-02-15:09
 */
@Service("gbAnalysisService")
public class GbAnalysisService implements IAnalysisGb {

    private static final Logger log = LogManager.getLogger(GbAnalysisService.class);

    @Override
    public GbCanEntity analysisFrames(ProtocolObject protocolObject) {
        GbCanEntity canEntity = new GbCanEntity();
        if (2 == protocolObject.getKey()) {
            doAnalysis(protocolObject, canEntity);
        }

        if (3 == protocolObject.getKey()) {

        }
        return canEntity;
    }

    /**
     * 解析方法
     *
     * @param protocolObject 协议对象
     * @param canEntity      基础对象，组装结果
     */
    private void doAnalysis(ProtocolObject protocolObject, GbCanEntity canEntity) {
        String dateStr = getDateStr(protocolObject.getAttrBytes(), 0, 6);
        System.out.println(dateStr);
//            canEntity.setsTime(ByteAnalysisUtil.yyMMddHHmmss.parse(dateStr).getTime());
        doNextAnalysis(protocolObject.getAttrBytes(), 6, canEntity);
    }

    /**
     * 解析方法，各类型数据解析分发方法
     *
     * @param data      数据源
     * @param offset    偏移量
     * @param canEntity 解析后对象
     */
    private void doNextAnalysis(byte[] data, int offset, GbCanEntity canEntity) {
        if (offset >= data.length - 1) {
            log.info("data analysis finish");
            return;
        }
        int code = ByteAnalysisUtil.bytes2Int(data, offset++, 1);

        switch (code) {
            case 0x01:
                doTotalCarAnalysis(data, offset, canEntity);
                break;
            case 0x02:
                doDriveMotorAnalysis(data, offset, canEntity);
                break;
            case 0x03:
                doFuelCellAnalysis(data, offset, canEntity);
                break;
            case 0x04:
                doEngineAnalysis(data, offset, canEntity);
                break;
            case 0x05:
                doGPSAnalysis(data, offset, canEntity);
                break;
            case 0x06:
                doExtremeAnalysis(data, offset, canEntity);
                break;
            case 0x07:
                doAlarmAnalysis(data, offset, canEntity);
                break;
            case 0x08:
                doChargeableEnergyStorageVoltageAnalysis(data, offset, canEntity);
                break;
            case 0x09:
                doChargeableEnergyStorageTemAnalysis(data, offset, canEntity);
                break;
            default:
                log.warn("unknown data type");
        }
    }

    /**
     * 整车数据解析方法
     *
     * @param data      原始数据
     * @param offset    偏移量
     * @param canEntity 解析后封装对象
     */
    private void doTotalCarAnalysis(byte[] data, int offset, GbCanEntity canEntity) {
        GbVcuEntity vcu = canEntity.getVcu();

        if (vcu == null) {
            vcu = new GbVcuEntity();
        }

        //车辆状态 1:启动 2：熄火 3：其他 254：异常 255：无效
        int carState = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        vcu.setCarStatus(carState);
        //充电状态 1：停车充电 2：行驶充电 3：未充电状态 4：充电完成 254：异常 255：无效
        int chargeState = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        vcu.setChargeStatus(chargeState);
        //运行模式
        int workModel = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        vcu.setRunModel(workModel);
        //车速 65534:异常 65535：无效
        float speed = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f;
        offset = offset + 2;
        vcu.setCarS(speed);
        //累计里程
        float mileage = ByteAnalysisUtil.bytes2Int(data, offset, 4) * 0.1f;
        offset = offset + 4;
        vcu.setMile(mileage);
        //总电压
        float totalVoltage = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f;
        offset = offset + 2;
        vcu.settV(totalVoltage);
        //总电流
        float totalA = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f - 1000;
        offset = offset + 2;
        vcu.settC(totalA);
        //SOC
        int soc = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        vcu.setSoc(soc);
        //dc-dc状态
        int dcdc = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        vcu.setDcdcStatus(dcdc);
        //档位 offset++
        //档位驱动状态 1：有驱动力 0：无驱动力
        int driverStatus = ByteAnalysisUtil.byte2BitIntReverse(data[offset], 2);
        vcu.setDriverStatus(driverStatus);
        //档位制动状态 1：有制动力 0：无制动力
        int brakingStatus = ByteAnalysisUtil.byte2BitIntReverse(data[offset], 3);
        vcu.setBrakingStatus(brakingStatus);
        String pear = ByteAnalysisUtil.byte2BitStr(data[offset++]).substring(4);
        vcu.setGearStatus(ByteAnalysisUtil.bit2Int(pear));
        //绝缘电阻
        int ir = ByteAnalysisUtil.bytes2Int(data, offset, 2);
        offset = offset + 2;
        vcu.setIr(ir);
        //加速踏板行程值
        int apm = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        vcu.setRes(apm);
        //制动踏板状态 0:制动关 101：制动有效
        int bpm = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        vcu.setBraS(bpm);
        canEntity.setVcu(vcu);
        doNextAnalysis(data, offset, canEntity);
    }

    /**
     * 驱动机电数据解析方法
     *
     * @param data      数据源
     * @param offset    偏移量
     * @param canEntity 解析后封装对象
     */
    private void doDriveMotorAnalysis(byte[] data, int offset, GbCanEntity canEntity) {
        GbMcuEntity mcu = canEntity.getMcu();
        if (mcu == null) {
            mcu = new GbMcuEntity();
        }

        int dmSum = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        mcu.setmNum(dmSum);
        List<GbDmEntity> dms = mcu.getDms();
        if (dms == null) {
            dms = new ArrayList<>(dmSum);
        }

        GbDmEntity dm;
        for (int i = 0; i < dmSum; i++) {
            dm = new GbDmEntity();
            //驱动电机序号
            int dmNum = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
            dm.setmSnum(dmNum);
            //驱动电机状态
            int dmState = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
            dm.setMotorStatus(dmState);
            //驱动电机控制器温度
            int dmControlTem = ByteAnalysisUtil.bytes2Int(data, offset++, 1) - 40;
            dm.setmCT(dmControlTem);
            //驱动电机转速
            int dmSpeed = ByteAnalysisUtil.bytes2Int(data, offset, 2) - 20000;
            offset = offset + 2;
            dm.setmS(dmSpeed);
            //驱动电机转矩
            float dmTorque = (ByteAnalysisUtil.bytes2Int(data, offset, 2) - 20000) * 0.1f;
            offset += 2;
            dm.setmR(dmTorque);
            //驱动电机温度
            int dmTem = ByteAnalysisUtil.bytes2Int(data, offset++, 1) - 40;
            dm.setmT(dmTem);
            //电机控制器输入电压
            float dmControlInV = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f;
            offset += 2;
            dm.setmV(dmControlInV);
            //电机控制器直流母线电流
            float dmdcbc = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f - 1000;
            offset += 2;
            dm.setmC(dmdcbc);
            dms.add(dm);
        }
        mcu.setDms(dms);
        canEntity.setMcu(mcu);
        doNextAnalysis(data, offset, canEntity);
    }

    /**
     * 燃料电池数据解析方法
     *
     * @param data      数据源
     * @param offset    偏移量
     * @param canEntity 解析后封装数据
     */
    private void doFuelCellAnalysis(byte[] data, int offset, GbCanEntity canEntity) {
        GbBmsEntity bms = canEntity.getBms();
        if (bms == null) {
            bms = new GbBmsEntity();
        }

        //燃料电池电压
        float fcv = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f;
        offset += 2;
        bms.setFv(fcv);
        //燃料电池电流
        float fca = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f;
        offset += 2;
        bms.setFc(fca);
        //燃料消耗率
        float fcr = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.01f;
        offset += 2;
        bms.setFur(fcr);
        //燃料电池温度探针总数
        int fctpn = ByteAnalysisUtil.bytes2Int(data, offset, 2);
        offset += 2;
        bms.setFtpn(fctpn);
        List<Integer> fts = new ArrayList<>(fctpn);
        for (int i = 0; i < fctpn; i++) {
            //探针温度值
            int pt = ByteAnalysisUtil.bytes2Int(data, offset++, 2) - 40;
            fts.add(pt);
        }
        bms.setProbTempValue(fts);
        //氢系统中最高温度
        float hsht = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f - 40;
        offset += 2;
        bms.setHsht(hsht);
        //氢系统中最高温度探针代号
        int hshtpc = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        bms.setHshtpc(hshtpc);
        //氢气最高浓度
        int hhc = ByteAnalysisUtil.bytes2Int(data, offset, 2);
        offset += 2;
        bms.setHhc(hhc);
        //氢气最高浓度传感器代号
        int hhcsc = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        bms.setHhcsc(hhcsc);
        //氢气最高压力
        float hhp = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f;
        offset += 2;
        bms.setHhp(hhp);
        //氢气最高压力传感器代号
        int hhpsc = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        bms.setHhpsc(hhpsc);
        //高压DC/DC状态
        int hpdcdcs = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        bms.setHpdcdcs(hpdcdcs);
        canEntity.setBms(bms);
        doNextAnalysis(data, offset, canEntity);
    }

    /**
     * 发动机数据解析方法
     *
     * @param data      数据源
     * @param offset    偏移量
     * @param canEntity 解析后封装对象
     */
    private void doEngineAnalysis(byte[] data, int offset, GbCanEntity canEntity) {
        GbMcuEntity mcu = canEntity.getMcu();
        if (mcu == null) {
            mcu = new GbMcuEntity();
        }

        //发动机状态
        int es = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        mcu.setEs(es);
        //曲轴转速
        int cs = ByteAnalysisUtil.bytes2Int(data, offset, 2);
        offset += 2;
        mcu.setCs(cs);
        //燃料消耗率
        float fcr = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.01f;
        offset += 2;
        mcu.setFcr(fcr);
        canEntity.setMcu(mcu);
        doNextAnalysis(data, offset, canEntity);
    }

    /**
     * 车辆位置数据解析访法
     *
     * @param data      数据源
     * @param offset    偏移量
     * @param canEntity 解析后封装对象
     */
    private void doGPSAnalysis(byte[] data, int offset, GbCanEntity canEntity) {
        GbLocEntity loc = canEntity.getLoc();
        if (loc == null) {
            loc = new GbLocEntity();
        }

        //定位状态 0:有效  1:无效
        int isLoc = ByteAnalysisUtil.byte2BitIntReverse(data[offset], 0);
        loc.setLocStatus(isLoc);
        //纬度类型 0:北纬  1:南纬
        int latitudeType = ByteAnalysisUtil.byte2BitIntReverse(data[offset], 1);
        loc.setLatType(latitudeType);
        //经度类型 0:东经  1:西经
        int longitudeType = ByteAnalysisUtil.byte2BitIntReverse(data[offset++], 2);
        loc.setLonType(longitudeType);
        //经度
        float longitude = ByteAnalysisUtil.bytes2Int(data, offset, 4) * 0.000001f;
        offset += 4;
        loc.setLon(longitude);
        //纬度
        float latitude = ByteAnalysisUtil.bytes2Int(data, offset, 4) * 0.000001f;
        offset += 4;
        loc.setLat(latitude);
        canEntity.setLoc(loc);
        doNextAnalysis(data, offset, canEntity);
    }

    /**
     * 极值数据解析方法
     *
     * @param data      数据源
     * @param offset    偏移量
     * @param canEntity 解析后封装对象
     */
    private void doExtremeAnalysis(byte[] data, int offset, GbCanEntity canEntity) {
        GbExtEntity ext = canEntity.getExt();
        if (ext == null) {
            ext = new GbExtEntity();
        }

        //最高电压电池子系统号
        int hvcssn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        ext.setHvcssn(hvcssn);
        //最高电压电池单体代号
        int hvcsc = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        ext.setHvcsc(hvcsc);
        //电池单体电压最高值
        float csvhv = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.001f;
        offset += 2;
        ext.setCsvhv(csvhv);
        //最低电压电池子系统号
        int lvcssn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        ext.setLvcssn(lvcssn);
        //最低电压电池单体代号
        int lvcsc = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        ext.setLvcsc(lvcsc);
        //电池单体电压最低值
        float csvlv = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.001f;
        offset += 2;
        ext.setCsvlv(csvlv);
        //最高温度子系统号
        int htssn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        ext.setHtssn(htssn);
        //最高温度探针序号
        int htpn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        ext.setHtpn(htpn);
        //最高温度值
        int htv = ByteAnalysisUtil.bytes2Int(data, offset++, 1) - 40;
        ext.setHtv(htv);
        //最低温度子系统号
        int ltssn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        ext.setLtssn(ltssn);
        //最低温度探针序号
        int ltpn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        ext.setLtpn(ltpn);
        //最低温度值
        int ltv = ByteAnalysisUtil.bytes2Int(data, offset++, 1) - 40;
        ext.setLtv(ltv);
        canEntity.setExt(ext);
        doNextAnalysis(data, offset, canEntity);
    }

    /**
     * 报警数据解析方法
     *
     * @param data      数据源
     * @param offset    偏移量
     * @param canEntity 解析后封装对象
     */
    private void doAlarmAnalysis(byte[] data, int offset, GbCanEntity canEntity) {

        GbAlarmEntity alarm = new GbAlarmEntity();

        //最高报警等级 0:无故障  1:1级故障 2:2级故障 3:3级故障
        int hal = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        alarm.setHal(hal);
        //通用报警标志
        String cas = ByteAnalysisUtil.bytes2BitStr(data, offset, 4);
        try {
            doCommonAlarm(cas, alarm);
        } catch (IllegalAccessException e) {
            log.error("common alarm analysis error");
        }
        offset += 4;
        //可充电储能装置故障总数
        int rcesdfn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        alarm.setCesfn(rcesdfn);
        List<Integer> cesfcList = alarm.getCesfcList();
        if (cesfcList == null) {
            cesfcList = new ArrayList<>(rcesdfn);
        }
        for (int i = 0; i < rcesdfn; i++) {
            //可充电储能装置故障代码
            int rcesdfc = ByteAnalysisUtil.bytes2Int(data, offset, 4);
            offset += 4;
            cesfcList.add(rcesdfc);
        }
        alarm.setCesfcList(cesfcList);
        //驱动电机故障总数
        int dmfn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        alarm.setDmfn(dmfn);
        List<Integer> dmfcList = alarm.getDmfcList();
        if (dmfcList == null) {
            dmfcList = new ArrayList<>(dmfn);
        }
        for (int i = 0; i < dmfn; i++) {
            //驱动电机故障代码
            int dmfc = ByteAnalysisUtil.bytes2Int(data, offset, 4);
            offset += 4;
            dmfcList.add(dmfc);
        }
        alarm.setDmfcList(dmfcList);
        //发动机故障总数
        int efn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        alarm.setEfn(efn);
        List<Integer> efcList = alarm.getEfcList();
        if (efcList == null) {
            efcList = new ArrayList<>(efn);
        }
        for (int i = 0; i < efn; i++) {
            //发动机故障代码
            int efc = ByteAnalysisUtil.bytes2Int(data, offset, 4);
            offset += 4;
            efcList.add(efc);
        }
        alarm.setEfcList(efcList);
        //其他故障总数
        int ofn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        alarm.setOfn(ofn);
        List<Integer> ofcList = alarm.getOfcList();
        if (ofcList == null) {
            ofcList = new ArrayList<>();
        }
        for (int i = 0; i < ofn; i++) {
            //其他故障代码
            int ofc = ByteAnalysisUtil.bytes2Int(data, offset, 4);
            offset += 4;
            ofcList.add(ofc);
        }
        alarm.setOfcList(ofcList);
        // TODO: 2017/3/6 报警
        doNextAnalysis(data, offset, canEntity);
    }

    /**
     * 通用报警标志解析方法
     *
     * @param bitStr 通用报警标志二进制字符串
     * @param alarm  报警对象
     * @throws IllegalAccessException
     */
    private void doCommonAlarm(String bitStr, GbAlarmEntity alarm) throws IllegalAccessException {
        CommonAlarmBitIndex[] index = CommonAlarmBitIndex.values();
        Class clz = alarm.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (CommonAlarmBitIndex i : index) {
            int s = Integer.valueOf(bitStr.substring(i.getIndex(), i.getIndex() + 1));
            for (Field field : fields) {
                if (field.getName().equals(i.getParam())) {
                    field.setAccessible(true);
                    field.set(alarm, s);
                }
            }
        }
    }

    /**
     * 可充电储能装置电压数据解析方法
     *
     * @param data      数据源
     * @param offset    偏移量
     * @param canEntity 解析后封装对象
     */
    private void doChargeableEnergyStorageVoltageAnalysis(byte[] data, int offset, GbCanEntity canEntity) {
        GbBmsEntity bms = canEntity.getBms();
        if (bms == null) {
            bms = new GbBmsEntity();
        }
        //可充电储能子系统个数
        int cesssn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
        bms.setCesssn(cesssn);
        List<GbCesvEntity> cesvList = bms.getCesvList();
        if (cesvList == null) {
            cesvList = new ArrayList<>(cesssn);
        }
        GbCesvEntity cesv;
        for (int i = 0; i < cesssn; i++) {
            cesv = new GbCesvEntity();
            //可充电储能子系统号
            int cesssc = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
            cesv.setCesssc(cesssc);
            //可充电储能装置电压
            float cesdv = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f;
            offset += 2;
            cesv.setCesdv(cesdv);
            //可充电储能装置电流
            float cesda = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.1f - 1000;
            offset += 2;
            cesv.setCesdc(cesda);
            //单体电池总数
            int scn = ByteAnalysisUtil.bytes2Int(data, offset, 2);
            offset += 2;
            cesv.setScn(scn);
            //本帧起始电池序号
            int tfcn = ByteAnalysisUtil.bytes2Int(data, offset, 2);
            offset += 2;
            cesv.setTfcn(tfcn);
            //本帧单体电池总数
            int tfscn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
            cesv.setTfscn(tfscn);
            List<Float> scvList = new ArrayList<>(tfscn);
            for (int j = 0; j < tfscn; j++) {
                //单体电池电压
                float scv = ByteAnalysisUtil.bytes2Int(data, offset, 2) * 0.001f;
                offset += 2;
                scvList.add(scv);
            }
            cesv.setScvList(scvList);
            cesvList.add(cesv);
        }
        bms.setCesvList(cesvList);
        canEntity.setBms(bms);
        doNextAnalysis(data, offset, canEntity);
    }

    /**
     * 可充电储能装置温度数据解析方法
     *
     * @param data      数据源
     * @param offset    偏移量
     * @param canEntity 解析后封装对象
     */
    private void doChargeableEnergyStorageTemAnalysis(byte[] data, int offset, GbCanEntity canEntity) {
        GbBmsEntity bms;
        if (canEntity.getBms() != null) {
            bms = canEntity.getBms();
        } else {
            bms = new GbBmsEntity();
        }

        //可充电子系统个数
        int cesssn = ByteAnalysisUtil.bytes2Int(data, offset++, 1);

        List<GbCestEntity> cestList = bms.getCestList();
        if (cestList == null) {
            cestList = new ArrayList<>(cesssn);
        }
        GbCestEntity cest;
        for (int i = 0; i < cesssn; i++) {
            cest = new GbCestEntity();
            //可充电储能子系统号
            int cesssc = ByteAnalysisUtil.bytes2Int(data, offset++, 1);
            cest.setCesssc(cesssc);
            //可充电储能温度探针个数
            int cestpn = ByteAnalysisUtil.bytes2Int(data, offset, 2);
            offset += 2;
            cest.setCestpn(cestpn);
            List<Integer> tpctList = new ArrayList<>(cestpn);
            for (int j = 0; j < cestpn; j++) {
                //可充电储能子系统各温度探针检测到的温度值
                int cesssetpct = ByteAnalysisUtil.bytes2Int(data, offset++, 1) - 40;
                tpctList.add(cesssetpct);
            }
            cest.setTpcvList(tpctList);
            cestList.add(cest);
        }
        bms.setCestList(cestList);
        canEntity.setBms(bms);
        doNextAnalysis(data, offset, canEntity);
    }

    /**
     * 自定义数据解析方法
     *
     * @param data      数据源
     * @param offset    偏移量
     * @param canEntity 解析后封装对象
     */
    private void doDiyDataAnalysis(byte[] data, int offset, GbCanEntity canEntity) {
        //自定义数据长度
        int diyLength = ByteAnalysisUtil.bytes2Int(data, offset, 2);
        offset += 2;
        for (int i = 0; i < diyLength; i++) {
            //自定义数据
        }
        doNextAnalysis(data, offset, canEntity);
    }

    /**
     * 获取时间字符串
     *
     * @param b      byte 数组
     * @param offset 偏移量
     * @param length 长度
     * @return 时间字符串
     */
    private String getDateStr(byte[] b, int offset, int length) {
        StringBuilder sbf = new StringBuilder();
        for (int i = offset; i < length + offset; i++) {
            if (b[i] < 10) {
                sbf.append(0).append(b[i]);
            } else {
                sbf.append(b[i]);
            }
        }
        return sbf.toString();
    }

}
