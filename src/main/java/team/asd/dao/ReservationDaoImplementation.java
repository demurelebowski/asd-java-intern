package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.constant.ReservationState;
import team.asd.entity.Reservation;
import team.asd.mapper.ReservationMapper;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ReservationDaoImplementation implements ReservationDao {

	private final ReservationMapper reservationMapper;

	public ReservationDaoImplementation(@Autowired ReservationMapper reservationMapper) {
		this.reservationMapper = reservationMapper;
	}

	@Override
	public Reservation readById(Integer id) {
		return reservationMapper.readById(id);
	}

	@Override
	public void create(Reservation reservation) {
		reservationMapper.create(reservation);
	}

	@Override
	public void update(Reservation reservation) {
		reservationMapper.update(reservation);
	}

	@Override
	public Boolean delete(Integer id) {
		return reservationMapper.delete(id);
	}

	@Override
	public List<Reservation> getListByParameters(Integer productId, Integer organizationId, Integer agentId) {
		return reservationMapper.getListByParameters(productId, organizationId, agentId);
	}

	@Override
	public List<Reservation> getListByDates(LocalDate fromDate, LocalDate toDate, ReservationState state) {
		return reservationMapper.getListByDates(fromDate, toDate, state);
	}
}
