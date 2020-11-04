package com.cubedcraft.CCAPIHook;

import com.cubedcraft.CCAPIHook.Utils.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EZJSONFetchData {

    private static final CCAPIHook CCAPI = CCAPIHook.get();
    private static final JSONParser parser = new JSONParser();

    String data;

    /** Init
     * @param data String: JSON
     */
    public EZJSONFetchData(String data) {
        this.data = data;
    }

    // TODO: 11/3/2020 Recode this to work with new JSON. 
    /** General Use 
     * @param identifier String : Identifier 
     */
    public String getServerData(String identifier) throws ArrayIndexOutOfBoundsException {
        return this.data.split(identifier+"\":")[1].split(",")[0].replace(" ", "");
    }

    /** Get server votes. **/
    public String getVotes() throws ArrayIndexOutOfBoundsException {
        return this.data.split("votes"+"\":")[1].split(",")[0].replace(" ", "");
    }

    /** Get server boosters. **/
    public String getBoosters() throws ArrayIndexOutOfBoundsException {
        return this.data.split("boosters"+"\":")[1].split(",")[0].replace(" ", "");
    }

    /** Get if the server is suspended. **/
    public String getSuspended() throws ArrayIndexOutOfBoundsException {
        return this.data.split("suspended"+"\":")[1].split(",")[0].replace(" ", "");
    }

    /** Get a servers plan id. **/
    public String getPlanID() throws ArrayIndexOutOfBoundsException {
        return this.data.split("plan_id"+"\":")[1].split(",")[0].replace(" ", "");
    }

    /** Get the amount of online players on a server. **/
    public String getAmountOfPlayers() throws ArrayIndexOutOfBoundsException {
        return this.data.split("onlinePlayers"+"\":")[1].split(",")[0].replace(" ", "");
    }

    // TODO: 11/4/2020 Use this.
    /** Get the software a server is using. **/
    public String getServerSoftware() throws ArrayIndexOutOfBoundsException {
        return this.data.split("software"+"\":")[1].split(",")[0].replace(" ", "");
    }

    /** Get the max amount of players allowed on a server. **/
    public String getMaxAmountOfPlayers() throws ArrayIndexOutOfBoundsException {
        return this.data.split("maxPlayers"+"\":")[1].split(",")[0].replace(" ", "");
    }

    /** Fetch all of the top 10 most voted servers by index.
     * @param index int: Server placement
     */
    public String getTopVotedServer(int index) throws ParseException {
        if(index < 0 || index > 10) return "N/A";
        // We are most likely going to have people using <1-10> so just remove 1.
        if(index != 0) index = index - 1;

        if(CCAPI.isDebugging()) System.out.println("Type: Vote");

        JSONObject json = (JSONObject) parser.parse(JSON.read(this.data));
        JSONArray topVotedServers = (JSONArray) json.get("servers");

        if(topVotedServers.get(index) != null) {
            JSONObject votedServer = (JSONObject) topVotedServers.get(index);
            if(CCAPI.isDebugging()) System.out.println("At index " + index + " we found the server " + votedServer.get("name"));
            return (String) votedServer.get("name");
        }

        // Should be unreachable?
        return "N/A";
    }

    /** Fetch all of the top 10 most boosted servers by index.
     * @param index int: Server placement
     */
    public String getTopBoostedServer(int index) throws ParseException {
        if(index < 0 || index > 10) return "N/A";
        // We are most likely going to have people using <1-10> so just remove 1.
        if(index != 0) index = index - 1;

        if(CCAPI.isDebugging()) System.out.println("Type: Boosts");

        JSONObject json = (JSONObject) parser.parse(JSON.read(this.data));
        JSONArray topBoostedServers = (JSONArray) json.get("servers");

        if(topBoostedServers.get(index) != null) {
            JSONObject boostedServer = (JSONObject) topBoostedServers.get(index);
            if(CCAPI.isDebugging()) System.out.println("At index " + index + " we found the server " + boostedServer.get("name"));
            return (String) boostedServer.get("name");
        }

        // Should be unreachable?
        return "N/A";
    }
}
