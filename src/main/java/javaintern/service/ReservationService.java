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
		validateReservation(reservation);
		return reservationDao.create(reservation);
	}

	public Reservation update(Reservation reservation) throws NonValidIdException {
		validateId(reservation.getId());
		return reservationDao.update(reservation);
	}

	public Boolean delete(Reservation reservation) throws NonValidIdException {
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
		if (reservation.getId() < 0) {
			throw new WrongParameterException("id is invalid");
		}
		if (reservation.getOrganizationId() < 0) {
			throw new WrongParameterException("Organization id is invalid");
		}
		if (reservation.getAgentId() < 0) {
			throw new WrongParameterException("Agent id is invalid");
		}
		if (reservation.getPrice() < 0) {
			throw new WrongParameterException("Price is invalid");
		}
		if (reservation.getQuote() < 0) {
			throw new WrongParameterException("Quote is invalid");
		}
		if (reservation.getCustomerId() < 0) {
			throw new WrongParameterException("Customer id is invalid");
		}
		if (reservation.getProductId() < 0) {
			throw new WrongParameterException("Product id is invalid");
		}
	}
}
