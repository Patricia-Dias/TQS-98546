package lab3_2.CarService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {
    
    @Mock( lenient = true)
    private CarRepository repo;

    @InjectMocks
    private CarManagerService service;

    private Car car1;
    private Car car2;

    @BeforeEach
    public void setUp() {

        car1 = new Car("Toyota", "Corolla");
        car1.setCardId(1L);
		car2 = new Car("Renault", "Clio");
        car2.setCardId(2L);

        List<Car> allCars = Arrays.asList(car1, car2);
        System.out.println(allCars);

        Mockito.when(repo.findById(car1.getCardId())).thenReturn(Optional.of(car1));
        Mockito.when(repo.findById(-1L)).thenReturn(Optional.empty());
        Mockito.when(repo.findAll()).thenReturn(allCars);

    }

    @Test
     void whenSearchValidID_thenCarShouldBeFound() {
        System.out.println(car1.getCardId());
        Optional<Car> found = service.getCarDetails(car1.getCardId());
        System.out.println(found);

        assertThat(found.get().getModel()).isEqualTo(car1.getModel());
        verifyFindByIdIsCalledOnce();
    }

    @Test
     void whenSearchInvalidID_thenCarShouldNotBeFound() {
        Optional<Car> fromDb = service.getCarDetails(-1L);
        assertThat(fromDb).isEmpty();

        verifyFindByIdIsCalledOnce();
    }

    @Test
     void given2Cars_whenGetAll_thenReturn2Records() {
        car1 = new Car("Toyota", "Corolla");
		car2 = new Car("Renault", "Clio");

        List<Car> allCars = service.getAllCars();
        verifyFindAllCarsIsCalledOnce();
        assertThat(allCars).hasSize(2).extracting(Car::getModel).contains(car1.getModel(), car2.getModel());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(repo, VerificationModeFactory.times(1)).findAll();
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(repo, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
    }

}
