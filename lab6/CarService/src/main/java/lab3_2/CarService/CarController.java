package lab3_2.CarService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarManagerService service;

    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody CarDTO carDTO){
        Car car = new Car(carDTO);
        Car saved = service.save(car);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return service.getAllCars();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long id){
        Optional<Car> optional = service.getCarDetails(id);
        if (optional.isPresent()){
            Car c = optional.get();
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
