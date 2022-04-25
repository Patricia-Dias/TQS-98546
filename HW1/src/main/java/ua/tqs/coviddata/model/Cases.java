package ua.tqs.coviddata.model;

public class Cases {
    private int newCases;
    private int active;
    private int recovered;
    private int total;

    public Cases(int newCases, int active, int recovered, int total){
        this.newCases = newCases;
        this.active = active;
        this.recovered = recovered;
        this.total = total;
    }

    public int getNewCases(){
        return newCases;
    }

    public int getActive(){
        return active;
    }

    public int getRecovered(){
        return recovered;
    }

    public int getTotal(){
        return total;
    }
}
