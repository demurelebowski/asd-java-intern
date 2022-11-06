package javaintern.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import javaintern.constant.ReservationState;
import javaintern.entity.Reservation;

import java.time.LocalDateTime;
import java.util.Date;

public class ReservationDto {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("organization_id")
	private Integer organizationId;

	@JsonProperty("agent_id")
	private Integer agentId;

	@JsonProperty("customer_id")
	private Integer customerId;

	@JsonProperty("product_id")
	private Integer productId;

	@JsonProperty("state")
	private ReservationState state;

	@JsonProperty("from_date")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime fromDate;

	@JsonProperty("to_date")
	private LocalDateTime toDate;

	@JsonProperty("price")
	private Double price;

	@JsonProperty("quote")
	private Double quote;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("guests")
	private Integer guests;

	@JsonProperty("notes")
	private String notes;

	@JsonProperty("version")
	@JsonSerialize(using = DateSerializer.class)
	private Date version;

	public ReservationDto(Reservation reservation) {
		this.id = reservation.getId();
		this.organizationId = reservation.getOrganizationId();
		this.agentId = reservation.getAgentId();
		this.customerId = reservation.getCustomerId();
		this.productId = reservation.getProductId();
		this.state = reservation.getState();
		this.fromDate = reservation.getFromDate();
		this.toDate = reservation.getToDate();
		this.price = reservation.getPrice();
		this.quote = reservation.getQuote();
		this.currency = reservation.getCurrency();
		this.guests = reservation.getGuests();
		this.notes = reservation.getNotes();
		this.version = reservation.getVersion();
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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
