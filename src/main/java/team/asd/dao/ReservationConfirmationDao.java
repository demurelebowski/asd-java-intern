package team.asd.dao;

import team.asd.entity.ReservationConfirmation;

import java.util.List;

public interface ReservationConfirmationDao {
	ReservationConfirmation readById(Integer id);

	void create(ReservationConfirmation reservationConfirmation);

	void update(ReservationConfirmation reservationConfirmation);

	Boolean delete(Integer id);

	List<ReservationConfirmation> getListByReservationId(Integer reservationId);
}
