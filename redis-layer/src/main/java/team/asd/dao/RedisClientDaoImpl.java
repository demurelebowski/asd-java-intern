package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;

import java.util.List;
import java.util.Map;

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

	@Override
	public void saveList(String keyList, List<String> list) {
		list.forEach(element -> jedisPooled.lpush(keyList, element));
	}

	@Override
	public List<String> retrieveList(String keyList) {
		return jedisPooled.lrange(keyList, 0, -1);
	}

	@Override
	public Long saveElementIntoList(String keyList, String value) {
		return jedisPooled.lpush(keyList, value);
	}

	@Override
	public Long saveValueInHashMap(String primaryKey, String secondaryKey, String value) {
		return jedisPooled.hset(primaryKey, secondaryKey, value);
	}

	@Override
	public String retrieveValueFromHashMap(String primaryKey, String secondaryKey) {
		return jedisPooled.hget(primaryKey, secondaryKey);
	}

	@Override
	public Map<String, String> retrieveValueFromHashMap(String primaryKey) {
		return jedisPooled.hgetAll(primaryKey);
	}

	@Override
	public String saveValueWithExpireDate(String key, String value, Long expireDate) {
		return jedisPooled.setex(key, expireDate, value);
	}
}
