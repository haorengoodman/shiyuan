package com.icegit.myredis.myjredis;

import redis.clients.jedis.*;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Description
 * @Author GAOTAO
 * @Date 2019-12-26 8:34
 */
public class JedisUtil {

    static int soTimeout =100;        //读取数据超时时间
    static int MAX_ATTEMPTS = 5;      //失败重试次数
    static int timeOut = 2000;        //连接超时时间
    static String password = "redis"; //密码
    private static JedisCluster jedisCluster = null; //密码

    public static JedisCluster createJedis(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(1);         // 最大连接数
        poolConfig.setMaxIdle(1);          // 最大空闲数
        poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.2.4", 7001));
        nodes.add(new HostAndPort("192.168.2.5", 7001));
        nodes.add(new HostAndPort("192.168.2.6", 7001));
        nodes.add(new HostAndPort("192.168.2.4", 7002));
        nodes.add(new HostAndPort("192.168.2.5", 7002));
        nodes.add(new HostAndPort("192.168.2.6", 7002));
        //JedisCluster cluster = new JedisCluster(nodes, poolConfig);
        jedisCluster = new JedisCluster(nodes, timeOut, soTimeout,MAX_ATTEMPTS,password, poolConfig);
        return jedisCluster;
    }

    public static void close(){
        if(jedisCluster != null) jedisCluster.close();
    }

    /**
     * 获取 jedisCluster
     * @return
     */
    public static JedisCluster getJedisCluster(){
        if(jedisCluster == null){
            return createJedis();
        }else{
            return jedisCluster;
        }
    }

    /**
     * 获取集群冲指定规则的key列表
     * @param pattern
     * @return
     */
    public static TreeSet<String> keys(String pattern){
        TreeSet<String> keys = new TreeSet<>();
        Map<String, JedisPool> clusterNodes = getJedisCluster().getClusterNodes();
        for(String k : clusterNodes.keySet()){
            System.err.printf("Getting keys from: {%s}\n", k);
            JedisPool jp = clusterNodes.get(k);
            Jedis connection = jp.getResource();
            try {
                keys.addAll(connection.keys(pattern));
            } catch(Exception e){
                System.err.printf("Getting keys error: {%s}\n", e);
            } finally{
                connection.close();//用完一定要close这个链接！！！
            }
        }
        return keys;
    }


}
