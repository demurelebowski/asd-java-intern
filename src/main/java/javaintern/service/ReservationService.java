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
		validateReservationCreation(reservation);
		return reservationDao.create(reservation);
	}

	public Reservation update(Reservation reservation) throws NonValidIdException {
		if (Objects.isNull(reservation)) {
			return null;
		}
		validateId(reservation.getId());
		return reservationDao.update(reservation);
	}

	public Boolean delete(Integer id) throws NonValidIdException {
		validateId(id);
		return reservationDao.delete(id);
	}

	private void validateId(Integer id) throws NonValidIdException {
		if (Objects.isNull(id) || id < 0) {
			throw new NonValidIdException();
		}
	}

	private void validateReservationCreation(Reservation reservation) throws MissingParameterException, WrongParameterException {
		if (ObjectUtils.anyNull(reservation.getOrganizationId(), reservation.getCustomerId(), reservation.getAgentId(), reservation.getProductId(),
				reservation.getFromDate(), reservation.getToDate(), reservation.getPrice(), reservation.getQuote(), reservation.getCurrency())) {
			throw new MissingParameterException();
		}
		if (reservation.getOrganizationId() < 1 || reservation.getAgentId() < 1 || reservation.getPrice() < 0
				|| reservation.getQuote() < 0 || reservation.getCustomerId() < 1 || reservation.getProductId() < 1) {
			throw new WrongParameterException();
		}
	}
}
