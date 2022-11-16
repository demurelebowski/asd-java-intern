package team.asd.dao;

import team.asd.entity.ArchivePrice;
import team.asd.entity.Reservation;

public interface ArchivePriceDao {
	Reservation readById(Integer id);

	void create(ArchivePrice archivePrice);

	void update(ArchivePrice archivePrice);

	Boolean delete(Integer id);
}
