package hello.advanced.part2.proxy.config.v1_proxy.concrete_proxy;

import hello.advanced.part1.trace.TraceStatus;
import hello.advanced.part1.trace.logtrace.LogTrace;
import hello.advanced.part2.proxy.app.v2.ProxyOrderControllerV2;

public class OrderControllerConcreteProxy extends ProxyOrderControllerV2 {

    ProxyOrderControllerV2 target;
    LogTrace trace;

    public OrderControllerConcreteProxy(ProxyOrderControllerV2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try{
            status = trace.begin("OrderControllerConcreteProxy.request");
            String result = target.request(itemId);
            trace.end(status);
            return result;
        } catch(Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
        return target.noLog();
    }

}
