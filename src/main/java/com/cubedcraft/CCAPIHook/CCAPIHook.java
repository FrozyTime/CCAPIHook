package com.cubedcraft.CCAPIHook;

import com.cubedcraft.CCAPIHook.Exceptions.BadRequestException;
import com.cubedcraft.CCAPIHook.Server.PS.*;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

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

        if(values.get(0).equalsIgnoreCase("votes") || values.get(0).equalsIgnoreCase("vote")) {
            if(values.size() == 3 && values.get(1).equalsIgnoreCase("server") && values.get(2) != null) {
                String server = values.get(2);
                try { return getVotes.server(server); } catch (BadRequestException | ArrayIndexOutOfBoundsException e) { e.printStackTrace(); return "-1"; }
            }
        }

        else if(values.get(0).equalsIgnoreCase("boosters") || values.get(0).equalsIgnoreCase("booster")) {
            if(values.size() == 3 && values.get(1).equalsIgnoreCase("server") && values.get(2) != null) {
                String server = values.get(2);
                try { return getBoosters.server(server); } catch (BadRequestException | ArrayIndexOutOfBoundsException e) { e.printStackTrace(); return "-1"; }
            }
        }

        else if(values.get(0).equalsIgnoreCase("suspended")) {
            if(values.size() == 3 && values.get(1).equalsIgnoreCase("server") && values.get(2) != null) {
                String server = values.get(2);
                try { return getSuspended.server(server); } catch (BadRequestException | ArrayIndexOutOfBoundsException e) { e.printStackTrace(); return "N/A"; }
            }
        }

        else if(values.get(0).equalsIgnoreCase("planid")) {
            if(values.size() == 3 && values.get(1).equalsIgnoreCase("server") && values.get(2) != null) {
                String server = values.get(2);
                try { return getPlanID.server(server); } catch (BadRequestException | ArrayIndexOutOfBoundsException e) { e.printStackTrace(); return "N/A"; }
            }
        }

        else if(values.get(0).equalsIgnoreCase("online")) {
            if(values.size() == 3 && values.get(1).equalsIgnoreCase("server") && values.get(2) != null) {
                String server = values.get(2);
                try { return getAmountOfPlayers.server(server); } catch (BadRequestException | ArrayIndexOutOfBoundsException e) { e.printStackTrace(); return "N/A"; }
            }
        }

        else if(values.get(0).equalsIgnoreCase("maxonline")) {
            if(values.size() == 3 && values.get(1).equalsIgnoreCase("server") && values.get(2) != null) {
                String server = values.get(2);
                try { return getMaxAmountOfPlayers.server(server); } catch (BadRequestException | ArrayIndexOutOfBoundsException e) { e.printStackTrace(); return "N/A"; }
            }
        }

        else if(values.get(0).equalsIgnoreCase("topboosted")) {
            if(values.size() == 3 && values.get(1).equalsIgnoreCase("server") && values.get(2) != null) {
                int index;
                try { index = Integer.parseInt(values.get(2)); } catch (NumberFormatException ignored) { return "N/A"; }
                try { return getTopBoosted.server(index); } catch (BadRequestException | ArrayIndexOutOfBoundsException | ParseException e) { e.printStackTrace(); return "N/A"; }
            }
        }

        else if(values.get(0).equalsIgnoreCase("topvoted")) {
            if(values.size() == 3 && values.get(1).equalsIgnoreCase("server") && values.get(2) != null) {
                int index;
                try { index = Integer.parseInt(values.get(2)); } catch (NumberFormatException ignored) { return "N/A"; }
                try { return getTopVoted.server(index); } catch (BadRequestException | ArrayIndexOutOfBoundsException | ParseException e) { e.printStackTrace(); return "N/A"; }
            }
        }

        return null;
    }
}
