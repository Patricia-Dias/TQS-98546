package ua.tqs.coviddata.model;

public class Cases {
    private int newCases;
    private int active;
    private int critical;
    private int recovered;
    private int perMillion;
    private int total;

    public Cases(int newCases, int active, int critical, int recovered, int perMillion, int total){
        this.newCases = newCases;
        this.active = active;
        this.critical = critical;
        this.recovered = recovered;
        this.perMillion = perMillion;
        this.total = total;
    }

    public int getNewCases(){
        return newCases;
    }

    public int getActive(){
        return active;
    }

    public int getCritical(){
        return critical;
    }

    public int getRecovered(){
        return recovered;
    }
    
    public int getPerMillion(){
        return perMillion;
    }

    public int getTotal(){
        return total;
    }
}
