package com.cubedcraft.CCAPIHook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequest {
    private static final CCAPIHook CCAPIHook = new CCAPIHook();

    /** Request data from the API.
     * @param requestedData String: Add suffixes on what data to query from the API.
     */
    public static String getData(String requestedData) throws IOException {
        URL apiURL = new URL(CCAPIHook.getAPIURL()+requestedData);

        HttpURLConnection httpURLConnection = (HttpURLConnection) apiURL.openConnection();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", CCAPIHook.getUserAgent());

        int apiResponseCode = httpURLConnection.getResponseCode();
        if(CCAPIHook.isDebugging()) System.out.println("API response code, " + apiResponseCode);

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
