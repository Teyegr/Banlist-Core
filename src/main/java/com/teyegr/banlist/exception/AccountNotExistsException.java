package com.teyegr.banlist.exception;

public class AccountNotExistsException extends Exception{
    public AccountNotExistsException(String name) {
        super("No riot account exists with name " + name);
    }
}
