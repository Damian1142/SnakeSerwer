package com.mech.server.player;

import com.mech.server.Connection;

public class NetPlayer {
    public Connection c;
    public int id;
    public String name;

    public NetPlayer(int id, String name, Connection c) {
        this.id = id;
        this.name = name;
        this.c = c;
    }


}
