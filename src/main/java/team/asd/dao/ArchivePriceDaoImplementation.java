package team.asd.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.constant.ArchivePriceState;
import team.asd.constant.ArchivePriceType;
import team.asd.entity.ArchivePrice;
import team.asd.mapper.ArchivePriceMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

	@Override
	public void createList(@Param("list") List<ArchivePrice> archivePriceList) {
		archivePriceMapper.createList(archivePriceList);
	}

	@Override
	public List<ArchivePrice> getListByReservationId(Integer reservationId) {
		return archivePriceMapper.getListByReservationId(reservationId);
	}

	@Override
	public List<ArchivePrice> getListByParameters(ArchivePriceType type, ArchivePriceState state, String name) {
		return archivePriceMapper.getListByParameters(type, state, name);
	}

	@Override
	public List<ArchivePrice> getListByReservationFromDateAtLeast(LocalDate date) {
		return archivePriceMapper.getListByReservationFromDateAtLeast(date);
	}

	@Override
	public String delayedUpdate(ArchivePrice archivePrice) {
		try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException ie) {
			Thread.currentThread()
					.interrupt();
		}
		archivePriceMapper.update(archivePrice);
		return null;
	}
}
