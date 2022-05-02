package ua.tqs.coviddata.ControllerTests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import ua.tqs.coviddata.Cache;
import ua.tqs.coviddata.Controller.CovidDataController;
import ua.tqs.coviddata.Service.CovidDataService;

@WebMvcTest(CovidDataController.class)
class CovidControllerRestAssuredTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private CovidDataService service;

    private ResponseEntity<String> allCountries_dummy = new ResponseEntity<String>(
        "[\n\t\"Afghanistan\",\n\t\"Albania\",\n\t\"Algeria\"\n]",
        HttpStatus.OK
    );

    private ResponseEntity<String> statistics_dummy = new ResponseEntity<String>(
        "[\n\t{\n\t\t\"continent\": \"North-America\",\n\t\t\"country\": \"Turks-and-Caicos\",\n\t\t\"population\": 39667,\n\t\t\"cases\": {\n\t\t\t\"new\": \"+22\",\n\t\t\t\"active\": 65,\n\t\t\t\"critical\": 4,\n\t\t\t\"recovered\": 5862,\n\t\t\t\"1M_pop\": \"150326\",\n\t\t\t\"total\": 5963\n\t\t},\n\t\t\"deaths\": {\n\t\t\t\"new\": null,\n\t\t\t\"1M_pop\": \"908\",\n\t\t\t\"total\": 36\n\t\t},\n\t\t\"tests\": {\n\t\t\t\"1M_pop\": \"12322258\",\n\t\t\t\"total\": 488787\n\t\t},\n\t\t\"day\": \"2022-04-27\",\n\t\t\"time\": \"2022-04-27T17:45:23+00:00\"\n\t},\n\t{\n\t\t\"continent\": \"North-America\",\n\t\t\"country\": \"USA\",\n\t\t\"population\": 39667,\n\t\t\"cases\": {\n\t\t\t\"new\": \"+22\",\n\t\t\t\"active\": 65,\n\t\t\t\"critical\": 4,\n\t\t\t\"recovered\": 5862,\n\t\t\t\"1M_pop\": \"150326\",\n\t\t\t\"total\": 5963\n\t\t},\n\t\t\"deaths\": {\n\t\t\t\"new\": null,\n\t\t\t\"1M_pop\": \"908\",\n\t\t\t\"total\": 36\n\t\t},\n\t\t\"tests\": {\n\t\t\t\"1M_pop\": \"12322258\",\n\t\t\t\"total\": 488787\n\t\t},\n\t\t\"day\": \"2022-04-27\",\n\t\t\"time\": \"2022-04-27T17:45:23+00:00\"\n\t}\n]",
        HttpStatus.OK
    );

    private ResponseEntity<String> usa_statistics_dummy = new ResponseEntity<String>(
        "[\n\t{\n\t\t\"continent\": \"North-America\",\n\t\t\"country\": \"USA\",\n\t\t\"population\": 39667,\n\t\t\"cases\": {\n\t\t\t\"new\": \"+22\",\n\t\t\t\"active\": 65,\n\t\t\t\"critical\": 4,\n\t\t\t\"recovered\": 5862,\n\t\t\t\"1M_pop\": \"150326\",\n\t\t\t\"total\": 5963\n\t\t},\n\t\t\"deaths\": {\n\t\t\t\"new\": null,\n\t\t\t\"1M_pop\": \"908\",\n\t\t\t\"total\": 36\n\t\t},\n\t\t\"tests\": {\n\t\t\t\"1M_pop\": \"12322258\",\n\t\t\t\"total\": 488787\n\t\t},\n\t\t\"day\": \"2022-04-27\",\n\t\t\"time\": \"2022-04-27T17:45:23+00:00\"\n\t}\n]",
        HttpStatus.OK
    );

    private ResponseEntity<String> usa_history_dummy = new ResponseEntity<>(
        "[\n\t{\n\t\t\"continent\": \"North-America\",\n\t\t\"country\": \"USA\",\n\t\t\"population\": 39667,\n\t\t\"cases\": {\n\t\t\t\"new\": \"+22\",\n\t\t\t\"active\": 65,\n\t\t\t\"critical\": 4,\n\t\t\t\"recovered\": 5862,\n\t\t\t\"1M_pop\": \"150326\",\n\t\t\t\"total\": 5963\n\t\t},\n\t\t\"deaths\": {\n\t\t\t\"new\": null,\n\t\t\t\"1M_pop\": \"908\",\n\t\t\t\"total\": 36\n\t\t},\n\t\t\"tests\": {\n\t\t\t\"1M_pop\": \"12322258\",\n\t\t\t\"total\": 488787\n\t\t},\n\t\t\"day\": \"2022-04-27\",\n\t\t\"time\": \"2022-04-27T17:45:23+00:00\"\n\t},\n\t{\n\t\t\"continent\": \"North-America\",\n\t\t\"country\": \"USA\",\n\t\t\"population\": 39667,\n\t\t\"cases\": {\n\t\t\t\"new\": \"+22\",\n\t\t\t\"active\": 65,\n\t\t\t\"critical\": 4,\n\t\t\t\"recovered\": 5862,\n\t\t\t\"1M_pop\": \"150326\",\n\t\t\t\"total\": 5963\n\t\t},\n\t\t\"deaths\": {\n\t\t\t\"new\": null,\n\t\t\t\"1M_pop\": \"908\",\n\t\t\t\"total\": 36\n\t\t},\n\t\t\"tests\": {\n\t\t\t\"1M_pop\": \"12322258\",\n\t\t\t\"total\": 488787\n\t\t},\n\t\t\"day\": \"2020-05-05\",\n\t\t\"time\": \"2020-05-05T17:45:23+00:00\"\n\t}\n]",
        HttpStatus.OK
    );

    private ResponseEntity<String> usa_2020_05_20_history_dummy = new ResponseEntity<>(
        "[\n\t{\n\t\t\"continent\": \"North-America\",\n\t\t\"country\": \"USA\",\n\t\t\"population\": 39667,\n\t\t\"cases\": {\n\t\t\t\"new\": \"+22\",\n\t\t\t\"active\": 65,\n\t\t\t\"critical\": 4,\n\t\t\t\"recovered\": 5862,\n\t\t\t\"1M_pop\": \"150326\",\n\t\t\t\"total\": 5963\n\t\t},\n\t\t\"deaths\": {\n\t\t\t\"new\": null,\n\t\t\t\"1M_pop\": \"908\",\n\t\t\t\"total\": 36\n\t\t},\n\t\t\"tests\": {\n\t\t\t\"1M_pop\": \"12322258\",\n\t\t\t\"total\": 488787\n\t\t},\n\t\t\"day\": \"2020-05-20\",\n\t\t\"time\": \"2020-05-20T17:45:23+00:00\"\n\t},\n\t{\n\t\t\"continent\": \"North-America\",\n\t\t\"country\": \"USA\",\n\t\t\"population\": 39667,\n\t\t\"cases\": {\n\t\t\t\"new\": \"+22\",\n\t\t\t\"active\": 65,\n\t\t\t\"critical\": 4,\n\t\t\t\"recovered\": 5862,\n\t\t\t\"1M_pop\": \"150326\",\n\t\t\t\"total\": 5963\n\t\t},\n\t\t\"deaths\": {\n\t\t\t\"new\": null,\n\t\t\t\"1M_pop\": \"908\",\n\t\t\t\"total\": 36\n\t\t},\n\t\t\"tests\": {\n\t\t\t\"1M_pop\": \"12322258\",\n\t\t\t\"total\": 488787\n\t\t},\n\t\t\"day\": \"2020-05-20\",\n\t\t\"time\": \"2020-05-05T17:45:23+00:00\"\n\t}\n]",
        HttpStatus.OK
    );

    private Map<String, Object> cacheInfoMap = new HashMap<>();

    

    private ResponseEntity<Map<String, Object>> cacheInfo_dummy;

    private String country = "usa";
    private String country_valueFromAPI = "USA";
    private String day = "2020-05-20";

    @BeforeEach
    void setUp(){
        Cache cache = new Cache(5*60);
        cacheInfoMap.put("ttl", cache.getTTL());
        cacheInfoMap.put("requests", cache.getRequests());
        cacheInfoMap.put("hits", cache.getHits());    
        cacheInfoMap.put("misses", cache.getMisses());
        cacheInfoMap.put("hitMissRatio", cache.getHitMissRatio());
        cacheInfoMap.put("size", cache.getSize());
        cacheInfo_dummy = new ResponseEntity<>(
            cacheInfoMap,
            HttpStatus.OK
        );
    }
    
    @Test
    void whenGetAllCountries_thenReturnJsonArray() throws Exception {
        when(service.getAllCountries()).thenReturn(allCountries_dummy);

        RestAssuredMockMvc.mockMvc(mock);
        RestAssured.given()
        .header("Content-type", "application/json")
		.when()
			.get("/api/countries")
		.then()
            .statusCode(200)
            .and().body("$", hasSize(3))
            .and().body("$", hasItems("Afghanistan","Albania"));

		verify(service, times(1)).getAllCountries();
    }

    

    @Test
    void whenGetStatistics_thenReturnJson() throws Exception {
        when(service.getCovidStatistics(null)).thenReturn(statistics_dummy);

        RestAssuredMockMvc.mockMvc(mock);
        RestAssured.given()
        .header("Content-type", "application/json")
		.when()
			.get("/api/statistics")
		.then()
            .statusCode(200)
            .and().body("$", hasSize(2))
            .and().body("$country", hasItems("Turks-and-Caicos","USA"));
		
		verify(service, times(1)).getCovidStatistics(null);
    }

    @Test
    void whenGetStatisticsWithParamCountry_thenReturnJson() throws Exception {
        when(service.getCovidStatistics(country)).thenReturn(usa_statistics_dummy);

        RestAssuredMockMvc.mockMvc(mock);
        RestAssured.given()
        .header("Content-type", "application/json")
		.when()
			.get("/api/statistics?country="+country)
		.then()
            .statusCode(200)
            .and().body("$", hasSize(1))
            .and().body("$country", hasItems(country_valueFromAPI));

		verify(service, times(1)).getCovidStatistics(country);
    }

    @Test
    void whenGetHistory_thenReturnJson() throws Exception {
        when(service.getCovidHistory(country, null)).thenReturn(usa_history_dummy);

        RestAssuredMockMvc.mockMvc(mock);
        RestAssured.given()
        .header("Content-type", "application/json")
		.when()
			.get("/api/history?country="+country)
		.then()
            .statusCode(200)
            .and().body("$", hasSize(1))
            .and().body("$country", hasItems(country_valueFromAPI));
        
		verify(service, times(1)).getCovidHistory(country, null);
    }

    @Test
    void whenGetHistoryWithParamDay_thenReturnJson() throws Exception {
        when(service.getCovidHistory(country, day)).thenReturn(usa_2020_05_20_history_dummy);

        RestAssuredMockMvc.mockMvc(mock);
        RestAssured.given()
        .header("Content-type", "application/json")
		.when()
			.get("/api/history?country="+country+"&day="+day)
		.then()
            .statusCode(200)
            .and().body("$", hasSize(2))
            .and().body("$country", hasItems(country_valueFromAPI, country_valueFromAPI))
            .and().body("$country", hasItems(day, day));

		verify(service, times(1)).getCovidHistory(country, day);
    }

    @Test
    void whenGetCacheInfo_thenReturnJsonArray() throws Exception {
        when(service.getCacheInfo()).thenReturn(cacheInfo_dummy);

        RestAssuredMockMvc.mockMvc(mock);
        RestAssured.given()
        .header("Content-type", "application/json")
		.when()
			.get("/api/cache")
		.then()
            .statusCode(200)
            .and().body("$", hasSize(1))
            .and().body("$requests", hasItems(0))
            .and().body("$hits", hasItems(0))
            .and().body("$misses", hasItems(0))
            .and().body("$ttl", hasItems(5*60*1000))
            .and().body("$size", hasItems(0))
            .and().body("$hitMissRatio", hasItems(0.0));

		verify(service, times(1)).getCacheInfo();
    }
}
