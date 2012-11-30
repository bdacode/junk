package vmware;

import java.util.HashMap;
import java.util.LinkedList;

class LRUImplementation<K,V> implements LRU<K,V>
{
    LinkedList<K> cache = new LinkedList<K>();
    HashMap<K,V> cacheMap = new HashMap<K,V>();
    
    int limit;
    
    public void setLimit(int limit) {
		this.limit = limit;
	}

    @Override
    public void put(K key, V value) {
        V entry = (V) cacheMap.get(key);
        if (null == entry) {
        	cacheMap.put(key,entry);
        	cache.addFirst(key);
        	
        	if (cache.size() > limit) {
        		K leastRecentlyUsed = cache.getLast();
        		cache.removeLast();
        		cacheMap.remove(leastRecentlyUsed);
        	}
        } else {
        	cache.remove(key);
        	cache.addFirst(key);
        }
        
    }

    @Override
    public V get(K key) {
        V entry = (V) cacheMap.get(key);
        if (null != entry) {
        	cache.remove(key);
        	cache.addFirst(key);        
        }

        return null;
    }

    public void clear() {
        cache.clear();
        cacheMap.clear();
    }
    
}
