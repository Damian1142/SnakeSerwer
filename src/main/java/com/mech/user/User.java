package com.mech.user;

import com.mech.game.Game;
import com.mech.game.Snake;
import com.mech.view.Direction;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class User implements Runnable{

    Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    Snake snake;
    Runnable send;

    @SneakyThrows
    public User(Socket socket) {
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
        snake = new Snake(Color.ORANGE);
        send = () -> {
            try {
                outputStream.writeObject(Game.allPlayers);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        new Thread(send).start();
        Game.allPlayers.add(snake);
    }

    @SneakyThrows
    @Override
    public void run() {
        snake.setID(inputStream.readInt());
        while (true) {
            snake.setSnakeDirection((Direction)inputStream.readObject());
        }
    }
}
