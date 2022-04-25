package ua.tqs.coviddata;

import java.net.URI;
import java.util.HashMap;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;

public class Cache {
	private Logger logger = Logger.getLogger(CovidDataService.class.getName());
    
    private final long timeToLive;
	private final long refreshTime;
	private HashMap<URI, ResponseEntity<String>> cache;
	private HashMap<URI, Long> cacheItemsLife;
	private int requests = 0;
	private int hits = 0;
	private int misses = 0;

 
	public Cache(long ttl) {
		this.timeToLive = ttl * 1000;
		this.refreshTime = timeToLive+1;
		this.cache = new HashMap<>();
		this.cacheItemsLife = new HashMap<>();
		if (timeToLive>0){
			Thread t = new Thread( new Runnable() {
				public void run(){
					while(true){
						try {
							Thread.sleep(refreshTime);
							cleanCashe();
						} catch (InterruptedException e) {
							logger.severe("Exception. "+e);;
						}
					}
				}

			});
			t.setDaemon(true);
			t.start();
		}
	}
 
	public void put(URI key, ResponseEntity<String> value) {
		cache.put(key, value);
		cacheItemsLife.put(key, System.currentTimeMillis());
	}
	//new comment just cuz
	public ResponseEntity<String> get(URI key) {
		ResponseEntity<String> value = cache.get(key);
		requests++;

		if (value == null)
		{
			misses++;
			return null;
		}	
		else {
			hits++;
			return value;
		}
	}
 
	public void remove(URI key) {
		logger.info("removed key:\t"+key);
		cache.remove(key);
		cacheItemsLife.remove(key);
		logger.info("Cashe size after cleanup\t"+size());
	}
 
	public int size() {
		if (cache.size()!=cacheItemsLife.size()){
			logger.severe("ALERT: The cache map and cacheItemsLife map should have the same size.");
		}
		return cache.size();
	}

	//used with testing
	public void emptyCache(){
		this.cache.clear();
		this.cacheItemsLife.clear();
		this.requests=0;
		this.hits=0;
		this.misses=0;
	}

	public void cleanCashe(){
		// int elements_cleared = cache.size();
		// cache.clear();
		logger.info("cleanCashe was called");
		long now = System.currentTimeMillis();
		
		for ( URI entry : cache.keySet()){
			if (timeToLive<=now-cacheItemsLife.get(entry)){
				remove(entry);
			}
			Thread.yield();
		}
	}

	public HashMap<URI, Long> getCacheItemsLife(){
		return this.cacheItemsLife;
	}

	public boolean isEmpty(){
		return cache.isEmpty() && cacheItemsLife.isEmpty();
	}

	public int getRequests(){
		return requests;
	}

	public int getHits(){
		return hits;
	}

	public int getMisses(){
		return misses;
	}

	public long getTTL(){
		return timeToLive;
	}

}
