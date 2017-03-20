package com.flag.c3p0.test.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * @Authuor Administrator
 * @Create 2016-11-03-15:33
 */
public class ClientReq implements Runnable {
    private Channel channel = null;
    public ClientReq(Channel channel){
        this.channel = channel;
    }
    @Override
    public void run() {
        while(true){
            String msg = "query";
            ByteBuf encoded = channel.alloc().buffer(4 * msg.length());
            encoded.writeBytes(msg.getBytes());
            channel.writeAndFlush(encoded);
            System.out.println("======== query");
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
