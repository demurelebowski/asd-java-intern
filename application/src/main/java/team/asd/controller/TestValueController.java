package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.asd.mapper.TestMapper;

@RestController
@RequestMapping(value = "/test/value")
public class TestValueController {
	@Autowired
	public TestMapper testMapper;

	@PostMapping("/{value}")
	public void postTestValue(@PathVariable String value) {
		testMapper.insertValue(value);
	}
}
