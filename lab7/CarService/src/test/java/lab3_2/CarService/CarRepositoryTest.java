package lab3_2.CarService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class CarRepositoryTest {
    // get a test-friendly Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repo;

    @Test
    void whenFindCarByExistingId_thenReturnClioCar() {
        Car clio = new Car("Renault", "Clio");

        entityManager.persistAndFlush(clio); //ensure data is persisted at this point

        // test the query method of interest
        Car found = repo.findById(clio.getCardId()).get();
        assertThat( found ).isEqualTo(clio);
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car fromDb = repo.findById(-111L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfEmployees_whenFindAll_thenReturnAllEmployees() {
        Car car1 = new Car("Toyota", "Corolla");
		Car car2 = new Car("Renault", "Clio");

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.flush();

        List<Car> allEmployees = repo.findAll();

        assertThat(allEmployees).hasSize(2).extracting(Car::getModel).containsOnly(car1.getModel(), car2.getModel());
    }

}
