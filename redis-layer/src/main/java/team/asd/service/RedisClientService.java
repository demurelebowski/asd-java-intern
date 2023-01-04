package team.asd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.RedisClientDao;

@Service
public class RedisClientService {
	private final RedisClientDao redisClientDao;

	public RedisClientService(@Autowired RedisClientDao redisClientDao) {
		this.redisClientDao = redisClientDao;
	}

	public String readByKey(String key) {
		return redisClientDao.readByKey(key);
	}
}
