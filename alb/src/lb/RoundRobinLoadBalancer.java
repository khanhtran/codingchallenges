package lb;

import handler.IClientHandler;
import server.IServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoundRobinLoadBalancer extends AbstractLoadBalancer implements IServer {

    private int curr = -1;

    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    public RoundRobinLoadBalancer(int port, List<String> backends) {
        this.port = port;
        this.backends = backends;
    }
    @Override
    public String chooseBackend() {
        curr = (curr + 1)%backends.size();
        return this.backends.get(curr);
    }

    @Override
    public void listen() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(this.getClass().getSimpleName() + " started at " + port);
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("client: " + client);
                IClientHandler clientHandler = new LBClientHandler(chooseBackend());
                clientHandler.handleClient(client);
//                client.close();

//                executorService.submit(() -> {
//                    try {
//                        clientHandler.handleClient(client); client.close();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
            }
        }
    }
}
