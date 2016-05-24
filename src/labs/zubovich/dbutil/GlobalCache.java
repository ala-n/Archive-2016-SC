package labs.zubovich.dbutil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey on 24.05.2016.
 */
public class GlobalCache {

	public enum Key {
		KLOC_Map
	}

	private static Map<Key, Object> cache = new HashMap<>();

	public static void init(Key key, Object value) {
		cache.put(key, value);
	}

	public static Object get(Key key) {
		return cache.get(key);
	}

}
