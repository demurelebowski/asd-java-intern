package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.ArchivePrice;

import java.util.List;

@Mapper
public interface ArchivePriceMapper {
    ArchivePrice readById(Integer id);

    void create(ArchivePrice archivePrice);

    void update(ArchivePrice archivePrice);

    Boolean delete(Integer id);

    void createList(List<ArchivePrice> archivePriceList);
}
