package com.mech;

import com.mech.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(6500);
        server.start();
    }
}
