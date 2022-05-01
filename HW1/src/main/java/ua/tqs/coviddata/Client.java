package ua.tqs.coviddata;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpClient;

public class Client {
    private static final String HOST = "covid-193.p.rapidapi.com";
    private static final String KEY = "92de5aad8fmsh0c2550784af2ed1p1a93f1jsnbe09eaa6ac3d";
    private Logger logger = Logger.getLogger(Client.class.getName());

    public ResponseEntity<String> getFromAPI(URI uri) {
        logger.info("GET request was called.");
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("X-RapidAPI-Host", HOST)
            .header("X-RapidAPI-Key", KEY)
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Get response was successful.");
            JSONObject json = new JSONObject(response.body());
            JSONArray responseArray = json.getJSONArray("response");
            return new ResponseEntity<>(responseArray.toString(), HttpStatus.OK);
        } catch (IOException | InterruptedException e) {
            logger.severe("Exception thrown. "+e);
            Thread.currentThread().interrupt();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
}
