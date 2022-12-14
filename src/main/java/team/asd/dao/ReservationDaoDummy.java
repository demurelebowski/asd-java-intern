package team.asd.dao;

import team.asd.constant.ReservationState;
import team.asd.dto.ReservationReportDto;
import team.asd.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public List<Reservation> getListByParameters(Integer productId, Integer organizationId, Integer agentId) {
        return null;
    }

    @Override
    public List<Reservation> getListByDates(LocalDate fromDate, LocalDate toDate, ReservationState state) {
        return null;
    }

    @Override
    public List<ReservationReportDto> getReservationReport(Integer reservationId, Integer firstRow, Integer limit) {
        return null;
    }
}
