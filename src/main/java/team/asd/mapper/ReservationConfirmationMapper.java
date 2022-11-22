package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.ReservationConfirmation;

@Mapper
public interface ReservationConfirmationMapper {
	ReservationConfirmation readById(Integer id);

	void create(ReservationConfirmation reservationConfirmation);

	void update(ReservationConfirmation reservationConfirmation);
}
