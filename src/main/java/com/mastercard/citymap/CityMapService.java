package com.mastercard.citymap;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class CityMapService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityMapService.class);
	private CityMap cityMap;

	@Value("${cityConnectionFile}")
	private String cityConnectionFile;

	@PostConstruct
	public void init() {
		List<String> cityConnections = new ArrayList<String>();
		try {
			cityConnections = Files.readAllLines(ResourceUtils.getFile("classpath:" + cityConnectionFile).toPath());
		} catch (IOException e) {
			LOGGER.error("File of City connections was not found: "+this.cityConnectionFile+", service not started.");
			LOGGER.error(e.toString());
			System.exit(-1);
		}

		this.cityMap = new CityMap();
		for (String connectedCities : cityConnections) {
			if (!connectedCities.isEmpty()) {
				String[] cities = connectedCities.split(", ");
				if (cities.length == 2) {
					this.cityMap.addConnection(cities[0],cities[1]);
				} else {
					LOGGER.error("Invalid city connection in "+cityConnectionFile+", connection line skipped.");
				}
			} else {
				LOGGER.error("Empty line in "+cityConnectionFile+", line skipped.");
			}
		}
	}

	public String isConnected(String origin, String destination) {
		LOGGER.info("isConnected is called with origin = {} and destination = {}", origin, destination);
		try {
			if ((origin.length() == 0) || (destination.length() == 0)) {
				LOGGER.warn("isConnected is called with empty origin or destination.");
				return "no";
			}
			if (this.cityMap.isConnected(origin,destination)) {
				return "yes";
			}
		} catch (Exception e) {
			LOGGER.error("Either argument was not valid or unknown exception occured");
		}
		return "no";
	}
}