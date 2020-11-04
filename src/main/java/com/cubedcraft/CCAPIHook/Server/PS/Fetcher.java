package com.cubedcraft.CCAPIHook.Server.PS;

import com.cubedcraft.CCAPIHook.CCAPIHook;
import com.cubedcraft.CCAPIHook.Exceptions.BadRequestException;
import com.cubedcraft.CCAPIHook.Utils.Cache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fetcher {
	private final Cache cache;
	private final CCAPIHook expansion;
	private final Parser parser = new Parser();

	public Fetcher(CCAPIHook expansion, Cache cache) {
		this.expansion = expansion;
		this.cache = cache;
	}

	public Server getServerData(String name) throws BadRequestException {
		Server cached = cache.get(name);
		if (cached != null) return cached;

		String rawData = null;

		try {
			rawData = getData("/server/" + name);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BadRequestException();
		}

		Server server = parser.parse(rawData);

		cache.set(name, server);

		return server;
	}

	/** Request data from the API.
	 * @param requestedData String: Add suffixes on what data to query from the API.
	 */
	private String getData(String requestedData) throws IOException {
		URL apiURL = new URL(this.expansion.API_URL+requestedData);

		HttpURLConnection httpURLConnection = (HttpURLConnection) apiURL.openConnection();

		httpURLConnection.setRequestMethod("GET");
		httpURLConnection.setRequestProperty("User-Agent", this.expansion.USER_AGENT);

		int apiResponseCode = httpURLConnection.getResponseCode();
		if(this.expansion.DEBUG) System.out.println("API response code, " + apiResponseCode);

		if(apiResponseCode != HttpURLConnection.HTTP_OK) {
			httpURLConnection.disconnect();
			return null;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		} in.close();

		httpURLConnection.disconnect();
		return response.toString();
	}

}
