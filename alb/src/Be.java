import http.server.HttpServer;

import java.io.IOException;

public class Be {
    public static void main(String[] args) throws IOException {
        new HttpServer(8080, "BE");
    }
}
