package team.asd.dao;

import team.asd.entity.Reservation;

public interface ReservationDao {
	Reservation readById(Integer id);

	Reservation create(Reservation reservation);

	Reservation update(Reservation reservation);

	Boolean delete(Integer id);
}
