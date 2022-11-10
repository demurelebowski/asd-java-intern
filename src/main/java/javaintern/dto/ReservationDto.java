package javaintern.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDto {
	@NotNull(message = "Invalid id.")
	@Min(value = 1, message = "Invalid id.")
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
	private String state;

	@JsonProperty("from_date")
	private String fromDate;

	@JsonProperty("to_date")
	private String toDate;

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
	private String version;
}
