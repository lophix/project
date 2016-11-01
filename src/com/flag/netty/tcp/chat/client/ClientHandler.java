package com.flag.netty.tcp.chat.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Authuor Administrator
 * @Create 2016-11-01-17:03
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf result = (ByteBuf) msg;
        byte[] bytes = new byte[result.readableBytes()];
        result.readBytes(bytes);
        System.out.println("server resp : " + new String(bytes, "UTF-8"));
        result.release();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("==============  active");
        new Thread(new ClientScanner(ctx.channel())).start();
    }
}
