package vmware;

import java.util.LinkedHashMap;

class LRUImplementation2<K, V> implements LRU<K, V> {
	int limit;

	LinkedHashMap<K, V> cache = new LinkedHashMap<K, V>(10, (float) 0.5, true) {
		private static final long serialVersionUID = 1L;

		protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
			return size() > limit;
		};
	};

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
    public void put(K key, V value) {
    	cache.put(key, value);
    }

	@Override
	public V get(K key) {
		return cache.get(key);
	}

	public void clear() {
		cache.clear();
	}

}
