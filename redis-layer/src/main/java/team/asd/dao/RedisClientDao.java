package team.asd.dao;

import java.util.List;

public interface RedisClientDao {
	String readByKey(String key);

	String saveValueByKey(String key, String value);

	String saveList(String keyList, List<String> list);

	List<String> retrieveList(String keyList);
}
