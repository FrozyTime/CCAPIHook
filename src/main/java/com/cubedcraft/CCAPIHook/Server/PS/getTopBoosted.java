package com.cubedcraft.CCAPIHook.Server.PS;

import com.cubedcraft.CCAPIHook.EZJSONFetchData;
import com.cubedcraft.CCAPIHook.Exceptions.BadRequestException;
import com.cubedcraft.CCAPIHook.HTTPRequest;

import java.io.IOException;

public class getTopBoosted {

    public static String server(int index) throws BadRequestException {
        String data;
        try {
            data = HTTPRequest.getData("/servers/topboosted/");
        } catch(IOException ignored) { throw new BadRequestException(); }

        return new EZJSONFetchData(data).fetchTopBoostedServer(index);
    }

}