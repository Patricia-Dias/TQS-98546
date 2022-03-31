package lab3_2.CarService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerService {
    @Autowired
    private CarRepository repo;
    
    public Car save(Car car){
        return repo.save(car);
    }

    public List<Car> getAllCars() {
        return repo.findAll();
    }

    public Optional<Car> getCarDetails(Long id){
        return repo.findById(id);
    }
}
