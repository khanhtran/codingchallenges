package server;

import handler.IClientHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultServer implements IServer {
    private ExecutorService executorService = Executors.newFixedThreadPool(100);
    private String name;

    private int port;

    private IClientHandler clientHandler;

    public DefaultServer(int port, String name, IClientHandler clientHandler) {
        this.name = name;
        this.port = port;
        this.clientHandler = clientHandler;
    }

    @Override
    public void listen() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(this.name + " started at " + port);
            while (true) {
                Socket client = serverSocket.accept();
                executorService.submit(() -> {
                    try {
                        clientHandler.handleClient(client); client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
}
