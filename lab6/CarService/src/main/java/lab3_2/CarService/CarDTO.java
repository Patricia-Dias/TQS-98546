package lab3_2.CarService;

public class CarDTO {
    private String maker;
    private String model;

    public CarDTO(String maker, String model){
        this.maker = maker;
        this.model = model;
    }

    public String getMaker(){
        return this.maker;
    }
    public void setMaker(String maker){
        this.maker = maker;
    }

    public String getModel(){
        return this.model;
    }
    public void setModel(String model){
        this.model = model;
    }
}
