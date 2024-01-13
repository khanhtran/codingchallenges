import lb.ILoadBalancer;
import lb.RoundRobinLoadBalancer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
//        final List<String> backends = Arrays.asList("http://localhost:8080", "http://localhost:8081", "http://localhost:8082");

//        RoundRobinLoadBalancer lb = new RoundRobinLoadBalancer(80, backends);
//
//        lb.listen();

        try (ServerSocket serverSocket = new ServerSocket(80)) {
            System.out.println("Main started at " + 80);
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("client: " + client);
            }
        }

    }
}
