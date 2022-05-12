package com.mech.server;

import com.google.gson.Gson;
import com.mech.game.MainGame;
import com.mech.game.snake.Snake;
import com.mech.server.packets.PlayerPacket;
import com.mech.server.packets.PlayerPacketType;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable{

    public Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private EventListener listener;
    private int id;
    private String name;
    public Snake snake;


    @SneakyThrows
    public Connection(Socket socket) {
        this.socket = socket;
        id = 0;
        name = "";

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        listener = new EventListener(this);
        snake = new Snake();
        MainGame.snakes.add(snake);
    }


    @Override
    public void run() {
        while (socket.isConnected()){
            Gson gson = new Gson();
            PlayerPacket data;
            try {
                data = gson.fromJson((String) in.readObject(), PlayerPacket.class);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Nieprawidłowe dane od klienta\nkoniec połączenia");
                listener.removePlayer(new PlayerPacket(id,name, PlayerPacketType.REMOVE));
                break;
            }
            listener.received(data);
        }
        close();
    }

    @SneakyThrows
    public void close(){
        listener.timer.stop();
        in.close();
        out.close();
        socket.close();
    }

    @SneakyThrows
    public void sendObject(Object p) {
        if (socket.isConnected()){
            Gson gson = new Gson();
            out.writeObject(gson.toJson(p));
            out.flush();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
