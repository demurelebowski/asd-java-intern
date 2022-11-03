package javaintern.entity;

import javaintern.constant.ReservationState;

import java.time.LocalDateTime;
import java.util.Date;

public class Reservation {
	private final Integer id;
	private final Integer organizationId;
	private final Integer agentId;
	private final Integer customerId;
	private final Integer productId;
	private final ReservationState state;
	private final LocalDateTime fromDate;
	private final LocalDateTime toDate;
	private final Double price;
	private final Double quote;
	private final String currency;
	private final Integer guests;
	private final String notes;
	private final Date version;

	public Reservation(Integer id, Integer organizationId, Integer agentId, Integer customerId, Integer productId, ReservationState state, LocalDateTime fromDate, LocalDateTime toDate, Double price, Double quote,
			String currency, Integer guests, String notes, Date version) {
		this.id = id;
		this.organizationId = organizationId;
		this.agentId = agentId;
		this.customerId = customerId;
		this.productId = productId;
		this.state = state;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.price = price;
		this.quote = quote;
		this.currency = currency;
		this.guests = guests;
		this.notes = notes;
		this.version = version;
	}
}
