package hello.advanced.part2.proxy.config.v1_proxy.concrete_proxy;

import hello.advanced.part1.trace.TraceStatus;
import hello.advanced.part1.trace.logtrace.LogTrace;
import hello.advanced.part2.proxy.app.v2.ProxyOrderServiceV2;

public class OrderServiceConcreteProxy extends ProxyOrderServiceV2 {

    private final ProxyOrderServiceV2 target;
    private final LogTrace trace;

    public OrderServiceConcreteProxy(ProxyOrderServiceV2 target, LogTrace trace) {
        super(null); // 부모 것 사용하지 않을 거기 때문에 null로 준다. proxy용도로만 쓸 거기 때문에.. 자바 문법상 부모의 생성자를 강제로 호출해야함(부모가 필드생성자만 있음)
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try{
            status = trace.begin("OrderServiceConcreteProxy.orderItem");
            target.orderItem(itemId);
            trace.end(status);
        } catch(Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
