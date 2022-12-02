package team.asd.dao;

import team.asd.constant.ArchivePriceState;
import team.asd.constant.ArchivePriceType;
import team.asd.entity.ArchivePrice;

import java.util.List;

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

	@Override
	public void createList(List<ArchivePrice> archivePriceList) {

	}

	@Override
	public List<ArchivePrice> getListByReservationId(Integer reservationId) {
		return null;
	}

	@Override
	public List<ArchivePrice> getListByParameters(ArchivePriceType type, ArchivePriceState state, String name) {
		return null;
	}
}
