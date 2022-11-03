package javaintern.dao;

import javaintern.entity.Reservation;

public interface ReservationDao {
	Reservation readById(Integer id);

	Reservation create(Reservation reservation);

	Reservation update(Reservation reservation);

	void delete(Reservation reservation);
}
