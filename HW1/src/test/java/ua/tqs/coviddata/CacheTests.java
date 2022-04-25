package ua.tqs.coviddata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
class CacheTests {
	private final long ttl = 60*5;   // 5 minutes
	private Cache cache;
	private URI test_uri1 = URI.create("https://www.example.com");
	private URI test_uri2 = URI.create("https://www.cars.com");
	private ResponseEntity<String> test_respEntity1 = new ResponseEntity<>("reponse1", HttpStatus.OK);
	private ResponseEntity<String> test_respEntity2 = new ResponseEntity<>("reponse2", HttpStatus.OK);

	@BeforeEach
	void setUp(){
		cache = new Cache(ttl);
	}

	@AfterEach
	void cleanUp(){
		cache.emptyCache();
	}

	@DisplayName("Initializing cache test.")
	@Test
	void InitializingCacheTest() {
		assertEquals(ttl*1000, cache.getTTL());
		assertTrue(cache.isEmpty());
		assertEquals(0, cache.getRequests());
		assertEquals(0, cache.getHits());
		assertEquals(0, cache.getMisses());
	}

	@DisplayName("Adding and removing elements from cache")
	@Test
	void AddAndRemoveElementFromCacheTest(){
		cache.put(test_uri1, test_respEntity1);
		cache.put(test_uri2, test_respEntity2);
		assertThat(cache.size(), is(2));
		assertThat(cache.getCacheItemsLife().size(), is(2));

		cache.remove(test_uri1);
		assertThat(cache.size(), is(1));
		assertThat(cache.getCacheItemsLife().size(), is(1));
	}

	@DisplayName("Accessing available element from cache")
	@Test
	void GetAvailableElementFromCacheTest(){
		cache.put(test_uri1, test_respEntity1);
		assertThat(cache.get(test_uri1), is(test_respEntity1));
		assertThat(cache.getHits(), is(1));
		assertThat(cache.getMisses(), is(0));
		assertThat(cache.getRequests(), is(1));
	}

	@DisplayName("Accessing unavailable element from cache")
	@Test
	void GetUnavailableElementFromCacheTest(){
		assertNull(cache.get(test_uri1));
		assertThat(cache.getHits(), is(0));
		assertThat(cache.getMisses(), is(1));
		assertThat(cache.getRequests(), is(1));
	}

	@DisplayName("Regularly clean cashe")
	@Test
	synchronized void cleanCashTest() throws InterruptedException{
		Cache newCache = new Cache(1);
		
		newCache.put(test_uri1, test_respEntity1);
		newCache.get(test_uri1);

		assertThat(newCache.size(), is(1));
		assertThat(newCache.getRequests(), is(1));

		wait(2000);

		assertThat(newCache.size(), is(0));
		assertThat(newCache.getRequests(), is(1));

	}

}
