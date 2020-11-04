package com.cubedcraft.CCAPIHook;

public class EZJSONFetchData {

    private static final CCAPIHook CCAPIHook = new CCAPIHook();

    String data;

    /**
     * @param data String: JSON
     */
    public EZJSONFetchData(String data) {
        this.data = data;
        if(CCAPIHook.isDebugging()) System.out.println("Parsing data, " + this.data);
    }

    /** General Use **/
    public String fetchServerData(String identifier) throws ArrayIndexOutOfBoundsException {
        return this.data.split(identifier+"\":")[1].split(",")[0].replace(" ", "");
    }

    /** Fetch all of the top 10 most voted servers by index.
     * @param index int: Server placement
     */
    public String fetchTopVotedServer(int index) {
        return "-1";
    }

    /** Fetch all of the top 10 most boosted servers by index.
     * @param index int: Server placement
     */
    public String fetchTopBoostedServer(int index) {
        return "-1";
    }
}
