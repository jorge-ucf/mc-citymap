# MasterCard code Challenge: 
## CityMap service which determines if two cities are connected.

This is a spring boot web project which tells if two cities are connected.
To build the project use the following command `./mvn clean install`, the unit and service tests are run as part of the build.
To run only the tests meaning the unit test, and service endpoint tests use the following command `./mvn test`.
To modify the connections in use for the end point edit the city.txt file in `src/main/resources/city.txt`
To start/run the project spring boot maven plugin is included just run `mvn spring-boot:run`
Once the application is started, the api documentation may be found in `localhost:8080/city-map-api-documentation.html`
The performance testing of this service would be done on the k8s pod or vm where the service would run,
using either grid gatling, jmeter or other performance tools that would run in the performance step of the pipeline. The majority
of the performance hit is on loading the main hashmap with all the city connections, thus the performance hit is in the service start time.
Once the service is up and runnng, it has a big O of O(n)=2 since it needs to perform two hashmap/set lookups. This is the most ideal
look up service as far as performance, load testing would show how many request per minute the service would be able
to serve. A way to speed up load time is to store the cityMapConnections Object and load it at start time which would
improve start up performance.
