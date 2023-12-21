import http.server.HttpServer;
import http.server.Utils;

import java.io.IOException;

public class Be {
    public static void main(String[] args) throws IOException {
        new HttpServer(8080, "BE", client -> {
            String requestMessage = Utils.readRequest(client.getInputStream());

            System.out.println(requestMessage);
            System.out.println("Generating response...");
            Utils.writeOKResponse(client.getOutputStream(), "Hello from BE");
            System.out.println("End.");

            client.close();

        });

//        new HttpServer(8080, "BE");
    }
}
