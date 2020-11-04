package com.cubedcraft.CCAPIHook.Server.PS;

import com.google.gson.Gson;

public class Parser {
	private final Gson gson = new Gson();

	public Server parse(String data) {
		return gson.fromJson(data, Server.class);
	}
}
