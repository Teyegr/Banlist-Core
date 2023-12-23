package com.teyegr.banlist.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ban {
    private final List<String> nameList;
    private final String puuid;
    private final String reason;
    private final String date;

    public Ban(String name, String puuid, String reason, String date) {
        this.puuid = puuid;
        this.reason = reason;
        this.date = date;
        this.nameList = new ArrayList<>();
        this.nameList.add(name);
    }

    public Ban(String name, String puuid, String reason) {
        this(name, puuid, reason, LocalDate.now().toString());
    }

    public String getPuuid() {
        return puuid;
    }

    public String getName() {
        return nameList.getLast();
    }

    public void rename(String name) {
        nameList.add(name);
    }
}
