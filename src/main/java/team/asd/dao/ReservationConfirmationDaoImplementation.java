package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.ReservationConfirmation;
import team.asd.mapper.ReservationConfirmationMapper;

import java.time.LocalDateTime;
import java.util.List;

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

	@Override
	public List<ReservationConfirmation> getListByReservationId(Integer reservationId) {
		return reservationConfirmationMapper.getListByReservationId(reservationId);
	}

	@Override
	public List<ReservationConfirmation> getListByConfirmationIdAndDateRange(String confirmationId, LocalDateTime dateStart, LocalDateTime dateEnd) {
		return reservationConfirmationMapper.getListByConfirmationIdAndDateRange(confirmationId, dateStart, dateEnd);
	}
}
