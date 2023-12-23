package com.teyegr.banlist.core;

import com.teyegr.banlist.core.exception.AccountAlreadyBannedException;
import com.teyegr.banlist.core.exception.AccountNotBannedException;
import com.teyegr.banlist.core.exception.AccountNotExistsException;
import com.teyegr.banlist.core.exception.InvalidNameException;

public interface Banable {
    void ban(String name, String reason) throws AccountAlreadyBannedException, InvalidNameException, AccountNotExistsException;
    void unban(String name) throws AccountNotBannedException;
    void rename(String alt, String name) throws AccountNotBannedException;
    Ban findByName(String name) throws AccountNotBannedException;
    Ban findByPuuid(String puuid) throws AccountNotBannedException;
}
