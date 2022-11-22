package team.asd.dao;

import team.asd.entity.ArchivePrice;

public interface ArchivePriceDao {
	ArchivePrice readById(Integer id);

	void create(ArchivePrice archivePrice);

	void update(ArchivePrice archivePrice);

	Boolean delete(Integer id);
}
