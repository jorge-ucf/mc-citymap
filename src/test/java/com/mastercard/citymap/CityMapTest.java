package com.mastercard.citymap;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.Assert;

import static org.springframework.util.Assert.isTrue;

@ComponentScan("com.mastercard.citymap")
public class CityMapTest {

    @Test
    public void TestDirectConnection() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        isTrue(cityMap.isConnected("cityA","cityB"));
    }

    @Test
    public void TestDirectConnectionReverse() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        isTrue(cityMap.isConnected("cityB","cityA"));
    }

    @Test
    public void TestIndirectConnectionOneHop() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityB","cityC");
        isTrue(cityMap.isConnected("cityA","cityC"));
    }

    @Test
    public void TestIndirectConnectionOneHopReverse() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityB","cityC");
        isTrue(cityMap.isConnected("cityC","cityA"));
    }

    @Test
    public void TestIndirectConnectionTwoHops() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityB","cityC");
        cityMap.addConnection("cityC","cityD");
        isTrue(cityMap.isConnected("cityA","cityD"));
    }

    @Test
    public void TestIndirectConnectionTwoHopsReverse() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityB","cityC");
        cityMap.addConnection("cityC","cityD");
        isTrue(cityMap.isConnected("cityD","cityA"));
    }

    @Test
    public void TestIndirectConnectionThreeHops() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityB","cityC");
        cityMap.addConnection("cityC","cityD");
        cityMap.addConnection("cityD","cityE");
        isTrue(cityMap.isConnected("cityA","cityE"));
    }

    @Test
    public void TestIndirectConnectionThreeHopsReverse() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityB","cityC");
        cityMap.addConnection("cityC","cityD");
        cityMap.addConnection("cityD","cityE");
        isTrue(cityMap.isConnected("cityE","cityA"));
    }

    @Test
    public void TestIndirectConnectionThreeHopsNoOrder() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityD","cityE");
        cityMap.addConnection("cityB","cityC");
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityC","cityD");
        isTrue(cityMap.isConnected("cityA","cityE"));
    }

    @Test
    public void TestIndirectConnectionThreeHopsNoOrderReverse() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityD","cityE");
        cityMap.addConnection("cityB","cityC");
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityC","cityD");
        isTrue(cityMap.isConnected("cityE","cityA"));
    }

    @Test
    public void TestNoConnection() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityB","cityC");
        cityMap.addConnection("cityD","cityE");
        isTrue(!cityMap.isConnected("cityA","cityE"));
    }

    @Test
    public void TestNoConnectionReverse() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityB","cityC");
        cityMap.addConnection("cityD","cityE");
        isTrue(!cityMap.isConnected("cityE","cityA"));
    }

    @Test
    public void TestNoConnectionUnknownCities() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityB","cityC");
        cityMap.addConnection("cityD","cityE");
        isTrue(!cityMap.isConnected("cityX","cityY"));
    }

    @Test
    public void TestNoConnectionUnknownCitiesReverse() throws Exception
    {
        CityMap cityMap = new CityMap();
        cityMap.addConnection("cityA","cityB");
        cityMap.addConnection("cityB","cityC");
        cityMap.addConnection("cityD","cityE");
        isTrue(!cityMap.isConnected("cityY","cityX"));
    }
}