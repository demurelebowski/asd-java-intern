package team.asd.service;

import team.asd.constant.ArchivePriceState;
import team.asd.constant.ArchivePriceType;
import team.asd.constant.EntityType;
import team.asd.entity.ArchivePrice;
import team.asd.entity.ReservationConfirmation;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestResources {
	private static Map<Integer, ArchivePrice> testResourcesArchivePrice() {
		Map<Integer, ArchivePrice> archivePriceMap = new HashMap<>();
		archivePriceMap.put(1, new ArchivePrice(1, EntityType.Payment, 4, "Mok", ArchivePriceState.Created, ArchivePriceType.Price, 9.9, new Date()));
		archivePriceMap.put(2, new ArchivePrice(2, EntityType.Reservation, 55, "Non", ArchivePriceState.Created, ArchivePriceType.Fee, 11.0, new Date()));
		archivePriceMap.put(3, new ArchivePrice(3, EntityType.Modification, 44, "Mok2", ArchivePriceState.Created, ArchivePriceType.Price, 19.9, new Date()));
		return archivePriceMap;
	}

	static ArchivePrice getTestArchivePrice(Integer id) {
		return testResourcesArchivePrice().get(id);
	}

	private static Map<Integer, ReservationConfirmation> testResourcesReservationConfirmation() {
		Map<Integer, ReservationConfirmation> reservationConfirmationMap = new HashMap<>();
		reservationConfirmationMap.put(1, new ReservationConfirmation(1, 2, 34, "0345", LocalDateTime.now(), new Date()));
		reservationConfirmationMap.put(2, new ReservationConfirmation(2, 11, 23, "234", LocalDateTime.now(), new Date()));
		reservationConfirmationMap.put(3, new ReservationConfirmation(3, 22, 43, "5333", LocalDateTime.now(), new Date()));

		return reservationConfirmationMap;
	}

	static ReservationConfirmation getTestResourcesReservationConfirmation(Integer id) {
		return testResourcesReservationConfirmation().get(id);
	}
}
