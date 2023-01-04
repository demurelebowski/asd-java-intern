package team.asd.dao;

import org.springframework.stereotype.Repository;

@Repository
public class RedisClientDaoImpl implements RedisClientDao{
	@Override
	public String readByKey(String key) {
		return "test";
	}
}
