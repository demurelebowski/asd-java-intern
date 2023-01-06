package team.asd.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;

import java.util.List;

@Repository
public class RedisClientDaoImpl implements RedisClientDao {
	@Autowired
	JedisPooled jedisPooled;
	ObjectMapper mapper = new ObjectMapper();

	@Override
	public String readByKey(String key) {
		return jedisPooled.get(key);
	}

	@Override
	public String saveValueByKey(String key, String value) {
		return jedisPooled.set(key, value);
	}

	@Override
	public String saveList(String keyList, List<String> list) {
		try {
			String jsonInString = mapper.writeValueAsString(list);
			return jedisPooled.set(keyList, jsonInString);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String> retrieveList(String keyList) {
		String jsonString = jedisPooled.get(keyList);
		try {
			return mapper.readValue(jsonString, new TypeReference<>() {
			});
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

	}
}
