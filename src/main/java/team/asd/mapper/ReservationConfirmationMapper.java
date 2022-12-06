package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.ReservationConfirmation;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ReservationConfirmationMapper {
	ReservationConfirmation readById(Integer id);

	void create(ReservationConfirmation reservationConfirmation);

	void update(ReservationConfirmation reservationConfirmation);

	Boolean delete(Integer id);

	List<ReservationConfirmation> getListByReservationId(Integer reservationId);

	List<ReservationConfirmation> getListByConfirmationIdAndDateRange(String confirmationId, LocalDateTime dateStart, LocalDateTime dateEnd);

}
