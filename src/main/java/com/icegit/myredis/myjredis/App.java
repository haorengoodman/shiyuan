package com.icegit.myredis.myjredis;

import redis.clients.jedis.JedisCluster;

import java.util.TreeSet;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        String pattern = "*";
        TreeSet<String> keys = JedisUtil.keys(pattern);
        System.out.println(keys);

        String key = "20191226";
        String field = "Jack";
        JedisCluster jedisCluster = JedisUtil.getJedisCluster();
        System.out.println(field+":"+jedisCluster.hget(key,field));
        jedisCluster.hincrBy(key,field,1);
        System.out.println(key+":"+jedisCluster.hgetAll(key));

        jedisCluster.incr("马");
        System.out.println("马:"+jedisCluster.get("马"));
    }

}
