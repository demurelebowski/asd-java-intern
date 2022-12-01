package team.asd.dao;

import team.asd.entity.ArchivePrice;

import java.util.List;

public interface ArchivePriceDao {
    ArchivePrice readById(Integer id);

    void create(ArchivePrice archivePrice);

    void update(ArchivePrice archivePrice);

    Boolean delete(Integer id);

    void createList(List<ArchivePrice> archivePriceList);
}
