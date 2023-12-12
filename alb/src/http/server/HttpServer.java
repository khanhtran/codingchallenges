package http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    private String name;

    public static void main(String[] args) throws IOException {
        new HttpServer(80, "LB");
    }
    public HttpServer(int port, String name) throws IOException {
        this.name = name;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(this.name + " started at " + port);
            while (true) {
                Socket client = serverSocket.accept();
                executorService.submit(() -> handleClient(client));
            }
        }
    }

    private void handleClient(Socket client) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

            StringBuilder requestBuilder = new StringBuilder();
            String line;
            while (!(line = br.readLine()).isBlank()) {
                requestBuilder.append(line + "\r\n");
            }

            String request = requestBuilder.toString();
            System.out.println(request);

            sendResponse(client, ("Response from " + this.name).getBytes());

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
