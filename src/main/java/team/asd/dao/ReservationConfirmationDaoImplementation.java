package team.asd.dao;

import org.springframework.stereotype.Repository;
import team.asd.entity.ReservationConfirmation;

@Repository
public class ReservationConfirmationDaoImplementation implements ReservationConfirmationDao {
	@Override
	public ReservationConfirmation readById(Integer id) {
		return null;
	}

	@Override
	public void create(ReservationConfirmation reservationConfirmation) {

	}

	@Override
	public void update(ReservationConfirmation reservationConfirmation) {

	}

	@Override
	public Boolean delete(Integer id) {
		return null;
	}
}
