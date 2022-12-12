package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.constant.ReservationState;
import team.asd.entity.ArchivePrice;
import team.asd.entity.Reservation;
import team.asd.entity.ReservationConfirmation;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReservationMapper {
    Reservation readById(Integer id);

    void create(Reservation reservation);

    void update(Reservation reservation);

    Boolean delete(Integer id);

    List<Reservation> getListByParameters(Integer productId, Integer organizationId, Integer agentId);

    List<Reservation> getListByDates(LocalDate fromDate, LocalDate toDate, ReservationState state);

    List<ArchivePrice> getArchivePrice(Integer id);

    ReservationConfirmation getReservationConfirmation(Integer reservationId);
}
