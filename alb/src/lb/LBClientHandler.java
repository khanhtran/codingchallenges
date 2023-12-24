package lb;

import handler.IClientHandler;
import server.Utils;

import java.io.IOException;
import java.net.Socket;

/**
 * Reads client request and pass it to the backend
 */
public class LBClientHandler implements IClientHandler {

    private String delegate;

    public LBClientHandler (String delegate) {
        this.delegate = delegate;
    }

    @Override
    public void handleClient(Socket client) throws IOException {
        String requestMessage = Utils.readRequest(client.getInputStream());
        System.out.println(requestMessage);
        System.out.println("Forwarding request to " + delegate);

        System.out.println("Reading response from " + delegate);


        client.close();
    }

    
}
