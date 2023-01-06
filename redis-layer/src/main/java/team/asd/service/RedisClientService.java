package team.asd.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
		validateValue(value);
		return redisClientDao.saveValueByKey(key, value);
	}

	public void saveList(String keyList, List<String> list) {
		validateKey(keyList);
		if (CollectionUtils.isEmpty(list)) {
			throw new ValidationException("List is empty");
		}
		redisClientDao.saveList(keyList, list);
	}

	public List<String> retrieveList(String keyList) {
		validateKey(keyList);
		return redisClientDao.retrieveList(keyList);
	}

	public Long saveElementIntoList(String keyList, String value) {
		validateKey(keyList);
		validateValue(value);
		return redisClientDao.saveElementIntoList(keyList, value);
	}

	Long saveValueInHashMap(String primaryKey, String secondaryKey, String value) {
		validateKey(primaryKey);
		if (Strings.isEmpty(secondaryKey) || Strings.isEmpty(value)) {
			throw new ValidationException("Parameter is empty");
		}
		return redisClientDao.saveValueInHashMap(primaryKey, secondaryKey, value);
	}

	private void validateKey(String keyList) {
		if (Strings.isEmpty(keyList)) {
			throw new ValidationException("Key is empty");
		}
	}

	private void validateValue(String value) {
		if (Strings.isEmpty(value)) {
			throw new ValidationException("Value is empty");
		}
	}
}
