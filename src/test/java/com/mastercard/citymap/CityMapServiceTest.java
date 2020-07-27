package com.mastercard.citymap;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CityMapService.class)
@ComponentScan("com.mastercard.citymap")
@AutoConfigureMockMvc
public class CityMapServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	CityMapService CityMapService;

	private void mockTest(String origin, String destination, String result) throws Exception {
		this.mockMvc
				.perform(
						MockMvcRequestBuilders
								.get("/connected")
								.param("origin", origin)
								.param("destination", destination)
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(result)));
	}

	@Test
	public void isConnectedTest_whenCitiesAreDirectlyConnected() throws Exception {
		mockTest("New York", "Boston", "yes");
	}

	@Test
	public void isConnectedTest_whenCitiesAreDirectlyConnected2() throws Exception {
		mockTest("City1", "City2", "yes");
	}

	@Test
	public void isConnectedTest_whenCitiesAreDirectlyConnectedReversed() throws Exception {
		mockTest("Boston", "New York", "yes");
	}

	@Test
	public void isConnectedTest_whenCitiesAreDirectlyConnectedReversed2() throws Exception {
		mockTest("City2", "City1", "yes");
	}

	@Test
	public void isConnectedTest_whenCitiesAreIndirectlyConnected() throws Exception {
		mockTest("Philadelphia", "Boston", "yes");
	}

	@Test
	public void isConnectedTest_whenCitiesAreIndirectlyConnected2() throws Exception {
		mockTest("City3", "City8", "yes");
	}

	@Test
	public void isConnectedTest_whenCitiesAreIndirectlyConnectedReversed() throws Exception {
		mockTest("Boston", "Philadelphia", "yes");
	}

	@Test
	public void isConnectedTest_whenCitiesAreIndirectlyConnectedReversed2() throws Exception {
		mockTest("City8", "City3", "yes");
	}

	@Test
	public void isConnectedTest_whenTwoCitiesAreNotConnected_butCitiesArePresent() throws Exception {
		mockTest("Philadelphia", "Albany", "no");
	}

	@Test
	public void isConnectedTest_whenTwoCitiesAreNotConnected_butCitiesArePresent2() throws Exception {
		mockTest("City8", "City9", "no");
	}

	@Test
	public void isConnectedTest_whenTwoCitiesAreNotConnected_andCitiesAreNotPresent() throws Exception {
		mockTest("London", "Sydney", "no");
	}

	@Test
	public void isConnectedTest_whenTwoCitiesAreNotConnected_andCitiesAreNotPresent2() throws Exception {
		mockTest("CityY", "CityX", "no");
	}
}