package com.cubedcraft.CCAPIHook.Utils;

import com.cubedcraft.CCAPIHook.Server.PS.Server;

import java.util.Date;
import java.util.HashMap;

public class Cache {
	private final HashMap<String, CacheData> cache = new HashMap<>();
	private final int invalidateAfter;

	public Cache() {
		this(60 * 5);
	}

	public Cache(int invalidateAfter) {
		this.invalidateAfter = invalidateAfter;
	}

	public Server get(String name) {
		CacheData data = cache.get(name);
		if (data == null) return null;

		Date now = new Date();

		if((now.getTime()-data.timeFetched.getTime())/1000 > invalidateAfter) {
			cache.remove(name);
			return null;
		}

		return data.data;
	}

	public void set(String name, Server jsonData) {
		CacheData data = new CacheData(jsonData);
		cache.put(name, data);
	}
}
