package com.icegit.mynetty.heartbeat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class HeartBeatClient {
    static String text = "ping";
    int port;        //8090
    String netHost;  //localhost
    Channel channel;
    Bootstrap bootstrap;


    public HeartBeatClient(String netHost, int port){
        this.netHost = netHost;
        this.port = port;
    }

    public void connect() throws Exception{
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new HeartBeatClientInitializer(HeartBeatClient.this));
            channel = bootstrap.connect(netHost,port).sync().channel();
        }catch(Exception e){
            System.out.println("连接失败，重新连接.......");
            Thread.sleep(2000);
            eventLoopGroup.shutdownGracefully();
            connect();
        }
    }

    public void sendMsg(String text) throws Exception{
        Thread.sleep(1 * 1000);
        channel.writeAndFlush(text);
    }

    public void reconnectAndPing() throws Exception{
        connect();
        while (channel.isActive()){
            sendMsg(text);
        }
    }

    public static void main(String[] args) throws Exception{
        HeartBeatClient client = new HeartBeatClient("localhost", 8090);
        client.connect();

        while (client.channel.isActive()){
            client.sendMsg(text);
        }
    }

}