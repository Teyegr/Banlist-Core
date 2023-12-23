package com.teyegr.banlist.core.exception;

public class AccountAlreadyBannedException extends Exception {
    public AccountAlreadyBannedException(String name) {
        super(name + " is already banned");
    }
}
