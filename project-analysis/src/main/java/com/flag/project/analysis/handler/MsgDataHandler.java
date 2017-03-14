package com.flag.project.analysis.handler;

import com.flag.project.analysis.entity.ProtocolObject;
import com.flag.project.analysis.entity.gb.GbCanEntity;
import com.flag.project.analysis.pojo.DataPackage;
import com.flag.project.analysis.service.IAnalysisGb;
import com.flag.project.analysis.service.gb.GbAnalysisService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * message data handler
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 11:09
 */
public class MsgDataHandler extends SimpleChannelInboundHandler<Object> {
    private IAnalysisGb analysisGb = new GbAnalysisService();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        DataPackage data = (DataPackage) msg;
        ProtocolObject obj = new ProtocolObject();
        obj.setAttrBytes(data.getData());
        obj.setKey(data.getCmm());
        GbCanEntity canEntity = analysisGb.analysisFrames(obj);
//        System.out.println(canEntity);
        ctx.writeAndFlush("good");
    }
}
