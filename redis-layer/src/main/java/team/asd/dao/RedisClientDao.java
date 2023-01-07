package team.asd.dao;

import java.util.List;

public interface RedisClientDao {
	String readByKey(String key);

	String saveValueByKey(String key, String value);

	void saveList(String keyList, List<String> list);

	List<String> retrieveList(String keyList);

	Long saveElementIntoList(String keyList, String value);

	Long saveValueInHashMap(String primaryKey, String secondaryKey, String value);

	String retrieveValueFromHashMap(String primaryKey, String secondaryKey);
}
