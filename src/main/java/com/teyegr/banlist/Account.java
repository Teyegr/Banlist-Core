package com.teyegr.banlist;

public class Account {
    String name;
    String tag;

    public Account(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public String toString() {
        return name + '#' + tag;
    }

    public static Account fromString(String name) {
        if (!name.contains("#")) {
            return null;
        }
        String[] splitted = name.split("#", 2);
        String accountName = splitted[0].strip();
        String accountTag = splitted[1].strip();
        return new Account(accountName, accountTag);
    }
}
