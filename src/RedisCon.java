package tcc.com.saber.tcc.integration;

import redis.clients.jedis.Jedis;

public class RedisCon {
	
	
	
	public Jedis conectar() {
		// TODO Auto-generated method stub
Jedis jedis = new Jedis("localhost");
		
	return jedis;
	}
	

}
