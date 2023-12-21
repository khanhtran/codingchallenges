package http.server;

import java.io.IOException;
import java.net.Socket;

public interface ClientHandler {

    void handleClient(Socket client) throws IOException;
}
