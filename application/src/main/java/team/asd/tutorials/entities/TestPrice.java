package team.asd.tutorials.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestPrice implements IsPrice {

	private BigDecimal price;

	private LocalDate fromDate;

	private LocalDate toDate;

	private String currency;

	@Override
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public LocalDate getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	@Override
	public LocalDate getToDate() {
		return this.toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	@Override
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
