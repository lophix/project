package com.flag.project.analysis.service;

import com.flag.project.analysis.entity.ProtocolObject;
import com.flag.project.analysis.entity.gb.GbCanEntity;

/**
 * 新国标解析服务接口
 *
 * @author xu
 * @since 2017-03-02-15:08
 */
public interface IAnalysisGb {
    /**
     * 数据解析方法入口
     *
     * @param protocolObject 消息对象
     * @return 解析后封装对象
     */
    GbCanEntity analysisFrames(ProtocolObject protocolObject);
}
