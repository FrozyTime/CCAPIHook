package com.cubedcraft.CCAPIHook;

public class EZJSONFetchData {

    private static final CCAPIHook CCAPIHook = new CCAPIHook();

    String data;

    public EZJSONFetchData(String data) {
        this.data = data;
        if(CCAPIHook.isDebugging()) System.out.println("Parsing data, " + this.data);
    }

    /** Fetch the votes of the specified server.
     * @param data String: JSON
     */
    public static String fetchPSVotes(String data) {
        return "-1";
    }

    /** Fetch the boosters of the specified server.
     * @param data String: JSON
     */
    public static String fetchPSBoosters(String data) {
        return "-1";
    }

    /** Fetch all of the top 10 most voted servers by index.
     * @param index int: Server placement
     */
    public static String fetchTopVotedServer(int index) {
        return "-1";
    }

    /** Fetch all of the top 10 most boosted servers by index.
     * @param index int: Server placement
     */
    public static String fetchTopBoostedServer(int index) {
        return "-1";
    }
}
