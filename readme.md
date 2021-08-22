Microservices with spring Boot

	Level 1
		Communication and Service Discovery
		
	Level 2
		Fault Tolerance and Resilience
		***Summary***
		* Understanding possible some causes for failure in microservices.
		* Thread and pools impacts of slow microservices.
		* Timeouts and its limitations
		* Circuit breaker pattern
		* Hystrix concepts and implementation
		* Bulkhead pattern

	Level 3
		Configuration and Management
	
	
	

http://localhost:8081/catalog/foo

http://localhost:8082/movies/1234

http://localhost:8083/ratingsdata/1234

http://localhost:8083/ratingsdata/users/foo

running second instance of microservice in discovery server

generate jar using "mvn install"

E:\Spring\springboot-microservices\movie-info-service\target>java -Dserver.port=8005 -jar movie-info-service-0.0.1-SNAPSHOT.jar
