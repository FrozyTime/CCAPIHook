package com.cubedcraft.CCAPIHook;

public class EZJSONFetchData {

    private static final CCAPIHook CCAPIHook = new CCAPIHook();

    String data;

    public EZJSONFetchData(String data) {
        this.data = data;
        if(CCAPIHook.isDebugging()) System.out.println("Parsing data, " + this.data);
    }

    public static String fetchPSVotes(String data) {
        return "-1";
    }

    public static String fetchPSBoosters(String data) {
        return "-1";
    }

    public static String fetchTopVotedServer(int index) {
        return "-1";
    }

    public static String fetchTopBoostedServer(int index) {
        return "-1";
    }
}
