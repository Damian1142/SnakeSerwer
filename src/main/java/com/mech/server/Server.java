package com.mech.server;

import com.mech.user.User;
import com.sun.jmx.remote.internal.ArrayQueue;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread{
    ServerSocket serverSocket;
    ExecutorService executorService;
    int port;

    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            Socket socket = serverSocket.accept();
            User user = new User(socket);
            executorService.submit(user);
        }
    }

    public Server() throws IOException {
        port = 6500;
        serverSocket = new ServerSocket(port,100);
        executorService = Executors.newFixedThreadPool(50);
        start();
    }
}
