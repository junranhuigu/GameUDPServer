package com.server.web.vo.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedisPool;

public class WebJedisVo {
	private String redisIP;
	private int port;
	private JedisPool jedisPool;
	private ShardedJedisPool shardedJedisPool;
	
	public WebJedisVo() {
		// TODO Auto-generated constructor stub
	}
	
	public WebJedisVo(String redisIP, int port) {
		this.redisIP = redisIP;
		this.port = port;
	}
	
	public void close(){
		if(jedisPool != null && !jedisPool.isClosed()){
			jedisPool.close();
		}
		if(shardedJedisPool != null && !shardedJedisPool.isClosed()){
			shardedJedisPool.close();
		}
	}

	public String getRedisIP() {
		return redisIP;
	}
	public void setRedisIP(String redisIP) {
		this.redisIP = redisIP;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public JedisPool getJedisPool() {
		return jedisPool;
	}
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}
}
