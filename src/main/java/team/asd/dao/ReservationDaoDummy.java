package team.asd.dao;

import team.asd.entity.Reservation;

public class ReservationDaoDummy implements ReservationDao {
	@Override
	public Reservation readById(Integer id) {
		return null;
	}

	@Override
	public void create(Reservation reservation) {
	}

	@Override
	public void update(Reservation reservation) {
	}

	@Override
	public Boolean delete(Integer id) {
		return false;
	}
}
