package ua.tqs.coviddata;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;


public class Cache {
	private Logger logger = Logger.getLogger(Cache.class.getName());
    
    private final long timeToLive;
	private HashMap<URI, ResponseEntity<String>> cacheMap;
	private HashMap<URI, Long> cacheItemsLife;
	private int requests = 0;
	private int hits = 0;
	private int misses = 0;

 
	public Cache(long ttl) {
		this.timeToLive = ttl * 1000;
		this.cacheMap = new HashMap<>();
		this.cacheItemsLife = new HashMap<>();
		if (timeToLive>0){
			Runnable runnable = () -> {
				while(true){
					try {
						Thread.sleep(timeToLive);
					} catch (InterruptedException e) {
						logger.severe("Exception. "+e);
						Thread.currentThread().interrupt();
					}
					if (cacheMap.size()>0)
						cleanCashe();
				}
			};
			Thread t = new Thread(runnable);
			t.setDaemon(true);
			t.start();
		}
	}
 
	public void put(URI key, ResponseEntity<String> aPIResponse) {
		cacheMap.put(key, aPIResponse);
		cacheItemsLife.put(key, System.currentTimeMillis());
	}
	
	public ResponseEntity<String> get(URI key) {
		ResponseEntity<String> value = cacheMap.get(key);
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
		logger.log( Level.INFO, "removed key: {0}",key);
		cacheMap.remove(key);
		cacheItemsLife.remove(key);
		logger.log( Level.INFO, "Cashe size after cleanup {0}", getSize());
	}
 
	public void emptyCache(){
		this.cacheMap.clear();
		this.cacheItemsLife.clear();
		this.requests=0;
		this.hits=0;
		this.misses=0;
	}

	public void cleanCashe(){
		logger.info("cleanCashe was called");
		long now = System.currentTimeMillis();
		
		for ( URI entry : cacheMap.keySet()){
			if (timeToLive<=now-cacheItemsLife.get(entry)){
				remove(entry);
			}
		}
	}

	public Map<URI, Long> cacheItemsLife(){
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
		return (hits/(hits+misses));
	}

	public int getSize(){
		if (cacheMap.size()!=cacheItemsLife.size()){
			logger.severe("ALERT: The cache map and cacheItemsLife map should have the same size.");
		}
		return this.cacheMap.size();
	}

}
