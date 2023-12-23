package lb;

import handler.IClientHandler;
import server.Utils;

import java.io.IOException;
import java.net.Socket;

/**
 * Reads client request and pass it to the backend
 */
public class LBClientHandler implements IClientHandler {


    @Override
    public void handleClient(Socket client) throws IOException {
        String requestMessage = Utils.readRequest(client.getInputStream());

//        System.out.println(requestMessage);
//        System.out.println("Generating response...");
//        Utils.writeOKResponse(client.getOutputStream(), "Hello from BE");
//        System.out.println("End.");

        client.close();
    }

    
}
