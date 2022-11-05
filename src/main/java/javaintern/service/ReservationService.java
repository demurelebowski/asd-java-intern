package javaintern.service;

import javaintern.dao.ReservationDao;
import javaintern.entity.Reservation;
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

	public Reservation create(Reservation reservation) throws MissingParameterException {
		if (ObjectUtils.anyNull(reservation.getOrganizationId(), reservation.getCustomerId(), reservation.getAgentId(), reservation.getProductId(),
				reservation.getFromDate(), reservation.getToDate(), reservation.getPrice(), reservation.getQuote(), reservation.getCurrency())) {
			throw new MissingParameterException();
		}
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
}
