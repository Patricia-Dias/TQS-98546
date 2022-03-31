package lab3_2.CarService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @Column
    private String maker;

    @Column
    private String model;

    public Car(){};

    public Car(String maker, String model){
        this.maker = maker;
        this.model = model;
    }

    public Long getCardId(){
        return this.carId;
    }

    public String getMaker(){
        return this.maker;
    }

    public String getModel(){
        return this.model;
    }

    public void setCardId(Long id){
        this.carId=id;
    }

    public void setMaker(String maker){
        this.maker = maker;
    }

    public void setModel(String model){
        this.model=model;
    }
}
