package lb;

import server.IServer;

import java.io.IOException;

public class RoundRobinLoadBalancer extends AbstractLoadBalancer implements IServer {

    private int curr = -1;

    @Override
    public String chooseBackend() {
        curr = (curr + 1)%backends.size();

        return this.backends.get(curr);
    }

    @Override
    public void listen() throws IOException {

    }
}
