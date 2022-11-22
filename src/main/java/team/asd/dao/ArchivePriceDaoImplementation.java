package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.ArchivePrice;
import team.asd.mapper.ArchivePriceMapper;

@Repository
public class ArchivePriceDaoImplementation implements ArchivePriceDao {
	private final ArchivePriceMapper archivePriceMapper;

	public ArchivePriceDaoImplementation(@Autowired ArchivePriceMapper archivePriceMapper) {
		this.archivePriceMapper = archivePriceMapper;
	}

	@Override
	public ArchivePrice readById(Integer id) {
		return archivePriceMapper.readById(id);
	}

	@Override
	public void create(ArchivePrice archivePrice) {
		archivePriceMapper.create(archivePrice);
	}

	@Override
	public void update(ArchivePrice archivePrice) {
		archivePriceMapper.update(archivePrice);
	}

	@Override
	public Boolean delete(Integer id) {
		return archivePriceMapper.delete(id);
	}
}
