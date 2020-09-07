package org.medeiros.adapter.controller.v1;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/message/")
public class MessageController {

	@PostMapping
	public void create() {

	}

	@GetMapping
	public String find() {
		return "OK";
	}

	@DeleteMapping
	public void delete() {

	}

}
