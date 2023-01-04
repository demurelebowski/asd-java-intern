package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;

@Repository
public class RedisClientDaoImpl implements RedisClientDao {
	private @Value("${redis.host}") String redisHost;
	private @Value("${redis.port}") int redisPort;
	private @Value("${redis.password}") String password;

	@Autowired
	JedisPooled jedisPooled;

	@Override
	public String readByKey(String key) {
		return jedisPooled.get(key);
	}
}
