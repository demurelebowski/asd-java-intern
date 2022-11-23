package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.ReservationConfirmation;
import team.asd.mapper.ReservationConfirmationMapper;

@Repository
public class ReservationConfirmationDaoImplementation implements ReservationConfirmationDao {
	private final ReservationConfirmationMapper reservationConfirmationMapper;

	public ReservationConfirmationDaoImplementation(@Autowired ReservationConfirmationMapper reservationConfirmationMapper) {
		this.reservationConfirmationMapper = reservationConfirmationMapper;
	}

	@Override
	public ReservationConfirmation readById(Integer id) {
		return reservationConfirmationMapper.readById(id);
	}

	@Override
	public void create(ReservationConfirmation reservationConfirmation) {
		reservationConfirmationMapper.create(reservationConfirmation);
	}

	@Override
	public void update(ReservationConfirmation reservationConfirmation) {
		reservationConfirmationMapper.update(reservationConfirmation);
	}

	@Override
	public Boolean delete(Integer id) {
		return reservationConfirmationMapper.delete(id);
	}
}
