package team.asd.dao;

import team.asd.entity.ReservationConfirmation;

public interface ReservationConfirmationDao {
	ReservationConfirmation readById(Integer id);

	void create(ReservationConfirmation reservationConfirmation);

	void update(ReservationConfirmation reservationConfirmation);

	Boolean delete(Integer id);
}
