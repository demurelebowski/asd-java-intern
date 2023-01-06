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
	@PostMapping("/")
	public String saveValueByKey(@RequestParam(name = "key") @ApiParam(value = "key", example = "keyTemp") String key,
			@RequestParam(name = "value") @ApiParam(value = "value", example = "My value") String value) {
		return redisClientService.saveValueByKey(key, value);
	}

	@ApiOperation(value = "Saves list by key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved"), @ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@PostMapping("/list")
	public String saveList(@RequestParam(name = "key") @ApiParam(value = "key", example = "keyTemp") String key, @RequestBody List<String> list) {
		return redisClientService.saveList(key, list);
	}

	@ApiOperation(value = "Get a list by key", notes = "Returns list as per the key")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved"),
			@ApiResponse(code = 400, message = "Invalid parameter was provided") })
	@GetMapping("/list/{key}")
	List<String> retrieveList(@PathVariable @ApiParam(value = "key", example = "keyTest") String key) {
		return redisClientService.retrieveList(key);
	}
}
