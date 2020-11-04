package com.cubedcraft.CCAPIHook;

import com.cubedcraft.CCAPIHook.Exceptions.BadRequestException;
import com.cubedcraft.CCAPIHook.Server.PS.*;
import com.cubedcraft.CCAPIHook.Utils.Cache;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
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

	/** Whether or not to send debug data to console. **/
	public boolean DEBUG = false;

	/** Get the expansion identifier. **/
	public String getIdentifier() {
        return "ccapi";
    }

	/** Get the expansion author. **/
	public String getAuthor() {
        return "DebitCardz, unlimitedcoder2";
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

		try {
			Server server = this.fetcher.getServerData(serverName);
			String type = values.get(1);

			Field f = Server.class.getField(type.toLowerCase());
			return String.valueOf(f.get(server));
		} catch (BadRequestException | NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
			return "Error";
		}
	}
}
