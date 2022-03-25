package lab2_1.Stocks;

/**
 * Stock
 */
public class Stock {
    private String label;
    private Integer quantity;
    public Stock(String label, Integer quantity){
        this.label = label;
        this.quantity = quantity;
    }
    //getters
    public String getLabel(){
        return label;
    }
    public Integer getQuantity(){
        return quantity;
    }
    //setters
    public void setLabel(String l){
        this.label=l;
    }
    public void setQuantity(Integer q){
        this.quantity=q;
    }
}