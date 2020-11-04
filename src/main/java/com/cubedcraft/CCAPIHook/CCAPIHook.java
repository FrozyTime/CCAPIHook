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
    /** Get the expansion identifier. **/
    public String getIdentifier() {
        return "ccapi";
    }

    /** Get the expansion author. **/
    public String getAuthor() {
        return "CubedCraft";
    }

    /** Get the expansion version. **/
    public String getVersion() {
        return "1.0.1";
    }

    /** Get the main API URL. **/
    public String getAPIURL() {
        return "https://api.playerservers.com";
    }

    /** Get the API User Agent. **/
    public String getUserAgent() {
        return "Mozilla/5.0";
    }

    /** Get whether or not to send debug data to console. **/
    public boolean isDebugging() {
        return false;
    }

    /** Expansion placeholder request.
     * @param player Player: Placeholders require a player to fetch data.
     * @param identifier String: Identifier/Values of the placeholder request.
     */
    public String onPlaceholderRequest(Player player, String identifier) {
        List<String> values = Arrays.asList(identifier.split("_"));

        return null;
    }
}
