package com.teyegr.banlist.exception;

public class AccountNotBannedException extends Exception {
    public AccountNotBannedException(String name) {
        super(name + " not found in banlist");
    }
}
