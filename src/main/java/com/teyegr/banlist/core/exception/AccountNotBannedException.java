package com.teyegr.banlist.core.exception;

public class AccountNotBannedException extends Exception {
    public AccountNotBannedException(String name) {
        super(name + " not found in banlist");
    }
}
