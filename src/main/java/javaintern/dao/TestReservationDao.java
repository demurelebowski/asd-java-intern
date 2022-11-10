package javaintern.dao;

import javaintern.entity.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public class TestReservationDao implements ReservationDao {
	@Override
	public Reservation readById(Integer id) {
		return null;
	}

	@Override
	public Reservation create(Reservation reservation) {
		return reservation;
	}

	@Override
	public Reservation update(Reservation reservation) {
		return reservation;
	}

	@Override
	public Boolean delete(Integer id) {
		return false;
	}
}
