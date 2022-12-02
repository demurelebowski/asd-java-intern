package team.asd.service;

import team.asd.constant.ArchivePriceState;
import team.asd.constant.ArchivePriceType;
import team.asd.constant.EntityType;
import team.asd.constant.ReservationState;
import team.asd.entity.ArchivePrice;
import team.asd.entity.Reservation;
import team.asd.entity.ReservationConfirmation;

import java.time.LocalDate;
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
		archivePriceMap.put(4,
				new ArchivePrice(null, EntityType.Modification, 44, "Mok2", ArchivePriceState.Created, ArchivePriceType.Price, 19.9, new Date()));

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
		reservationConfirmationMap.put(4, new ReservationConfirmation(null, 22, 43, "5333", LocalDateTime.now(), new Date()));

		return reservationConfirmationMap;
	}

	static ReservationConfirmation getTestResourcesReservationConfirmation(Integer id) {
		return testResourcesReservationConfirmation().get(id);
	}

	private static Map<Integer, Reservation> testResourcesReservation() {
		Map<Integer, Reservation> reservationMap = new HashMap<>();
		reservationMap.put(1,
				new Reservation(1, 32, 54, 22, 12, ReservationState.Confirmed, LocalDate.now(), LocalDate.now(), 77.88, 77.88, "USD", 2, null, new Date()));
		reservationMap.put(2,
				new Reservation(2, 54, 45, 65, 6, ReservationState.Confirmed, LocalDate.now(), LocalDate.now(), 66.5, 77.5, "USD", 1, null, new Date()));
		reservationMap.put(3,
				new Reservation(3, 4, 5, 6, 7, ReservationState.FullyPaid, LocalDate.now(), LocalDate.now(), 5.6, 7.5, "USD", 1, "text", new Date()));
		reservationMap.put(4,
				new Reservation(null, 4, 5, 6, 7, ReservationState.FullyPaid, LocalDate.now(), LocalDate.now(), 5.6, 7.5, "USD", 1, "text", new Date()));

		return reservationMap;
	}

	static Reservation getTestResourcesReservation(Integer id) {

		return testResourcesReservation().get(id);
	}

}
