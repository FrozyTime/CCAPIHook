package com.cubedcraft.CCAPIHook.Exceptions;

public class BadRequestException extends Exception {
    /** Thrown whenever there is an error contacting the API. **/
    public BadRequestException() {
        super("Error contacting server API.");
    }
}
