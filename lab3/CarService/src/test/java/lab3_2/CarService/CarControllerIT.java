package lab3_2.CarService;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase

public class CarControllerIT {
    // will need to use the server port for the invocation url
    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repo;

    @AfterEach
    public void resetDb() {
        repo.deleteAll();
    }


    @Test
     void whenValidInput_thenCreateCar() {
        Car car1 = new Car("Toyota", "Corolla");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/car", car1, Car.class);

        List<Car> found = repo.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly(car1.getModel());
    }

    @Test
     void givenCars_whenGetCars_thenStatus200()  {
        createTestCar("Toyota", "Corolla");
        createTestCar("Renault", "Clio");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("Corolla", "Clio");

    }


    private void createTestCar(String maker, String model) {
        Car car = new Car(maker, model);
        repo.saveAndFlush(car);
    }

}
