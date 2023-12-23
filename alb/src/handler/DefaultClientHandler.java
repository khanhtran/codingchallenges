package handler;

import server.Utils;

import java.io.IOException;
import java.net.Socket;

public class DefaultClientHandler implements IClientHandler {

    private String handleName;

    public DefaultClientHandler(String handlerName) {
        this.handleName = handlerName;
    }
    @Override
    public void handleClient(Socket client) throws IOException {
        String requestMessage = Utils.readRequest(client.getInputStream());

        System.out.println(requestMessage);
        System.out.println("Generating response...");
        Utils.writeOKResponse(client.getOutputStream(), "Hello from " + handleName);
        System.out.println("End.");

        client.close();
    }
}
