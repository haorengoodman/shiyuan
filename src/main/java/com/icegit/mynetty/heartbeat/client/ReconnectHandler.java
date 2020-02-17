package com.icegit.mynetty.heartbeat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

public class ReconnectHandler extends ChannelInboundHandlerAdapter {

    HeartBeatClient heartBeatClient;
    public ReconnectHandler(HeartBeatClient heartBeatClient){
        this.heartBeatClient = heartBeatClient;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Reconnecting ...");
                try {
                    heartBeatClient.reconnectAndPing();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1, TimeUnit.SECONDS);
    }
}
