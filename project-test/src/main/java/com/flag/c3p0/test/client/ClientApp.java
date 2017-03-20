package com.flag.c3p0.test.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Authuor Administrator
 * @Create 2016-11-01-16:56
 */
public class ClientApp implements Runnable {

    private String remotAddress;
    private int port;

    public ClientApp(String remotAddress, int port){
        this.remotAddress = remotAddress;
        this.port = port;
    }

    @Override
    public void run(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientHandler());
                }
            });
            ChannelFuture f = b.connect(remotAddress, port).sync();
            System.out.println("client connected at host : " + remotAddress + ", port : " + port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        String remoteAddress = "127.0.0.1";
        int port = 8080;
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 30; i++) {
            executorService.submit(new ClientApp(remoteAddress, port));
        }
//        new ClientApp(remoteAddress, port).run();
    }
}
