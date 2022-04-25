package ua.tqs.coviddata;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CovidDataController {
    @Autowired
    private CovidDataService service;

    @GetMapping("/history")
    public void getCovidInfoByCountryAndDate(@RequestParam(required = true) String country, @RequestParam(required = false) Date day) {
        service.getCovidInfoCountryAndDate(country, day);
    }

    @GetMapping("/statistics")
    public ResponseEntity<String> getCovidInfo(@RequestParam(required = false) String country){
        return service.getCovidInfo(country);   
    }

    @GetMapping("/countries")
    public ResponseEntity<String> getAllCountries(){
        return service.getAllCountries();   
    }

    @GetMapping("/cache")
    public ResponseEntity<String> getCacheInfo(){
        return service.getCacheInfo();
    }
    
}
