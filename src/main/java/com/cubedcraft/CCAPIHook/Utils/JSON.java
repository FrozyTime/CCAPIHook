package com.cubedcraft.CCAPIHook.Utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSON {

    public static String read(String data) {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(data);
            JSONObject jsonObj = (JSONObject) obj;
            return jsonObj.toJSONString();
        } catch(Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}
