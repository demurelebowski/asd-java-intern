package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.Reservation;

@Mapper
public interface ReservationMapper {
	Reservation readById(Integer id);

	Reservation create(Reservation reservation);

	Reservation update(Reservation reservation);

	Boolean delete(Integer id);
}
