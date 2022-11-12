package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	@JsonProperty("id")
	private Integer id;

	@Min(value = 1, message = "organization_id {javax.validation.constraints.Min.message}")
	@JsonProperty("organization_id")
	private Integer organizationId;

	@Min(value = 1, message = "agent_id {javax.validation.constraints.Min.message}")
	@JsonProperty("agent_id")
	private Integer agentId;

	@Min(value = 1, message = "customer_id {javax.validation.constraints.Min.message}")
	@JsonProperty("customer_id")
	private Integer customerId;

	@Min(value = 1, message = "product_id {javax.validation.constraints.Min.message}")
	@JsonProperty("product_id")
	private Integer productId;

	@JsonProperty("state")
	private String state;

	@JsonProperty("from_date")
	private String fromDate;

	@JsonProperty("to_date")
	private String toDate;

	@Min(value = 0, message = "price {javax.validation.constraints.Min.message}")
	@JsonProperty("price")
	private Double price;

	@Min(value = 0, message = "quote {javax.validation.constraints.Min.message}")
	@JsonProperty("quote")
	private Double quote;

	@Size(max = 3, message = "Currency is invalid.")
	@JsonProperty("currency")
	private String currency;

	@Min(value = 1, message = "guests {javax.validation.constraints.Min.message}")
	@JsonProperty("guests")
	private Integer guests;

	@JsonProperty("notes")
	private String notes;

	@JsonProperty("version")
	private String version;
}
