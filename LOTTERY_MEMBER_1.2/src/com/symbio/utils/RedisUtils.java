package com.symbio.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

/**
 * redis缓存类
 * @author 
 *
 */
public class RedisUtils{

	 public static boolean isOpen = true; 
	
	 private static int EXPIRE_TIMES = 60; //默认过期时间
	 
	 public static int PUBLIC_EXPIPE = 60 * 60;
	 
	 private static String REDIS_HOST = "127.0.0.1"; //redis地址
	 
	 private static int REDIS_PORT = 6379;	//redis端口
	 
	// private static Jedis jedis;
	 
	 private static JedisPool pool;
	
	 static{
		// jedis = new Jedis(REDIS_HOST,REDIS_PORT);
		 
		 
		 JedisPoolConfig config = new JedisPoolConfig();
		 config.setMaxActive(1024);//#最大分配的对象数
		 config.setMaxIdle(200);//#最大能够保持idel状态的对象数
		 config.setMaxWait(1000);//#当池内没有返回对象时，最大等待时间
		 config.setTestOnBorrow(true);//#当调用borrow Object方法时，是否进行有效性检查
		 config.setTestOnReturn(true);//#当调用return Object方法时，是否进行有效性检查
			    
			
		 pool= new JedisPool(config, REDIS_HOST,REDIS_PORT,100000);
		 
	 }
	   
	 
	 /**
	     * 序列化
	     * 
	     * @param object
	     * @return
	     */
	    public static byte[] serialize(Object object) {
	        ObjectOutputStream oos = null;
	        ByteArrayOutputStream baos = null;
	        try {
	            // 序列化
	            baos = new ByteArrayOutputStream();
	            oos = new ObjectOutputStream(baos);
	            oos.writeObject(object);
	            byte[] bytes = baos.toByteArray();
	            return bytes;
	        } catch (Exception e) {

	        }
	        return null;
	    }
	    /**
	     * 反序列化
	     * 
	     * @param bytes
	     * @return
	     */
	    public static Object unserialize(byte[] bytes) {
	        ByteArrayInputStream bais = null;
	        try {
	            // 反序列化
	            bais = new ByteArrayInputStream(bytes);
	            ObjectInputStream ois = new ObjectInputStream(bais);
	            return ois.readObject();
	        } catch (Exception e) {

	        }
	        return null;
	    }
	    
	 
	public static void set(String key,String value){
		Jedis jedis = pool.getResource(); //从redis池里面取
		jedis.set(key, value);
		jedis.expire(key, EXPIRE_TIMES);
		pool.returnResource(jedis);
	}
	
	public static void set(String key,Object object){
		Jedis jedis = pool.getResource();
		jedis.set(key.getBytes(),serialize(object));
		jedis.expire(key.getBytes(), EXPIRE_TIMES);
		pool.returnResource(jedis);
	}
	
	public static void set(String key,String value,int seconds){
		Jedis jedis = pool.getResource();
		jedis.set(key, value);
		jedis.expire(key, seconds);
		pool.returnResource(jedis);
	}
	
	public static void set(String key,Object object,int seconds){
		Jedis jedis = pool.getResource();
		jedis.set(key.getBytes(),serialize(object));
		jedis.expire(key.getBytes(), seconds);
		pool.returnResource(jedis);
	}
	
	public static Object get(String key){
		Jedis jedis = pool.getResource();
		if(jedis.exists(key)){
			Object obj = jedis.get(key);
			pool.returnResource(jedis);
			return obj;
		}else{
			pool.returnResource(jedis);
			return null;
		}
	}
	
	public static Object get(byte[] key){
		Jedis jedis = pool.getResource();
		if(jedis.exists(key)){
			Object obj = jedis.get(key);
			pool.returnResource(jedis);
			return obj;
		}else{
			pool.returnResource(jedis);
			return null;
		}
	}
	
	public static void delete(String key){
		Jedis jedis = pool.getResource();
		jedis.del(key);
		pool.returnResource(jedis);
	}
	 
	 
}
