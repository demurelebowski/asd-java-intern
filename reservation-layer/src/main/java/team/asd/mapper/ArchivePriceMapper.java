package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.constant.ArchivePriceState;
import team.asd.constant.ArchivePriceType;
import team.asd.entity.ArchivePrice;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ArchivePriceMapper {
	ArchivePrice readById(Integer id);

	void create(ArchivePrice archivePrice);

	void update(ArchivePrice archivePrice);

	Boolean delete(Integer id);

	void createList(List<ArchivePrice> archivePriceList);

	List<ArchivePrice> getListByReservationId(Integer reservationId);

	List<ArchivePrice> getListByParameters(ArchivePriceType type, ArchivePriceState state, String name);

	List<ArchivePrice> getListByReservationFromDateAtLeast(LocalDate dateStart);
}
