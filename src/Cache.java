import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<K,V> extends LinkedHashMap<K,V> {
	int CAPACITY;

	public Cache(int capacity){
		CAPACITY = capacity;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > CAPACITY;
	}

}
