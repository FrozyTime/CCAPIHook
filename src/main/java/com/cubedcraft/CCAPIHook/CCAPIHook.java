package com.cubedcraft.CCAPIHook;

import com.cubedcraft.CCAPIHook.Exceptions.BadRequestException;
import com.cubedcraft.CCAPIHook.Server.PS.*;
import com.cubedcraft.CCAPIHook.Utils.Cache;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import java.util.Arrays;
import java.util.List;

public class CCAPIHook extends PlaceholderExpansion {
	public Cache cache = new Cache();
	public Fetcher fetcher = new Fetcher(this, cache);

	/** Version of the plugin. **/
	public final String VERSION = "1.0.1";

	/** Get the main API URL. **/
	public final String API_URL ="https://api.playerservers.com";

	/** User Agent to use when sending requests. **/
	public final String USER_AGENT = "CCAPI/"+ VERSION;

	/** Get whether or not to send debug data to console. **/
	public boolean DEBUG = false;

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
        return VERSION;
    }

	/** Expansion placeholder request.
	 * @param player Player: Placeholders require a player to fetch data.
	 * @param identifier String: Identifier/Values of the placeholder request.
	 */
	public String onPlaceholderRequest(Player player, String identifier) {
		List<String> values = Arrays.asList(identifier.split("_"));
        if (!values.get(0).equalsIgnoreCase("ccapi")) return null;

	    String serverName = values.get(2);
	    if (serverName == null) return null;

	    Server server;
	    try {
		    server = this.fetcher.getServerData(serverName);
	    } catch (BadRequestException e) {
		    e.printStackTrace();
		    return "Error";
	    }

	    String type = values.get(1);

	    Object value = null;
	    
	    switch(type.toLowerCase()) {
		    case "votes":
		    	value = server.votes;
		    	break;
		    case "boosters":
		    	value = server.boosters;
		    	break;
		    case "suspended":
		    	value = server.suspended;
		    	break;
		    case "planid":
		    	value = server.plan_id;
		    	break;
		    case "online":	
		    	value = server.online;
		    	break;
		    case "maxonline":
		    	value = server.maxPlayers;
		    	break;
	    }

	    return String.valueOf(value);

	    //ccapi_votes_<server name>
		//ccapi_boosters_<server name>
		//ccapi_suspended_<server name>
		//ccapi_planid_<server name>
		//ccapi_online_<server name>
		//ccapi_maxonline_<server name>
    }
}
