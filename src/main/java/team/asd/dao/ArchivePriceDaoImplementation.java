package team.asd.dao;

import org.springframework.stereotype.Repository;
import team.asd.entity.ArchivePrice;

@Repository
public class ArchivePriceDaoImplementation implements ArchivePriceDao {
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
