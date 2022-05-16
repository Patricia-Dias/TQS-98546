package lab3_2.CarService;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "spring.jpa.hibernate.ddl-auto=create")
@AutoConfigureMockMvc
public class CarControllerTestIT {
    @Container
	public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
		.withUsername("duke")
		.withPassword("password")
		.withDatabaseName("test");

	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDb() {
        carRepository.deleteAll();
    }


    @Test
     void whenValidInput_thenCreateCar() {
        Car car1 = new Car("Toyota", "Corolla");
        RestAssuredMockMvc.mockMvc(mvc);

        RestAssuredMockMvc.given()
            .header("Content-type", "application/json")
            .and().body(car1)
            .and().post("/api/car")
            .then().statusCode(201);
    }

    @Test
     void givenCars_whenGetCars_thenStatus200()  {
        Car car1 = createTestCar("Toyota", "Corolla");
        Car car2 = createTestCar("Renault", "Clio");

        List<Car> cars = Arrays.asList(car1, car2);

        RestAssuredMockMvc.given()
            .header("Content-type", "application/json")
            .and().body(cars)
            .and().get("/api/cars")
            .then().statusCode(201)
            .and().body("maker", hasItems(car1.getMaker(), car2.getMaker()));

    }


    private Car createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        carRepository.saveAndFlush(car);
        return car;
    }

}
