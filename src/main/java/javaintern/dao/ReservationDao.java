package javaintern.dao;

import javaintern.entity.Reservation;

public interface ReservationDao {
	Reservation readById(Integer id);

	Reservation create(Integer id);

	Reservation update(Integer id);

	Boolean delete(Integer id);
}
