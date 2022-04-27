package ua.tqs.coviddata.model;

public class Tests {
    private int perMillion;
    private int total;


    public Tests(int perMillion, int total) {
        this.perMillion = perMillion;
        this.total = total;
    }
    
    public int getPerMillion(){
        return this.perMillion;
    }

    public int getTotal(){
        return this.total;
    }
}
