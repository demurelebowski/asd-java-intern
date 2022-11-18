package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.Reservation;

@Mapper
public interface ArchivePriceMapper {
	Reservation readById(Integer id);

	void create(Reservation reservation);

	void update(Reservation reservation);

	Boolean delete(Integer id);
}
