package lab3_2.CarService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(CarController.class)
class CarControllerTest {

	@Autowired
	private MockMvc mvnForTests;

	@MockBean
	private CarManagerService service;

	private Car car1;
	private Car car2;

	@BeforeEach
	public void setUp() {
		car1 = new Car("Toyota", "Corolla");
        car1.setCardId(1L);
		car2 = new Car("Renault", "Clio");
        car2.setCardId(2L);
	}
	

	@Test
	void whenPostCar_thenCreateCar() throws Exception{

		when(service.save(Mockito.any())).thenReturn(car1);

		mvnForTests.perform(MockMvcRequestBuilders.post("/api/car")
			.contentType(MediaType.APPLICATION_JSON)
			.content(lab3_2.CarService.JSONUtil.toJson(car1)))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.maker", Matchers.is(car1.getMaker())));
		verify(service, times(1)).save(Mockito.any());
	}

	@Test
	public void whenGetCars_thenReturnJsonArray() throws Exception{
		// Car car1 = new Car("Toyota", "Corolla");
		// Car car2 = new Car("Renault", "Clio");
		List<Car> allCars = Arrays.asList(car1, car2);
		when(service.getAllCars()).thenReturn(allCars);

		mvnForTests.perform(MockMvcRequestBuilders.get("/api/cars")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].model", is(car1.getModel())))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].model", is(car2.getModel())));
		
		verify(service, times(1)).getAllCars();
	}

	@Test
	public void whenGetCarId_thenReturnJson() throws Exception{
		// Car car = new Car("Toyota", "Corolla");
		when(service.getCarDetails(Long.valueOf(1))).thenReturn(Optional.of(car1));

		mvnForTests.perform(MockMvcRequestBuilders.get("/api/car/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.maker", is(car1.getMaker())));

			verify(service, times(1)).getCarDetails(Long.valueOf(1));
	}

}
