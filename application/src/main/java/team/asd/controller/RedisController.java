package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.service.RedisClientService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cache")
@ApiOperation("Redis API")
public class RedisController {
	@Autowired
	public RedisClientService redisClientService;

	@ApiOperation(value = "Get a value by key", notes = "Returns value as per the key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/{key}")
	public String readById(@PathVariable @ApiParam(value = "key", example = "keyTest") String key) {
		return redisClientService.readByKey(key);
	}

	@ApiOperation(value = "Saves value by key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved"), @ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@PostMapping("/{key}")
	public String saveValueByKey(@PathVariable @ApiParam(value = "key", example = "keyTest") String key,
			@RequestParam(name = "value") @ApiParam(value = "value", example = "My value") String value,
			@RequestParam(required = false, name = "expireDate") @ApiParam(value = "expireDate", example = "60") Long expireDate) {
		if (expireDate != null) {
			return redisClientService.saveValueWithExpireDate(key, value, expireDate);
		}
		return redisClientService.saveValueByKey(key, value);
	}

	@ApiOperation(value = "Saves list by key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved"), @ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@PostMapping("/list")
	public void saveList(@RequestParam(name = "key") @ApiParam(value = "key", example = "keyTemp") String key, @RequestBody List<String> list) {
		redisClientService.saveList(key, list);
	}

	@ApiOperation(value = "Get a list by key", notes = "Returns list as per the key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/list/{key}")
	List<String> retrieveList(@PathVariable @ApiParam(value = "key", example = "keyTest") String key) {
		return redisClientService.retrieveList(key);
	}

	@ApiOperation(value = "Saves element into a list by key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved"), @ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@PostMapping("/list/{key}")
	public Long saveElementIntoList(@PathVariable @ApiParam(value = "key", example = "keyTest") String key,
			@RequestParam(name = "value") @ApiParam(value = "value", example = "3") String value) {
		return redisClientService.saveElementIntoList(key, value);
	}

	@ApiOperation(value = "Saves value in a HashMap by key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved"), @ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@PostMapping("/hashmap/{primaryKey}")
	public Long saveValueInHashMap(@PathVariable @ApiParam(value = "primaryKey", example = "keyTest") String primaryKey,
			@RequestParam(name = "value") @ApiParam(value = "value", example = "3") String value,
			@RequestParam(name = "secondaryKey") @ApiParam(value = "secondaryKey", example = "secondaryKey") String secondaryKey) {
		return redisClientService.saveValueInHashMap(primaryKey, secondaryKey, value);
	}

	@ApiOperation(value = "Get a value from HashMap by key", notes = "Returns value as per the key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/hashmap/{primaryKey}")
	String retrieveValueFromHashMap(@PathVariable @ApiParam(value = "primaryKey", example = "keyTest") String primaryKey,
			@RequestParam(name = "secondaryKey") @ApiParam(value = "secondaryKey", example = "secondaryKey") String secondaryKey) {
		return redisClientService.retrieveValueFromHashMap(primaryKey, secondaryKey);
	}

	@ApiOperation(value = "Get a HashMap by key", notes = "Returns HashMap as per the key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/get_hashmap/{primaryKey}")
	Map<String, String> retrieveValueFromHashMap(@PathVariable @ApiParam(value = "primaryKey", example = "keyTest") String primaryKey) {
		return redisClientService.retrieveValueFromHashMap(primaryKey);
	}

	@ApiOperation(value = "Checks if provided dates are free and saves calculated quote in Redis.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved"), @ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@PostMapping("/calculated_quote/{productId}")
	public String saveQuoteCalculation(@PathVariable @ApiParam(value = "productId", example = "456") Integer productId,
			@RequestParam(name = "from_date") @ApiParam(value = "from_date", example = "2022-01-01") String fromDate,
			@RequestParam(name = "to_date") @ApiParam(value = "to_date", example = "2022-01-22") String toDate) {
		return redisClientService.saveQuoteCalculation(productId, fromDate, toDate);
	}
}
