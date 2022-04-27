package ua.tqs.coviddata;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CovidServiceTests {

    @Mock(lenient = true)
    private Cache mockedCache;

    @Mock(lenient = true)
    private HttpClient request;

    @InjectMocks
    private CovidDataService service;


    // private final String HOST = "covid-193.p.rapidapi.com";
    // private final String KEY = "92de5aad8fmsh0c2550784af2ed1p1a93f1jsnbe09eaa6ac3d";

    private ResponseEntity<String> statistics_dummy = new ResponseEntity<String>(
        "[{\"continent\":\"North-America\",\"country\":\"USA\",\"cases\":{\"new\":\"+10496\",\"recovered\":80553008,\"total\":82802226,\"critical\":1457,\"active\":1230043,\"1M_pop\":\"247524\"},\"tests\":{\"total\":1002378319,\"1M_pop\":\"2996447\"},\"time\":\"2022-04-27T20:00:02+00:00\",\"day\":\"2022-04-27\",\"deaths\":{\"new\":\"+83\",\"total\":1019175,\"1M_pop\":\"3047\"},\"population\":334522343}]",
        HttpStatus.OK
    );

    private ResponseEntity<Cache> cache_dummy;

    /*
    "[
        {
            "continent\": \"North-America\",
            "country\": \"Turks-and-Caicos\",
            "population\": 39667,
            "cases\": {
                "new\": \"+22\",
                "active\": 65,
                "critical\": 4,
                "recovered\": 5862,
                "1M_pop\": \"150326\",
                "total\": 5963},
                "deaths\": {
                    "new\": null,
                    "1M_pop\": \"908\",
                    "total\": 36
                },
                "tests\": {
                    "1M_pop\": \"12322258\",
                    "total\": 488787},
                    "day\": \"2022-04-27\",
                    "time\": \"2022-04-27T17:45:23+00:00\"
                },
            }
        }
    ]"
    */

    // private String response = "{\"get\": \"statistics\",\"parameters\": [],\"errors\": [],\"results\": 1,\"response\":[{\"continent\":\"North-America\",\"country\":\"USA\",\"cases\":{\"new\":\"+10496\",\"recovered\":80553008,\"total\":82802226,\"critical\":1457,\"active\":1230043,\"1M_pop\":\"247524\"},\"tests\":{\"total\":1002378319,\"1M_pop\":\"2996447\"},\"time\":\"2022-04-27T20:00:02+00:00\",\"day\":\"2022-04-27\",\"deaths\":{\"new\":\"+83\",\"total\":1019175,\"1M_pop\":\"3047\"},\"population\":334522343}]}";
    
/*{\"get\": \"statistics\",\"parameters\": [],\"errors\": [],\"results\": 240,\"response\": [
    {
      "continent": "North-America",
      "country": "Turks-and-Caicos",
      "population": 39667,
      "cases": {
        "new": "+22",
        "active": 65,
        "critical": 4,
        "recovered": 5862,
        "1M_pop": "150326",
        "total": 5963
      },
      "deaths": {
        "new": null,
        "1M_pop": "908",
        "total": 36
      },
      "tests": {
        "1M_pop": "12322258",
        "total": 488787
      },
      "day": "2022-04-27",
      "time": "2022-04-27T22:15:05+00:00"
    }, */

    private ResponseEntity<String> allCountries_dummy = new ResponseEntity<String>(
        "[\n\t\"Afghanistan\",\n\t\"Albania\",\n\t\"Algeria\"\n]",
        HttpStatus.OK
    );

    private final String host = "covid-193.p.rapidapi.com";
    private final String url = "https://"+host;
    
    // @BeforeEach
    // public void setUp(){
    //     cache = mock(Cache.class);
    // }

    @Test
    public void whenGetStatisticsAndURIinCache_thenReturnCashedValue() {
        URI uri = URI.create(url+"/statistics?country=usa");

        when((mockedCache.get(uri))).thenReturn(statistics_dummy);

        assertThat(service.getCovidStatistics("usa"), is(statistics_dummy));
        verify(mockedCache, times(1)).get(uri);
    }

    @Test
    public void whenGetHistoryAndURIinCache_thenReturnCashedValue() {
        URI uri = URI.create(url+"/history?country=usa");

        when((mockedCache.get(uri))).thenReturn(statistics_dummy);

        assertThat(service.getCovidHistory("usa", null), is(statistics_dummy));
        verify(mockedCache, times(1)).get(uri);
    }

    @Test
    public void whenGetCountriesAndURIinCache_thenReturnCashedValue() {
        URI uri = URI.create(url+"/countries");

        when((mockedCache.get(uri))).thenReturn(allCountries_dummy);

        assertThat(service.getAllCountries(), is(allCountries_dummy));
        verify(mockedCache, times(1)).get(uri);
    }


    @Test
    public void whenGetCache_thenReturnCasheInfo() {

        when((mockedCache.getRequests())).thenReturn(3);
        when((mockedCache.getHits())).thenReturn(2);
        when((mockedCache.getMisses())).thenReturn(1);
        when((mockedCache.getHitMissRatio())).thenReturn(2.0);
        when((mockedCache.getSize())).thenReturn(0);

        cache_dummy= new ResponseEntity<Cache>(
            mockedCache,
            HttpStatus.OK
        );
        System.out.println(cache_dummy);

        assertThat(service.getCacheInfo(), is(cache_dummy));
        
        // verify(mockedCache, times(1)).getRequests();
        // verify(mockedCache, times(1)).getHits();
        // verify(mockedCache, times(1)).getMisses();
        // verify(mockedCache, times(1)).getHitMissRatio();
        // verify(mockedCache, times(1)).getSize();
    }

    // @Test
    // public void whenGetStatisticsAndURInotInCache_thenReturnCashedValue() throws IOException, InterruptedException {
    //     URI uri = URI.create(url+"/statistics?country=usa");

    //     HttpRequest request = HttpRequest.newBuilder()
    //         .uri(uri)
    //         .header("X-RapidAPI-Host", HOST)
    //         .header("X-RapidAPI-Key", KEY)
    //         .method("GET", HttpRequest.BodyPublishers.noBody())
    //         .build();

    //     when((mockedCache.get(uri))).thenReturn(null);
    //     when((HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())).body()).thenReturn(response);

    //     assertThat(service.getCovidStatistics("usa"), is(statistics_dummy));
    //     verify(mockedCache, times(1)).get(uri);
    //     verify(HttpClient.newHttpClient(), times(1));
    // }
    // cant mock httpClient

    // @Test
    // public void whenGetStatisticsButException_thenCatchException() {
    //     URI uri = URI.create("usa");

    //     when((mockedCache.get(uri))).thenThrow(IOException.class);

    //     assertNotNull(service.getCovidStatistics("usa"));
    //     verify(mockedCache, times(1)).get(uri);
    // }

    // @Test
    //  void whenSearchInvalidID_thenCarShouldNotBeFound() {
    //     Optional<Car> fromDb = service.getCarDetails(-1L);
    //     assertThat(fromDb).isEmpty();

    // }

    // @Test
    //  void given2Cars_whenGetAll_thenReturn2Records() {
    //     car1 = new Car("Toyota", "Corolla");
	// 	car2 = new Car("Renault", "Clio");

    //     List<Car> allCars = service.getAllCars();
    //     assertThat(allCars).hasSize(2).extracting(Car::getModel).contains(car1.getModel(), car2.getModel());
    // }
    
}
