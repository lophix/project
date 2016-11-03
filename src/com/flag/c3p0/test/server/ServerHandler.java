package com.flag.c3p0.test.server;

import com.flag.c3p0.test.dao.ITestDao;
import com.flag.c3p0.test.dao.TestDaoImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Authuor Administrator
 * @Create 2016-11-01-17:03
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static ITestDao dao = new TestDaoImpl();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("======= read");
        String result = dao.query();
        System.out.println(result);
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("====== active");
    }
}
