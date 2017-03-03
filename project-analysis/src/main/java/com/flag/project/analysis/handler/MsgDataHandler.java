package com.flag.project.analysis.handler;

import com.flag.project.analysis.pojo.DataPackage;
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
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        DataPackage data = (DataPackage) msg;
//        System.out.println(data);
        ctx.writeAndFlush(msg);
    }
}
