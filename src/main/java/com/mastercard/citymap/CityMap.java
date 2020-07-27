package com.mastercard.citymap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.HashMap;

import javax.validation.constraints.NotBlank;

public class CityMap {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityMap.class);
	private HashMap<String, HashSet<String>> cityConnections = new HashMap<String, HashSet<String>>();

	private void printMap(){ //used for debug puposes
		LOGGER.info("City Map");
		for(String key : this.cityConnections.keySet()){
			LOGGER.info("city:"+key);
			StringBuilder connections = new StringBuilder();
			for(String city : this.cityConnections.get(key))
			{
				if (connections.length() != 0)
					connections.append(", ");
				connections.append(city);
			}
			LOGGER.info("connections:"+connections);
		}
	}

	public void addConnection(@NotBlank String xCity, @NotBlank String yCity) {
		LOGGER.info("Adding connection city=" + xCity + " to city=" + yCity);
		xCity = xCity.trim().toLowerCase();
		yCity = yCity.trim().toLowerCase();
		HashSet<String> xConnections = null;
		HashSet<String> yConnections = null;

		// The following is done so that a new hash set is returned, rather than null.
		if(this.cityConnections.get(xCity) == null) {
			this.cityConnections.put(xCity,new HashSet<String>());
		}
		if(this.cityConnections.get(yCity) == null) {
			this.cityConnections.put(yCity,new HashSet<String>());
		}

		xConnections = this.cityConnections.get(xCity);
		yConnections = this.cityConnections.get(yCity);

		// perform all connections on the xside
		for(String city : xConnections)
		{
			this.cityConnections.get(city).addAll(yConnections);
			this.cityConnections.get(city).add(yCity);
			this.cityConnections.get(city).remove(city);
		}
		xConnections.addAll(yConnections);
		xConnections.add(yCity);
		xConnections.remove(xCity);

		// perform all connections on the yside
		for(String city : yConnections)
		{
			this.cityConnections.get(city).addAll(xConnections);
			this.cityConnections.get(city).add(xCity);
			this.cityConnections.get(city).remove(city);
		}
		yConnections.addAll(xConnections);
		yConnections.add(xCity);
		yConnections.remove(yCity);

		// this.printMap(); // Used for connection debug
	}

	public boolean isConnected(@NotBlank String xCity, @NotBlank String yCity) {
		xCity = xCity.trim().toLowerCase();
		yCity = yCity.trim().toLowerCase();

		if (this.cityConnections.size() < 2) {
			return false; // file was empty
		}

		HashSet<String> connectedCities = this.cityConnections.get(xCity);
		if ((connectedCities != null) && (connectedCities.contains(yCity))) {
			return true;
		} else {
			return false;
		}
	}
}