package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDto {
	@Min(value = 1, message = "id {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "Reservation ID", example = "1", required = true)
	@JsonProperty("id")
	private Integer id;

	@Min(value = 1, message = "organization_id {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "Organization ID", example = "7", required = true)
	@JsonProperty("organization_id")
	private Integer organizationId;

	@Min(value = 1, message = "agent_id {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "Agent ID", example = "6", required = true)
	@JsonProperty("agent_id")
	private Integer agentId;

	@Min(value = 1, message = "customer_id {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "Customer ID", example = "26", required = true)
	@JsonProperty("customer_id")
	private Integer customerId;

	@Min(value = 1, message = "product_id {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "Product ID", example = "87", required = true)
	@JsonProperty("product_id")
	private Integer productId;

	@JsonProperty("state")
	@ApiModelProperty(notes = "State of record", example = "Initial, Confirmed, FullyPaid, Cancelled")
	private String state;

	@JsonProperty("from_date")
	@ApiModelProperty(notes = "Date when booking starts (included)", example = "2022-01-01", required = true)
	private String fromDate;

	@JsonProperty("to_date")
	@ApiModelProperty(notes = "Date when booking ends (excluded). This day will be available for another booking", example = "2022-12-01", required = true)
	private String toDate;

	@Min(value = 0, message = "price {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "NetRate price for current booking (without commissions, depends on settings)", example = "45.66", required = true)
	@JsonProperty("price")
	private Double price;

	@Min(value = 0, message = "quote {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "Full price for reservation with all the commissions. It's the price the customer pays", example = "55.66", required = true)
	@JsonProperty("quote")
	private Double quote;

	@Size(max = 3, message = "Currency is invalid.")
	@ApiModelProperty(notes = "Currency code of payment for reservation", example = "USD", required = true)
	@JsonProperty("currency")
	private String currency;

	@Min(value = 1, message = "guests {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "Number of guests for current reservation", example = "2")
	@JsonProperty("guests")
	private Integer guests;

	@JsonProperty("notes")
	@ApiModelProperty(notes = "Notes or exceptions related to reservation or its processing")
	private String notes;

	@JsonProperty("version")
	@ApiModelProperty(notes = "Date when record was updated last time", example = "2022-12-03 16:26:39")
	private String version;
}
