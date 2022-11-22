package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.ArchivePrice;

@Mapper
public interface ArchivePriceMapper {
	ArchivePrice readById(Integer id);

	void create(ArchivePrice archivePrice);

	void update(ArchivePrice archivePrice);

	Boolean delete(Integer id);
}
