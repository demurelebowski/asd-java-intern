package team.asd.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import team.asd.dao.ReservationDao;
import team.asd.entity.Reservation;
import team.asd.exceptions.ValidationException;

import java.util.Objects;

@Service
public class ReservationService {
	private final ReservationDao reservationDao;
	public ReservationService(ReservationDao reservationDao) {
			this.reservationDao = reservationDao;
		}

	public Reservation readById(Integer id) {
		validateId(id);
		return reservationDao.readById(id);
	}

	public void create(Reservation reservation) {
		validateReservationCreation(reservation);
		reservationDao.create(reservation);
	}

	public void update(Reservation reservation) {
		validateId(reservation.getId());
		reservationDao.update(reservation);
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
		if (Objects.isNull(reservation)) {
			throw new ValidationException("Reservation is null");
		}
		if (ObjectUtils.anyNull(reservation.getOrganizationId(), reservation.getCustomerId(), reservation.getAgentId(), reservation.getProductId(),
				reservation.getFromDate(), reservation.getToDate(), reservation.getPrice(), reservation.getQuote(), reservation.getCurrency())) {
			throw new ValidationException("One of the required parameters not found.");
		}
	}
}
