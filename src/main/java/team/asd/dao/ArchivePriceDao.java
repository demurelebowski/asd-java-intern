package team.asd.dao;

import team.asd.constant.ArchivePriceState;
import team.asd.constant.ArchivePriceType;
import team.asd.entity.ArchivePrice;

import java.util.List;

public interface ArchivePriceDao {
	ArchivePrice readById(Integer id);

	void create(ArchivePrice archivePrice);

	void update(ArchivePrice archivePrice);

	Boolean delete(Integer id);

	void createList(List<ArchivePrice> archivePriceList);

	List<ArchivePrice> getListByReservationId(Integer reservationId);

	List<ArchivePrice> getListByParameters(ArchivePriceType type, ArchivePriceState state, String name);
}
