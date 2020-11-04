package com.cubedcraft.CCAPIHook.Server.PS;

import com.cubedcraft.CCAPIHook.EZJSONFetchData;
import com.cubedcraft.CCAPIHook.Exceptions.BadRequestException;
import com.cubedcraft.CCAPIHook.HTTPRequest;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class getTopVoted {

    public static String server(int index) throws BadRequestException, ParseException {
        String data;
        try {
            data = HTTPRequest.getData("/servers/topvoted");
        } catch(IOException ignored) { throw new BadRequestException(); }

        return new EZJSONFetchData(data).getTopVotedServer(index);
    }

}
