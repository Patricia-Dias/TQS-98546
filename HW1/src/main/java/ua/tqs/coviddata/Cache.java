package ua.tqs.coviddata;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;

import ua.tqs.coviddata.model.Country;

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

	public Cache(long ttl, int requests, int hits, int misses) {
		this.timeToLive = ttl * 1000;
		this.refreshTime = timeToLive+1;
		this.cache = new HashMap<>();
		this.cacheItemsLife = new HashMap<>();
		this.requests= requests;
		this.hits = hits;
		this.misses = misses;
	}
 
	public void put(URI key, ResponseEntity<String> aPIResponse) {
		cache.put(key, aPIResponse);
		cacheItemsLife.put(key, System.currentTimeMillis());
	}
	
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
		logger.info("Cashe size after cleanup\t"+getSize());
	}
 
	public void emptyCache(){
		this.cache.clear();
		this.cacheItemsLife.clear();
		this.requests=0;
		this.hits=0;
		this.misses=0;
	}

	public void cleanCashe(){
		logger.info("cleanCashe was called");
		long now = System.currentTimeMillis();
		
		for ( URI entry : cache.keySet()){
			if (timeToLive<=now-cacheItemsLife.get(entry)){
				remove(entry);
			}
		}
	}

	public HashMap<URI, Long> cacheItemsLife(){
		return this.cacheItemsLife;
	}

	public long getTTL(){
		return timeToLive;
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

	public double getHitMissRatio(){
		if (hits-misses==0)
			return 0;
		return hits/(hits-misses);
	}

	public int getSize(){
		if (cache.size()!=cacheItemsLife.size()){
			logger.severe("ALERT: The cache map and cacheItemsLife map should have the same size.");
		}
		return this.cache.size();
	}

}
