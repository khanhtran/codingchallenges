package lb;

import java.util.List;

public abstract class AbstractLoadBalancer {

    protected List<String> backends;

    public void LoadBalancer(List<String> backends) {
        this.backends = backends;
    }
    public abstract String chooseBackend();
}
