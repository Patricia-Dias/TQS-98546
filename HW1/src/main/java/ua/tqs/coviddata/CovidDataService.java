package ua.tqs.coviddata;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CovidDataService {
    private final String HOST = "covid-193.p.rapidapi.com";
    private final String URL = "https://"+HOST;
    private final String EXCEPTION = "Exception. ";
    private Logger logger = Logger.getLogger(CovidDataService.class.getName());
    private final long ttl = 60*2L;   // 2 minutes
    private Cache cache = new Cache(ttl);   
    private Client client = new Client();   

    public ResponseEntity<String> getAllCountries() {
        logger.info("service.getAllCountries() was called.");
        try {
            URI uri = new URI(URL+"/countries");
            return getFromCache(uri);
        }
        catch (URISyntaxException e) {
            logger.log(Level.SEVERE, EXCEPTION+"String couldn't be parsed as a URI reference.", e);
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
            URI uri = new URI(url_str);
            return getFromCache(uri);
            
        } catch (URISyntaxException e) {
            logger.log(Level.SEVERE, EXCEPTION+"String couldn't be parsed as a URI reference.", e);
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
            URI uri = new URI(url_str);
            return getFromCache(uri);
            
        } catch (URISyntaxException e) {
            logger.log(Level.SEVERE, EXCEPTION+"String couldn't be parsed as a URI reference.", e);
            Thread.currentThread().interrupt();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> getFromCache(URI uri) {
        // logger.info("Extract data from cache.");
        ResponseEntity<String> cashedResponse = cache.get(uri);
        if(cashedResponse==null){
            ResponseEntity<String> APIResponse = client.getFromAPI(uri);
            logger.info("Extracted data from API and added to cache.");
            cache.put(uri, APIResponse);
            return APIResponse;
        }
        logger.info("Extracted data from cache.");
        return cashedResponse;
    }

    public ResponseEntity<Map<String, Object>> getCacheInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("ttl", cache.getTTL());
        result.put("requests", cache.getRequests());
        result.put("hits", cache.getHits());    
        result.put("misses", cache.getMisses());
        result.put("hitMissRatio", cache.getHitMissRatio());
        result.put("size", cache.getSize());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
