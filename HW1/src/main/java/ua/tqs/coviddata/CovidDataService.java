package ua.tqs.coviddata;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ua.tqs.coviddata.model.Cases;
import ua.tqs.coviddata.model.Country;
import ua.tqs.coviddata.model.Deaths;
import ua.tqs.coviddata.model.Tests;

@Service
public class CovidDataService {
    private final String HOST = "covid-193.p.rapidapi.com";
    private final String URL = "https://"+HOST;
    private final String KEY = "92de5aad8fmsh0c2550784af2ed1p1a93f1jsnbe09eaa6ac3d";
    private final String EXCEPTION = "Exception. ";
    private Logger logger = Logger.getLogger(CovidDataService.class.getName());
    private final long ttl = 60*2L;   // 2 minutes
    private Cache cache = new Cache(ttl);      

    public ResponseEntity<String> getAllCountries() {
        logger.info("service.getAllCountries() was called.");
        try {
            URI url = new URI(URL+"/countries");
            return getFromCache(url);
        }
        catch (URISyntaxException e) {
            logger.log(Level.SEVERE, EXCEPTION+"String couldn't be parsed as a URI reference.", e);
            Thread.currentThread().interrupt();
        }
        catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, EXCEPTION, e);
            Thread.currentThread().interrupt();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
    }

    public ResponseEntity<String> getCovidStatistics(String country)  {
        logger.info("service.getCovidInfo() was called.");
        String url_str=URL+"/statistics";
        if (country!=null){
            logger.info("country is not null.");
            url_str += "?country="+ country;
        }
        try {
            URI url = new URI(url_str);
            return getFromCache(url);
            
        } catch (URISyntaxException e) {
            logger.log(Level.SEVERE, EXCEPTION+"String couldn't be parsed as a URI reference.", e);
            Thread.currentThread().interrupt();
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, EXCEPTION, e);
            Thread.currentThread().interrupt();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getCovidHistory(String country, String day) {
        logger.info("service.getCovidInfoCountryAndDate() was called.");
        String url_str=URL+"/history?country="+country;
        if (day!=null)
            url_str +=  "&day="+day;
        try {
            URI url = new URI(url_str);
            return getFromCache(url);
            
        } catch (URISyntaxException e) {
            logger.log(Level.SEVERE, EXCEPTION+"String couldn't be parsed as a URI reference.", e);
            Thread.currentThread().interrupt();
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, EXCEPTION, e);
            Thread.currentThread().interrupt();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> getFromAPI(URI url) throws IOException, InterruptedException{
        logger.info("GET request was called.");
        HttpRequest request = HttpRequest.newBuilder()
            .uri(url)
            .header("X-RapidAPI-Host", HOST)
            .header("X-RapidAPI-Key", KEY)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("Get response was successful.");
        JSONObject json = new JSONObject(response.body());
        JSONArray responseArray = json.getJSONArray("response");

        // ArrayList<Country> result = new ArrayList<>();

        // for (int idx_responseArray=0; idx_responseArray<responseArray.length(); idx_responseArray++){
        //     JSONObject object = responseArray.getJSONObject(idx_responseArray);
        //     Country country = getCountryFromJSON(object);
        //     result.add(country);
        // }
        // System.out.println(json);
        // // responseArray.get
        // System.out.println(responseArray);
        // Object continent = responseArray.getJSONObject(0).get("continent");
        // System.out.println(continent);
        //convert to models
        return new ResponseEntity<>(responseArray.toString(), HttpStatus.OK);
    }

    // private Country getCountryFromJSON(JSONObject object) {
    //     String continent = object.get("continent").toString();
    //     String country = object.get("country").toString();
    //     System.out.println(country);
    //     String population_str = object.get("population").toString();
    //     Integer population = Integer.parseInt(population_str);
    //     Cases cases = getCasesFromJSON(object.getJSONObject("cases"));
    //     Deaths deaths = getDeathsFromJSON(object);
    //     Tests tests = getTestsFromJSON(object);
    //     String day_str = object.get("day").toString();
    //     Date day = Date.valueOf(day_str);
    //     String time = object.get("time").toString();
    //     return new Country(continent, country, population, cases, deaths, tests, day, time);
    // }

    // private Cases getCasesFromJSON(JSONObject json) {
    //     System.out.println(json);
    //     String newCases_str = json.get("new").toString();
    //     Integer newCases= ((newCases_str=="null") ? -1 : Integer.parseInt(newCases_str.split("+")[1]));
    //     String active_str = json.get("active").toString();
    //     Integer active = ((active_str=="null") ? -1 : Integer.parseInt(active_str));
    //     String critical_str = json.get("critical").toString();
    //     Integer critical = ((critical_str=="null") ? -1 :Integer.parseInt(critical_str));
    //     String recovered_str = json.get("recovered").toString();
    //     Integer recovered = ((recovered_str=="null") ? -1 : Integer.parseInt(recovered_str));
    //     String perMillion_str = json.get("1M_pop").toString();
    //     Integer perMillion = ((perMillion_str=="null") ? -1 : Integer.parseInt(perMillion_str.split("+")[1]));
    //     String total_str = json.get("total").toString();
    //     Integer total = ((total_str=="null")? -1:Integer.parseInt(total_str));
    //     return new Cases(newCases, active, critical, recovered, perMillion, total);
    // }

    // private Deaths getDeathsFromJSON(JSONObject json) {
    //     String newDeaths_str = json.get("new").toString();
    //     Integer newDeaths= ((newDeaths_str!="null") ? -1 : Integer.parseInt(newDeaths_str.split("+")[1]));
    //     String perMillion_str = json.get("1M_pop").toString();
    //     Integer perMillion = ((perMillion_str!="null") ? -1 : Integer.parseInt(perMillion_str.split("+")[1]));
    //     String total_str = json.get("total").toString();
    //     Integer total = ((total_str=="null")? -1:Integer.parseInt(total_str));
    //     return new Deaths(newDeaths, perMillion, total);
    // }

    // private Tests getTestsFromJSON(JSONObject json) {
    //     String perMillion_str = json.get("1M_pop").toString();
    //     Integer perMillion = ((perMillion_str!="null") ? -1 : Integer.parseInt(perMillion_str.split("+")[1]));
    //     String total_str = json.get("total").toString();
    //     Integer total = ((total_str=="null")? -1:Integer.parseInt(total_str));
    //     return new Tests(perMillion, total);
    // }

    private ResponseEntity<String> getFromCache(URI url) throws IOException, InterruptedException{
        // logger.info("Extract data from cache.");
        ResponseEntity<String> cashedResponse = cache.get(url);
        if(cashedResponse==null){
            ResponseEntity<String> APIResponse = getFromAPI(url);
            logger.info("Extracted data from API and added to cache.");
            cache.put(url, APIResponse);
            return APIResponse;
        }
        logger.info("Extracted data from cache.");
        return cashedResponse;
    }

    public ResponseEntity<Cache> getCacheInfo() {
        return new ResponseEntity<>(this.cache, HttpStatus.OK);
    }
}
