package ua.tqs.coviddata.model;

import java.sql.Date;

public class Country {
    private String continent;
    private String country;
    private int population;
    private Cases cases;
    private Deaths deaths;
    private Tests tests;
    private Date day;
    private String time;


    public Country(String continent, String country, int population, Cases cases, Deaths deaths, Tests tests, Date day, String time) {
        this.continent = continent;
        this.country = country;
        this.population = population;
        this.cases = cases;
        this.deaths = deaths;
        this.tests = tests;
        this.day = day;
        this.time = time;
    }

    public String getContinent(){
        return this.continent;
    }
    
    public String getCountry(){
        return this.country;
    }

    public int getPopulation(){
        return this.population;
    }

    public Cases getCases(){
        return this.cases;
    }

    public Deaths getDeaths(){
        return this.deaths;
    }
    
    public Tests getTests(){
        return this.tests;
    }

    public Date getDay(){
        return this.day;
    }

    public String getTime(){
        return this.time;
    }
}
