package team.asd.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.service.RedisClientService;

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

}
