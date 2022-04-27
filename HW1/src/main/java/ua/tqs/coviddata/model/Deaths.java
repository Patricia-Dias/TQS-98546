package ua.tqs.coviddata.model;

public class Deaths {
    private int newDeaths;
    private int perMillion;
    private int total;


    public Deaths(int newDeaths, int perMillion, int total) {
        this.newDeaths = newDeaths;
        this.perMillion = perMillion;
        this.total = total;
    }

    public int getNewDeaths(){
        return this.newDeaths;
    }
    
    public int getPerMillion(){
        return this.perMillion;
    }

    public int getTotal(){
        return this.total;
    }
}
