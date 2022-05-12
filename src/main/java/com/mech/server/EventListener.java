package com.mech.server;

import com.google.gson.Gson;
import com.mech.game.MainGame;
import com.mech.game.view.Direction;
import com.mech.game.view.przeszkody.Spawner;
import com.mech.server.handlers.PlayerHandler;
import com.mech.server.packets.Map;
import com.mech.server.packets.PlayerPacket;
import com.mech.server.packets.PlayerPacketType;
import com.mech.server.player.NetPlayer;

import javax.swing.*;

public class EventListener {

    public EventListener(Connection connection) {
        gson = new Gson();
        c = connection;
        timer = new Timer(100,e -> {
            c.sendObject(new PlayerPacket(c.getId(), c.getName(), PlayerPacketType.MAP, gson.toJson(Map.builder().snakes(MainGame.snakes).obstacles(Spawner.przeszkody).foods(Spawner.foods).build())));
        });
        timer.start();
    }
    public Timer timer;
    private Gson gson;
    public Connection c;
    public void received(PlayerPacket packet){
        if (packet.getType() == PlayerPacketType.ADD){
            Server.ids++;
            int id = Server.ids;
            c.sendObject(new PlayerPacket(id,"ID",PlayerPacketType.ID));
            c.setId(id);
            c.setName(packet.getName());

            PlayerHandler.players.put(id, new NetPlayer(packet.getId(),packet.getName(),c));
            sendAll(packet);

            System.out.println("Gracz " + packet.getName() + " Dołączył do Gry");
        }else if(packet.getType() == PlayerPacketType.REMOVE){
            removePlayer(packet);
        } else if (packet.getType() == PlayerPacketType.TEXT) {
            sendAll(packet);
        } else if (packet.getType() == PlayerPacketType.DIR) {
            c.snake.setSnakeDirection(gson.fromJson(packet.getPacket(), Direction.class));
        }
    }

    private void sendAll(PlayerPacket packet) {
        PlayerHandler.players
                .values()
                .stream()
                .filter(n -> n.id != packet.getId())
                .forEach(n -> n.c.sendObject(packet));
    }

    public void removePlayer(PlayerPacket packet) {
        System.out.println("Gracz " + packet.getName() + " Wyszedł z Gry");
        PlayerHandler.players.remove(packet.getId());
        sendAll(packet);
    }
}
