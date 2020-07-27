package com.mastercard.citymap;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connected")
public class CityMapController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityMapController.class);

	@Autowired
	CityMapService cityMapService;

	@GetMapping
	public ResponseEntity<String> isConnected(
			@RequestParam(required = true, defaultValue = "") final @NotBlank String origin,
			@RequestParam(required = true, defaultValue = "") final @NotBlank String destination) {
		return new ResponseEntity<String>(cityMapService.isConnected(origin, destination), HttpStatus.OK);
	}
}