package com.teyegr.banlist;

import com.google.gson.Gson;
import com.teyegr.banlist.exception.AccountAlreadyBannedException;
import com.teyegr.banlist.exception.AccountNotBannedException;
import com.teyegr.banlist.exception.AccountNotExistsException;
import com.teyegr.banlist.exception.InvalidNameException;
import no.stelar7.api.r4j.basic.APICredentials;
import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.pojo.shared.RiotAccount;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Banlist implements Banable {
    private static final Gson gson = new Gson();
    private static final R4J api = new R4J(new APICredentials(System.getenv("RIOT_API_KEY")));
    private final RegionShard region;
    private final List<Ban> bans;

    public Banlist(RegionShard region) {
        this.region = region;
        this.bans = new ArrayList<>();
    }

    public Banlist() {
        this(RegionShard.EUROPE);
    }

    public String toString() {
        return gson.toJson(this);
    }

    public static Banlist fromString(String data) {
        return gson.fromJson(data, Banlist.class);
    }

    public void exportFile(File file) {
        Persistence.writeFile(toString(), file);
    }

    public static Banlist importFile(File file) {
        return Banlist.fromString(Persistence.readFile(file));
    }

    @Override
    public void ban(String name, String reason)
            throws AccountAlreadyBannedException, InvalidNameException, AccountNotExistsException {
        Account account = Account.fromString(name);
        if (account == null) {
            throw new InvalidNameException(name);
        }
        try {
            findByName(account.toString());
            throw new AccountAlreadyBannedException(name);
        } catch (AccountNotBannedException ignored) {}

        RiotAccount riotAccount = api.getAccountAPI().getAccountByTag(region, account.name, account.tag);

        if (riotAccount == null) {
            throw new AccountNotExistsException(name);
        }
        try {
            findByPuuid(riotAccount.getPUUID());
            throw new AccountAlreadyBannedException(name);
        } catch (AccountNotBannedException ignored) {}

        bans.add(new Ban(account.toString(), riotAccount.getPUUID(), reason));
    }

    @Override
    public void unban(String name) throws AccountNotBannedException {
        Ban ban = findByName(name);
        bans.remove(ban);
    }

    @Override
    public void rename(String alt, String name) throws AccountNotBannedException {
        Ban ban = findByName(alt);
        ban.rename(name);
    }

    @Override
    public Ban findByName(String name) throws AccountNotBannedException {
        return bans.stream().filter(e -> e.getName().equals(name)).findFirst()
                .orElseThrow(() -> new AccountNotBannedException(name));
    }

    @Override
    public Ban findByPuuid(String puuid) throws AccountNotBannedException {
        return bans.stream().filter(e -> e.getPuuid().equals(puuid)).findFirst()
                .orElseThrow(() -> new AccountNotBannedException(puuid));
    }
}
