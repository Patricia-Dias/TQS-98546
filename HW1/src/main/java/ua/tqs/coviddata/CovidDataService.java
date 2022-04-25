package ua.tqs.coviddata;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CovidDataService {
    private final String HOST = "covid-193.p.rapidapi.com";
    private final String URL = "https://"+HOST;
    private final String KEY = "92de5aad8fmsh0c2550784af2ed1p1a93f1jsnbe09eaa6ac3d";
    private final String EXCEPTION = "Exception. ";
    private Logger logger = Logger.getLogger(CovidDataService.class.getName());
    private final long ttl = 60*2;   // 2 minutes
    private Cache cache = new Cache(ttl);      

    public ResponseEntity<String> getAllCountries() {
        logger.info("service.getAllCountries() was called.");
        try {
            URI url = new URI(URL+"/countries");
            return getFromCache(url);
        }
        catch (URISyntaxException e) {
            logger.log(Level.SEVERE, EXCEPTION+"String couldn't be parsed as a URI reference.", e);
        }
        catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, EXCEPTION, e);
        }
        Thread.currentThread().interrupt();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

    public ResponseEntity<String> getCovidInfo(String country)  {
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
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, EXCEPTION, e);
        }
        Thread.currentThread().interrupt();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> getCovidInfoCountryAndDate(String country, Date day) {
        logger.info("service.getCovidInfoCountryAndDate() was called.");
        String url_str=URL+"/history?country="+country;
        if (day!=null)
            url_str +=  "&day="+day;
        try {
            URI url = new URI(url_str);
            return getFromCache(url);
            
        } catch (URISyntaxException e) {
            logger.log(Level.SEVERE, EXCEPTION+"String couldn't be parsed as a URI reference.", e);
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, EXCEPTION, e);
        }
        Thread.currentThread().interrupt();
        
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> getFromAPI(URI url) throws IOException, InterruptedException{
        logger.info("GET request to "+url+" was called.");
        HttpRequest request = HttpRequest.newBuilder()
            .uri(url)
            .header("X-RapidAPI-Host", HOST)
            .header("X-RapidAPI-Key", KEY)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        logger.info("GET request was successful.");
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return new ResponseEntity<>(response.body(), HttpStatus.OK);
    }

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

    public ResponseEntity<String> getCacheInfo() {
        String res = String.format("{\n" +
        "\t\"ttl\": %d,\n" +
        "\t\"statistics\": {\n" +
        "\t\t\"requests\": %d,\n"+
        "\t\t\"hits\": %d,\n" +
        "\t\t\"misses\": %d,\n" +
        "\t\"size\": %d\n" +
        "\t}\n" +
        "}", cache.getTTL(), cache.getRequests(),cache.getHits(), cache.getMisses(), cache.size());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
