package ua.tqs.coviddata;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(CovidDataController.class)
public class CovidControllerTests {

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

    private ResponseEntity<Cache> cacheInfo_dummy = new ResponseEntity<>(
        new Cache(5*60),
        HttpStatus.OK
    );

    private String country = "usa";
    private String country_valueFromAPI = "USA";
    private String day = "2020-05-20";

    @Test
    public void whenGetAllCountries_thenReturnJsonArray() throws Exception {
        when(service.getAllCountries()).thenReturn(allCountries_dummy);

        mock.perform(MockMvcRequestBuilders.get("/api/countries")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0]", is("Afghanistan")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1]", is("Albania")));
		
		verify(service, times(1)).getAllCountries();
    }

    @Test
    public void whenGetStatistics_thenReturnJson() throws Exception {
        when(service.getCovidStatistics(null)).thenReturn(statistics_dummy);

        mock.perform(MockMvcRequestBuilders.get("/api/statistics")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].country", is("Turks-and-Caicos")))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].country", is("USA")));
		
		verify(service, times(1)).getCovidStatistics(null);
    }

    @Test
    public void whenGetStatisticsWithParamCountry_thenReturnJson() throws Exception {
        when(service.getCovidStatistics(country)).thenReturn(usa_statistics_dummy);

        mock.perform(MockMvcRequestBuilders.get("/api/statistics?country="+country)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].country", is(country_valueFromAPI)));
		
		verify(service, times(1)).getCovidStatistics(country);
    }

    @Test
    public void whenGetHistory_thenReturnJson() throws Exception {
        when(service.getCovidHistory(country, null)).thenReturn(usa_history_dummy);

        mock.perform(MockMvcRequestBuilders.get("/api/history?country="+country)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].country", is(country_valueFromAPI)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].country", is(country_valueFromAPI)));
		
		verify(service, times(1)).getCovidHistory(country, null);
    }

    @Test
    public void whenGetHistoryWithParamDay_thenReturnJson() throws Exception {
        when(service.getCovidHistory(country, day)).thenReturn(usa_2020_05_20_history_dummy);

        mock.perform(MockMvcRequestBuilders.get("/api/history?country="+country+"&day="+day)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].country", is(country_valueFromAPI)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].day", is(day)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].country", is(country_valueFromAPI)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[1].day", is(day)));
		
		verify(service, times(1)).getCovidHistory(country, day);
    }

    @Test
    public void whenGetCacheInfo_thenReturnJsonArray() throws Exception {
        when(service.getCacheInfo()).thenReturn(cacheInfo_dummy);

        mock.perform(MockMvcRequestBuilders.get("/api/cache")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.requests", is(0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.hits", is(0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.misses", is(0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.ttl", is(5*60*1000)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.size", is(0)))
            .andExpect(MockMvcResultMatchers.jsonPath("$.hitMissRatio", is(0.0)));
		
		verify(service, times(1)).getCacheInfo();
    }
    
}
