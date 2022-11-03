package javaintern.service;

import javaintern.dao.ReservationDao;
import javaintern.entity.Reservation;

public class ReservationService {
	private final ReservationDao reservationDao;

	public ReservationService(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public Reservation readById(Integer id) {
		return reservationDao.readById(id);
	}

	public Reservation create(Reservation reservation) {
		return reservationDao.create(reservation);
	}

	public Reservation update(Reservation reservation) {
		return reservationDao.update(reservation);
	}

	public void delete(Reservation reservation) {
		reservationDao.delete(reservation);
	}
}
