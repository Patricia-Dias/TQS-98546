package ua.tqs.coviddata.model;

import java.util.HashSet;
import java.util.Set;

public class Continent {
    private String continent;
    private static Set<Object> children = new HashSet<>();

    public Continent(String continent){
        this.continent=continent;
        synchronized (this){
            children.add(this);
        }
    }

    public String getContinent(){
        return this.continent;
    }

    public Set getCountries(){
        return this.children;
    }

    @Override
    public String toString(){
        return ("Instance of Class " + getClass().getName());
    }
    
}
