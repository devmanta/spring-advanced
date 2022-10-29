package hello.advanced.part2.proxy.config.v1_proxy.concrete_proxy;

import hello.advanced.part1.trace.TraceStatus;
import hello.advanced.part1.trace.logtrace.LogTrace;
import hello.advanced.part2.proxy.app.v2.ProxyOrderRepositoryV2;

public class OrderRepositoryConcreteProxy extends ProxyOrderRepositoryV2 {

    private final ProxyOrderRepositoryV2 target;
    private final LogTrace trace;

    public OrderRepositoryConcreteProxy(ProxyOrderRepositoryV2 target, LogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try{
            status = trace.begin("OrderRepositoryConcreteProxy.save");
            target.save(itemId);
            trace.end(status);
        } catch(Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
