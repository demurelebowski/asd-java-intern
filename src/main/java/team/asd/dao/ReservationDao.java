package team.asd.dao;

import team.asd.entity.Reservation;

public interface ReservationDao {
	Reservation readById(Integer id);

	void create(Reservation reservation);

	void update(Reservation reservation);

	Boolean delete(Integer id);
}
