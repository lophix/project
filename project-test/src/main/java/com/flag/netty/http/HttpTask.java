package com.flag.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author xuj
 * @since 2017-08-15-16:17
 */
public class HttpTask implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(HttpTask.class);

    private ChannelHandlerContext ctx;

    public HttpTask(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        LOGGER.info("receive msg");
        String rsp = "hello";
        HttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(rsp.getBytes()));
        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, rsp.length());
        httpResponse.headers().set(HttpHeaderNames.SERVER, "netty4");
        ctx.writeAndFlush(httpResponse).addListener(ChannelFutureListener.CLOSE);
    }
}
