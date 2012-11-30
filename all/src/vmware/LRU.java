package vmware;


public interface LRU<K, V> {
	public void put(K key, V value);

	public V get(K K);

	public void clear();
}
