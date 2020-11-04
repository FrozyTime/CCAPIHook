package com.cubedcraft.CCAPIHook.Server.PS;

import com.cubedcraft.CCAPIHook.Exceptions.BadRequestException;
import com.cubedcraft.CCAPIHook.HTTPRequest;

import java.io.IOException;

public class getVotes {

    /** Get the votes from the specified server.
     * @param server String: Server name
     */
    public static String getVotes(String server) throws BadRequestException {

        String data;
        try {
            data = HTTPRequest.getData("/server/"+server);
        } catch(IOException ignored) { throw new BadRequestException(); }

        return "xd";
    }

}
