package lb;

import server.IServer;

import java.util.List;

public abstract class AbstractLoadBalancer implements ILoadBalancer, IServer {
    protected List<String> backends;
    protected int port;

    @Override
    public abstract String chooseBackend();
}
