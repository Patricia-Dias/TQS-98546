package ua.tqs.coviddata;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
public class CovidServiceTest {

    @Mock(lenient = true)
    private Cache mockedCache;

    @Mock(lenient = true)
    private Client client;

    @InjectMocks
    private CovidDataService service;

    private ResponseEntity<String> coviddata_dummy = new ResponseEntity<String>(
        "[{\"continent\":\"North-America\",\"country\":\"USA\",\"cases\":{\"new\":\"+10496\",\"recovered\":80553008,\"total\":82802226,\"critical\":1457,\"active\":1230043,\"1M_pop\":\"247524\"},\"tests\":{\"total\":1002378319,\"1M_pop\":\"2996447\"},\"time\":\"2022-04-27T20:00:02+00:00\",\"day\":\"2022-04-27\",\"deaths\":{\"new\":\"+83\",\"total\":1019175,\"1M_pop\":\"3047\"},\"population\":334522343}]",
        HttpStatus.OK
    );

    private ResponseEntity<Map<String, Object>> cache_dummy;

    private ResponseEntity<String> badRequest = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

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


    private ResponseEntity<String> allCountries_dummy = new ResponseEntity<String>(
        "[\n\t\"Afghanistan\",\n\t\"Albania\",\n\t\"Algeria\"\n]",
        HttpStatus.OK
    );

    private final String host = "covid-193.p.rapidapi.com";
    private final String url = "https://"+host;

    @Test
    public void whenGetStatisticsAndURIinCache_thenReturnCashedValue() {
        URI uri = URI.create(url+"/statistics?country=usa");

        when((mockedCache.get(uri))).thenReturn(coviddata_dummy);

        assertThat(service.getCovidStatistics("usa"), is(coviddata_dummy));

        verify(mockedCache, times(1)).get(uri);
        verify(client, times(0)).getFromAPI(uri);
    }

    @Test
    public void whenGetStatisticsAndURInotInCache_thenReturnApiValue() {
        URI uri = URI.create(url+"/statistics?country=usa");

        when((mockedCache.get(uri))).thenReturn(null);
        when((client.getFromAPI(uri))).thenReturn(coviddata_dummy);

        assertThat(service.getCovidStatistics("usa"), is(coviddata_dummy));

        verify(mockedCache, times(1)).get(uri);
        verify(client, times(1)).getFromAPI(uri);
    }

    @Test
    public void whenGetStatisticsWrong_thenReturnBadRequest() {
        URI uri = URI.create(url+"/statistics?country=jkhgfds");

        when((mockedCache.get(uri))).thenReturn(badRequest);

        assertThat(service.getCovidStatistics("jkhgfds"), is(badRequest));

        verify(mockedCache, times(1)).get(uri);

    }

    @Test
    public void whenGetHistoryAndURIinCache_thenReturnCashedValue() {
        URI uri = URI.create(url+"/history?country=usa");

        when((mockedCache.get(uri))).thenReturn(coviddata_dummy);

        assertThat(service.getCovidHistory("usa", null), is(coviddata_dummy));

        verify(mockedCache, times(1)).get(uri);
        verify(client, times(0)).getFromAPI(uri);
    }

    @Test
    public void whenGetHistoryAndURInotInCache_thenReturnApiValue() {
        URI uri = URI.create(url+"/history?country=usa");

        when((mockedCache.get(uri))).thenReturn(null);
        when((client.getFromAPI(uri))).thenReturn(coviddata_dummy);

        assertThat(service.getCovidHistory("usa", null), is(coviddata_dummy));

        verify(mockedCache, times(1)).get(uri);
        verify(client, times(1)).getFromAPI(uri);
    }

    @Test
    public void whenGetHistoryWrong_thenReturnBadRequest() {
        URI uri = URI.create(url+"/history?country=jkhgfds");

        when((mockedCache.get(uri))).thenReturn(badRequest);

        assertThat(service.getCovidHistory("jkhgfds", null), is(badRequest));

        verify(mockedCache, times(1)).get(uri);
    }

    @Test
    public void whenGetCountriesAndURIinCache_thenReturnCashedValue() {
        URI uri = URI.create(url+"/countries");

        when((mockedCache.get(uri))).thenReturn(allCountries_dummy);

        assertThat(service.getAllCountries(), is(allCountries_dummy));

        verify(mockedCache, times(1)).get(uri);
        verify(client, times(0)).getFromAPI(uri);
    }

    @Test
    public void whenGetCountriesAndURInotInCache_thenReturnApiValue() {
        URI uri = URI.create(url+"/countries");

        when((mockedCache.get(uri))).thenReturn(null);
        when((client.getFromAPI(uri))).thenReturn(allCountries_dummy);

        assertThat(service.getAllCountries(), is(allCountries_dummy));

        verify(mockedCache, times(1)).get(uri);
        verify(client, times(1)).getFromAPI(uri);
    }

    @Test
    public void whenGetCountriesWrong_thenReturnBadRequest() {
        URI uri = URI.create(url+"/countries");

        when((mockedCache.get(uri))).thenReturn(badRequest);

        assertThat(service.getAllCountries(), is(badRequest));

        verify(mockedCache, times(1)).get(uri);
    }

    @Test
    public void whenGetCache_thenReturnCasheInfo() {
        when((mockedCache.getTTL())).thenReturn(50L);
        when((mockedCache.getRequests())).thenReturn(3);
        when((mockedCache.getHits())).thenReturn(2);
        when((mockedCache.getMisses())).thenReturn(1);
        when((mockedCache.getHitMissRatio())).thenReturn(2.0);
        when((mockedCache.getSize())).thenReturn(0);

        Map<String, Object> cacheInfoMap = new HashMap<>();
        cacheInfoMap.put("ttl", 50L);
        cacheInfoMap.put("requests", 3);
        cacheInfoMap.put("hits", 2);    
        cacheInfoMap.put("misses", 1);
        cacheInfoMap.put("hitMissRatio", 2.0);
        cacheInfoMap.put("size", 0);

        cache_dummy= new ResponseEntity<Map<String,Object>>(
            cacheInfoMap,
            HttpStatus.OK
        );

        assertThat(service.getCacheInfo(), is(cache_dummy));
        verify(mockedCache, times(1)).getTTL();
        verify(mockedCache, times(1)).getRequests();
        verify(mockedCache, times(1)).getHits();
        verify(mockedCache, times(1)).getMisses();
        verify(mockedCache, times(1)).getHitMissRatio();
        verify(mockedCache, times(1)).getSize();
    }

}
