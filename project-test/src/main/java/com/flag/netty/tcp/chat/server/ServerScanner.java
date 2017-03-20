package com.flag.netty.tcp.chat.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Authuor Administrator
 * @Create 2016-11-01-17:05
 */
public class ServerScanner implements Runnable {

    private Channel channel;

    public ServerScanner(Channel channel){
        this.channel = channel;
    }

    @Override
    public void run() {
        System.out.println("chat start");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        while(msg != null && !msg.equals("exit")){
            ByteBuf encoded = channel.alloc().buffer(4 * msg.length());
            encoded.writeBytes(msg.getBytes());
            channel.writeAndFlush(encoded);
            msg = sc.nextLine();
        }
        sc.close();
    }
}
