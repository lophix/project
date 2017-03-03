package com.flag.project.analysis.server;

import com.flag.project.analysis.decoder.MsgDataDecoder;
import com.flag.project.analysis.encoder.MsgDataEncoder;
import com.flag.project.analysis.handler.MsgDataHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;


/**
 * init server channel handler
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 10:55
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static final Logger log = LogManager.getLogger(ServerChannelInitializer.class);

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        log.info("init netty pipeline");
        ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO))
                .addLast(new ReadTimeoutHandler(90, TimeUnit.SECONDS))
                .addLast(new LengthFieldBasedFrameDecoder(ByteOrder.BIG_ENDIAN, 65535, 22, 2, 22, 0, true))
                .addLast(new MsgDataDecoder())
                .addLast(new MsgDataHandler())
                .addLast(new MsgDataEncoder());
    }
}
