package javaintern.entity;

import javaintern.constant.ReservationState;
import javaintern.dto.ReservationDto;

import java.time.LocalDateTime;
import java.util.Date;

public class Reservation {
	private Integer id;
	private Integer organizationId;
	private Integer agentId;
	private Integer customerId;
	private Integer productId;
	private ReservationState state;
	private LocalDateTime fromDate;
	private LocalDateTime toDate;
	private Double price;
	private Double quote;
	private String currency;
	private Integer guests;
	private String notes;
	private Date version;

	public Reservation(ReservationDto reservationDto) {
		this.id = reservationDto.getId();
		this.organizationId = reservationDto.getOrganizationId();
		this.agentId = reservationDto.getAgentId();
		this.customerId = reservationDto.getCustomerId();
		this.productId = reservationDto.getProductId();
		this.state = reservationDto.getState();
		this.fromDate = reservationDto.getFromDate();
		this.toDate = reservationDto.getToDate();
		this.price = reservationDto.getPrice();
		this.quote = reservationDto.getQuote();
		this.currency = reservationDto.getCurrency();
		this.guests = reservationDto.getGuests();
		this.notes = reservationDto.getNotes();
		this.version = reservationDto.getVersion();
	}

	public Reservation() {

	}

	public Integer getId() {
		return id;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Integer getProductId() {
		return productId;
	}

	public ReservationState getState() {
		return state;
	}

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public LocalDateTime getToDate() {
		return toDate;
	}

	public Double getPrice() {
		return price;
	}

	public Double getQuote() {
		return quote;
	}

	public String getCurrency() {
		return currency;
	}

	public Integer getGuests() {
		return guests;
	}

	public String getNotes() {
		return notes;
	}

	public Date getVersion() {
		return version;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setState(ReservationState state) {
		this.state = state;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setQuote(Double quote) {
		this.quote = quote;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setGuests(Integer guests) {
		this.guests = guests;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setVersion(Date version) {
		this.version = version;
	}
}
