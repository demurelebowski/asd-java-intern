package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;

@Repository
public class RedisClientDaoImpl implements RedisClientDao {
	@Autowired
	JedisPooled jedisPooled;

	@Override
	public String readByKey(String key) {
		return jedisPooled.get(key);
	}

	@Override
	public String saveValueByKey(String key, String value) {
		return jedisPooled.set(key, value);
	}
}
