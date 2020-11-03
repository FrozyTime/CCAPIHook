package com.cubedcraft.CCAPIHook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class CCAPIHook extends PlaceholderExpansion {
    private static final String baseURL = "https://api.playerservers.com";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final boolean debug = false;

    public CCAPIHook() {
        try {
            Exception exception1, exception2 = null;
        } catch(Exception e) { e.printStackTrace(); }
    }

    public String getIdentifier() {
        return "ccapi";
    }

    public String getAuthor() {
        return "CubedCraft";
    }

    public String getVersion() {
        return "1.0.0";
    }

    public String onPlaceholderRequest(Player player, String identifier) {
        List<String> values = Arrays.asList(identifier.split("_"));

        if(debug) {
            print(new String(String.valueOf(values.size())));
            print(values.get(1));
        }

        if(values.get(0).equalsIgnoreCase("votes")) {
            if(
                values.size() == 3 &&
                values.get(1).equalsIgnoreCase("server") &&
                values.get(2) != null ) {

                String votes;
                try { votes = getVotes(baseURL+"/"+values.get(1)+"/"+values.get(2)); } catch (IOException ignored) { return "-1"; }

                return votes;
            }
        }
        if(values.get(0).equalsIgnoreCase("boosters")) {
            if(
                values.size() == 3 &&
                values.get(1).equalsIgnoreCase("server") &&
                values.get(2) != null ) {

                String votes;
                try { votes = getBoosters(baseURL+"/"+values.get(1)+"/"+values.get(2)); } catch (IOException ignored) { return "-1"; }

                return votes;
            }
        }

        return null;

    }

    private String getVotes(String fakeURL) throws IOException {
        URL theRealURL = new URL(fakeURL);

        HttpURLConnection httpURLConnection = (HttpURLConnection) theRealURL.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = httpURLConnection.getResponseCode();
        if (debug) System.out.println(responseCode);

        if (responseCode != HttpURLConnection.HTTP_OK) {
            httpURLConnection.disconnect(); // efficiency.
            return "-1";
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in                                                                                                                 .                                          readLine()) != null) {
            response.append(inputLine);
        } in .close();

        String votes = response.toString().split("votes\":")[1].split(",")[0].replace(" ", "");
        if(debug) System.out.println(response.toString().split("votes\":")[1].split(",")[0].replace(" ", ""));

        httpURLConnection.disconnect(); // efficiency.
        return votes;
    }

    private String getBoosters(String fakeURL) throws IOException {
        URL theRealRealURL = new URL(fakeURL);

        HttpURLConnection httpURLConnection = (HttpURLConnection) theRealRealURL.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = httpURLConnection.getResponseCode();
        if (debug) System.out.println(responseCode);

        if (responseCode != HttpURLConnection.HTTP_OK) {
            httpURLConnection.disconnect(); // efficiency.
            return "-1";
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in                                                                                                                 .                                          readLine()) != null) {
            response.append(inputLine);
        } in .close();

        String votes = response.toString().split("boosters\":")[1].split(",")[0].replace(" ", "");
        if(debug) System.out.println(response.toString().split("boosters\":")[1].split(",")[0].replace(" ", ""));

        return votes;
    }

    private void print(String theErrorMessage) {
        System.out.println(theErrorMessage);
    }
}
