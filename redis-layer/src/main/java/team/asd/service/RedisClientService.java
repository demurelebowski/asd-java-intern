package team.asd.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import team.asd.constant.ReservationState;
import team.asd.dao.FeeDao;
import team.asd.dao.PriceDao;
import team.asd.dao.RedisClientDao;
import team.asd.dao.ReservationDao;
import team.asd.entity.Fee;
import team.asd.entity.Price;
import team.asd.entity.Reservation;
import team.asd.exceptions.ValidationException;
import team.asd.util.ConvertUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class RedisClientService {
	private final RedisClientDao redisClientDao;
	private final ReservationDao reservationDao;
	private final PriceDao priceDao;
	private final FeeDao feeDao;

	public RedisClientService(@Autowired RedisClientDao redisClientDao, @Autowired ReservationDao reservationDao, @Autowired PriceDao priceDao,
			@Autowired FeeDao feeDao) {
		this.redisClientDao = redisClientDao;
		this.reservationDao = reservationDao;
		this.priceDao = priceDao;
		this.feeDao = feeDao;
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

	public Long saveValueInHashMap(String primaryKey, String secondaryKey, String value) {
		validateKey(primaryKey);
		if (Strings.isEmpty(secondaryKey) || Strings.isEmpty(value)) {
			throw new ValidationException("Parameter is empty");
		}
		return redisClientDao.saveValueInHashMap(primaryKey, secondaryKey, value);
	}

	public String retrieveValueFromHashMap(String primaryKey, String secondaryKey) {
		validateKey(primaryKey);
		validateKey(secondaryKey);
		return redisClientDao.retrieveValueFromHashMap(primaryKey, secondaryKey);
	}

	public Map<String, String> retrieveValueFromHashMap(String primaryKey) {
		validateKey(primaryKey);
		return redisClientDao.retrieveValueFromHashMap(primaryKey);
	}

	public String saveValueWithExpireDate(String key, String value, Long expireDate) {
		validateKey(key);
		if (Strings.isEmpty(value) || Objects.isNull(expireDate)) {
			throw new ValidationException("Parameter is empty");
		}
		return redisClientDao.saveValueWithExpireDate(key, value, expireDate);
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

	public String saveQuoteCalculation(Integer productId, String fromDate, String toDate) {
		String value = retrieveValueFromHashMap("quote_result:"+productId, fromDate+":"+toDate);

		if (value!=null){
			return value;
		}

		if (hasReservationCollision(productId, fromDate, toDate)){
			return "0";
		}
		return null;
	}

	public Boolean hasReservationCollision(Integer productId, String fromDateStr, String toDateStr) {
		LocalDate fromDate = ConvertUtil.localDateFromString(fromDateStr);
		LocalDate toDate = ConvertUtil.localDateFromString(toDateStr);
		List<Reservation> reservationList = reservationDao.getListByDates(fromDate, toDate, null);
		List<Reservation> reservationListFiltered = reservationList.stream()
				.filter(reservation -> productId.equals(reservation.getProductId()) && !ReservationState.Cancelled.equals(reservation.getState())
						&& !ReservationState.Initial.equals(reservation.getState()))
				.filter(reservation -> reservation.getFromDate()
						.isBefore(toDate) && reservation.getToDate()
						.isAfter(fromDate))
				.collect(Collectors.toList());

		return !reservationListFiltered.isEmpty();
	}

	public Double quoteCalculation(Integer productId, Date fromDate, Date toDate){

		List<Price> priceList = priceDao.readPricesByDateRange(fromDate, toDate);
		List<Fee> feeList = feeDao.readFeesByDateRange(fromDate, toDate);

		return null;
	}
}
