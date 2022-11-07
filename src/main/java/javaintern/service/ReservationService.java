package javaintern.service;

import javaintern.dao.ReservationDao;
import javaintern.entity.Reservation;
import javaintern.exceptions.MissingParameterException;
import javaintern.exceptions.NonValidIdException;
import javaintern.exceptions.WrongParameterException;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;

public class ReservationService {
	private final ReservationDao reservationDao;

	public ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public Reservation readById(Integer id) throws NonValidIdException {
		validateId(id);
		return reservationDao.readById(id);
	}

	public Reservation create(Reservation reservation) throws MissingParameterException, WrongParameterException {
		if (Objects.isNull(reservation)) {
			return null;
		}
		validateReservation(reservation);
		return reservationDao.create(reservation);
	}

	public Reservation update(Reservation reservation) throws NonValidIdException {
		if (Objects.isNull(reservation)) {
			return null;
		}
		validateId(reservation.getId());
		return reservationDao.update(reservation);
	}

	public Boolean delete(Reservation reservation) throws NonValidIdException {
		if (Objects.isNull(reservation)) {
			return null;
		}
		validateId(reservation.getId());
		return reservationDao.delete(reservation);
	}

	private void validateId(Integer id) throws NonValidIdException {
		if (Objects.isNull(id) || id < 0) {
			throw new NonValidIdException();
		}
	}

	private void validateReservation(Reservation reservation) throws MissingParameterException, WrongParameterException {
		if (ObjectUtils.anyNull(reservation.getOrganizationId(), reservation.getCustomerId(), reservation.getAgentId(), reservation.getProductId(),
				reservation.getFromDate(), reservation.getToDate(), reservation.getPrice(), reservation.getQuote(), reservation.getCurrency())) {
			throw new MissingParameterException();
		}
		if (reservation.getId() < 0 || reservation.getOrganizationId() < 0 || reservation.getAgentId() < 0 || reservation.getPrice() < 0
				|| reservation.getQuote() < 0 || reservation.getCustomerId() < 0 || reservation.getProductId() < 0) {
			throw new WrongParameterException();
		}
	}
}
