package http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws IOException {
        new HttpServer(80);
    }
    public HttpServer(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket client = serverSocket.accept()) {
//                    executorService.submit(() -> handleClient(client));
                    new Thread(() -> handleClient(client)).start();
                }
            }
        }
    }

    private static void handleClient(Socket client) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

            StringBuilder requestBuilder = new StringBuilder();
            String line;
            while (!(line = br.readLine()).isBlank()) {
                requestBuilder.append(line + "\r\n");
            }

            String request = requestBuilder.toString();
            System.out.println(request);

            sendResponse(client, "response".getBytes());

            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void sendResponse(Socket client, byte[] content) throws IOException {
        PrintWriter pw = new PrintWriter(client.getOutputStream());
        pw.println("HTTP/1.1 200 OK");
        pw.println(new Date());
        pw.println();
        pw.println(new String(content));
        pw.flush();
        pw.close();
    }
}
