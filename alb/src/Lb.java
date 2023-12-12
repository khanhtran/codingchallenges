import http.server.HttpServer;

import java.io.IOException;

public class Lb {
    public static void main(String[] args) throws IOException {
        new HttpServer(80, "LB");
    }
}
