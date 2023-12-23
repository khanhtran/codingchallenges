package handler;

import java.io.IOException;
import java.net.Socket;

public interface IClientHandler {

    void handleClient(Socket client) throws IOException;
}
