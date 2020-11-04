package com.cubedcraft.CCAPIHook.Utils;

import com.cubedcraft.CCAPIHook.Server.PS.Server;

import java.util.Date;

public class CacheData {
	public Date timeFetched;
	public Server data;

	public CacheData(Server data) {
		this.timeFetched = new Date();
		this.data = data;
	}
}
