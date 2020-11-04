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

    /** Fetch the votes of the specified server. **/
    public String fetchPSVotes() throws ArrayIndexOutOfBoundsException {
        return this.data.split("votes\":")[1].split(",")[0].replace(" ", "");
    }

    /** Fetch the boosters of the specified server. **/
    public String fetchPSBoosters() throws ArrayIndexOutOfBoundsException {
        return this.data.split("boosters\":")[1].split(",")[0].replace(" ", "");
    }

    /** General Use **/
    public String fetchOtherData(String identifier) throws ArrayIndexOutOfBoundsException {
        return this.data.split(identifier+"\":")[1].split(",")[0].replace(" ", "");
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
