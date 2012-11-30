package zoosk;

import java.util.HashMap;

public class LRUCache {

	HashMap<Object,Object> cache = new HashMap<Object,Object>();
	int capacity = 10;

	class DoubleLinkedListNode {
		Object key;
		DoubleLinkedListNode next;
		DoubleLinkedListNode prev;
	}
	
	public LRUCache() {
	}
	
	DoubleLinkedListNode removeFirstElement() {
		return null;
	}
	
	void addLast(Object key) {
	}

	void remove(Object key) {
	}

	void put(Object key,Object value) {
		if (get(key) == null && cache.size() >= capacity) {
			DoubleLinkedListNode firstElement = removeFirstElement();
			cache.remove(firstElement.key);
		}
		cache.put(key, value);
		addLast(key);
	}
	
	Object get(Object key) {
		Object result = cache.get(key);
		if (result != null) {
			// move to front
			remove(key);
			addLast(key);
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
