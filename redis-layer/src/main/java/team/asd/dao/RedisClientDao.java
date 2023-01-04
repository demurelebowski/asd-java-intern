package team.asd.dao;

public interface RedisClientDao {
	String readByKey(String key);

	String saveValueByKey(String key, String value);
}
