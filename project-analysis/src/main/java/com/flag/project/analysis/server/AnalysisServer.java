package com.flag.project.analysis.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Log4J2LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * analysis server class, use netty
 *
 * @author xuj
 * @version 0.0.1-SNAPSHOT
 * @since 2017-03-01 10:38
 */
public class AnalysisServer {

    private static final Logger log = LogManager.getLogger(AnalysisServer.class);
    private int port;

    static {
        InternalLoggerFactory.setDefaultFactory(Log4J2LoggerFactory.INSTANCE);
    }

    public AnalysisServer(int port){
        this.port = port;
    }

    public void bind(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ServerChannelInitializer());
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("bind the server port is {}", port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server bind error : {}", e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
