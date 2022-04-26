package ua.tqs.coviddata.model;

public class Country {
    private String country;
    private int population;
    private Cases cases;
    private String continent;


    public Country(String continent, String country, int population, Cases cases) {
        this.continent = continent;
        this.country = country;
        this.population = population;
        this.cases = cases;
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
}
