package com.flag.project.analysis.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * message data encoder
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 11:11
 */
public class MsgDataEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

    }
}
