package team.asd.dao;

import team.asd.entity.ArchivePrice;

public class ArchivePriceDaoDummy implements ArchivePriceDao {
	@Override
	public ArchivePrice readById(Integer id) {
		return null;
	}

	@Override
	public void create(ArchivePrice archivePrice) {

	}

	@Override
	public void update(ArchivePrice archivePrice) {

	}

	@Override
	public Boolean delete(Integer id) {
		return null;
	}
}
