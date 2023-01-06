package team.asd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.RedisClientDao;

import java.util.List;

@Service
public class RedisClientService {
	private final RedisClientDao redisClientDao;

	public RedisClientService(@Autowired RedisClientDao redisClientDao) {
		this.redisClientDao = redisClientDao;
	}

	public String readByKey(String key) {
		return redisClientDao.readByKey(key);
	}

	public String saveValueByKey(String key, String value) {
		return redisClientDao.saveValueByKey(key, value);
	}

	public String saveList(String keyList, List<String> list) {
		return redisClientDao.saveList(keyList, list);
	}

	public List<String> retrieveList(String keyList) {
		return redisClientDao.retrieveList(keyList);
	}
}
