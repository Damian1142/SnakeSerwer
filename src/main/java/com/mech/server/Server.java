package com.mech.server;

import lombok.SneakyThrows;

import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private int port;
    public static int ids;
    private ServerSocket serverSocket;
    private boolean running = false;

    @SneakyThrows
    public Server(int port) {
        this.port = port;

        serverSocket = new ServerSocket(port);
    }

    public void start(){
        new Thread(this).start();
    }

    @SneakyThrows
    @Override
    public void run() {
        running = true;

        while (running){
            Socket socket = serverSocket.accept();
            initSocket(socket);
        }
        shutdown();
    }

    private void initSocket(Socket socket){
        Connection connection = new Connection(socket);
        new Thread(connection).start();

    }
    @SneakyThrows
    public void shutdown(){
        running = false;
        serverSocket.close();
    }
}
