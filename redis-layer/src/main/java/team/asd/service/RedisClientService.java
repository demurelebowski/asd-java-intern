package team.asd.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.RedisClientDao;
import team.asd.exceptions.ValidationException;

import java.util.List;

@Service
public class RedisClientService {
	private final RedisClientDao redisClientDao;

	public RedisClientService(@Autowired RedisClientDao redisClientDao) {
		this.redisClientDao = redisClientDao;
	}

	public String readByKey(String key) {
		validateKey(key);
		return redisClientDao.readByKey(key);
	}

	public String saveValueByKey(String key, String value) {
		validateKey(key);
		return redisClientDao.saveValueByKey(key, value);
	}

	public void saveList(String keyList, List<String> list) {
		validateKey(keyList);
		redisClientDao.saveList(keyList, list);
	}

	public List<String> retrieveList(String keyList) {
		validateKey(keyList);
		return redisClientDao.retrieveList(keyList);
	}

	public Long saveElementIntoList(String keyList, String value) {
		validateKey(keyList);
		return redisClientDao.saveElementIntoList(keyList, value);
	}

	private void validateKey(String keyList) {
		if (Strings.isEmpty(keyList)) {
			throw new ValidationException("Key is empty");
		}
	}
}
