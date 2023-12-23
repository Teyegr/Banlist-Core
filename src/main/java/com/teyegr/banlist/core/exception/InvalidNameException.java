package com.teyegr.banlist.core.exception;

public class InvalidNameException extends Exception {
    public InvalidNameException(String name) {
        super(name + " is an invalid name");
    }
}
