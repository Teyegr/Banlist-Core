package com.teyegr.banlist.exception;

public class AccountAlreadyBannedException extends Exception {
    public AccountAlreadyBannedException(String name) {
        super(name + " is already banned");
    }
}
