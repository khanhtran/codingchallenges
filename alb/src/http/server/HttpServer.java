package http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    private String name;

    public HttpServer(int port, String name, ClientHandler handler) throws IOException {
        this.name = name;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(this.name + " started at " + port);
            while (true) {
                Socket client = serverSocket.accept();
                executorService.submit(() -> {
                    try {
                        handler.handleClient(client); client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
}
