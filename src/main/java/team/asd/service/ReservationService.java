package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.dao.ReservationDao;
import team.asd.entity.Reservation;
import team.asd.exceptions.ValidationException;

import java.util.Objects;

@Service
public class ReservationService {
	private final ReservationDao reservationDao;

	public ReservationService(@Autowired ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public Reservation readById(Integer id) {
		validateId(id);
		return reservationDao.readById(id);
	}

	public Reservation create(Reservation reservation) {
		if (Objects.isNull(reservation)) {
			return null;
		}
		validateReservationCreation(reservation);
		return reservationDao.create(reservation);
	}

	public Reservation update(Reservation reservation) {
		if (Objects.isNull(reservation)) {
			return null;
		}
		validateId(reservation.getId());
		return reservationDao.update(reservation);
	}

	public Boolean delete(Integer id) {
		validateId(id);
		return reservationDao.delete(id);
	}

	private void validateId(Integer id) {
		if (Objects.isNull(id) || id < 0) {
			throw new ValidationException("Invalid id.");
		}
	}

	private void validateReservationCreation(Reservation reservation) {
		if (ObjectUtils.anyNull(reservation.getOrganizationId(), reservation.getCustomerId(), reservation.getAgentId(), reservation.getProductId(),
				reservation.getFromDate(), reservation.getToDate(), reservation.getPrice(), reservation.getQuote(), reservation.getCurrency())) {
			throw new ValidationException("One of the required parameters not found.");
		}
	}
}
