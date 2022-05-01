package ua.tqs.coviddata.APITests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CovidaDataAPITests {
    @LocalServerPort
    int randomServerPort;
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenGetStatistics_thenStatus200() {
        ResponseEntity<String> response = restTemplate
                .exchange("/api/statistics", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSizeGreaterThan(1).contains("South-America","Europe", "Africa");
    }

    @Test
    void whenGetStatisticsByCountry_thenStatus200() {
        ResponseEntity<String> response = restTemplate
                .exchange("/api/statistics?country=portugal", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSizeGreaterThan(1).doesNotContain("USA");
        assertThat(response.getBody()).hasSizeGreaterThan(1).contains("Portugal");
    }

    @Test
    void whenGetHistoryByCountry_thenStatus200() {
        ResponseEntity<String> response = restTemplate
                .exchange("/api/history?country=portugal", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSizeGreaterThan(1).doesNotContain("Spain");
        assertThat(response.getBody()).hasSizeGreaterThan(1).contains("Portugal");
    }

    @Test
    void whenGetHistoryByCountryAndDate_thenStatus200() {
        ResponseEntity<String> response = restTemplate
                .exchange("/api/history?country=portugal&day=2020-04-09", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSizeGreaterThan(1).contains("Portugal");
        assertThat(response.getBody()).hasSizeGreaterThan(1).doesNotContain("Spain");
        assertThat(response.getBody()).hasSizeGreaterThan(1).doesNotContain("2022-04-09");
    }

    @Test
    void whenGetCountries_thenStatus200() {
        ResponseEntity<String> response = restTemplate
                .exchange("/api/countries", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSizeGreaterThan(1);
    }
}
