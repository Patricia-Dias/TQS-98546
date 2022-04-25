package ua.tqs.coviddata.model;

public class Country extends Continent {
    private String country;
    private int population;
    private Cases cases;


    public Country(String continent, String country, int population, Cases cases) {
        super(continent);
        this.country = country;
        this.population = population;
        this.cases = cases;
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
