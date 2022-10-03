package hello.advanced.part2.proxy.app.v1;

import org.springframework.beans.factory.annotation.Autowired;

public class ProxyOrderControllerV1Impl implements ProxyOrderControllerV1 {

    private final ProxyOrderServiceV1 orderService;

    @Autowired //생성자 1개 일 때는 생략 가능
    public ProxyOrderControllerV1Impl(ProxyOrderServiceV1 orderService) {
        this.orderService = orderService;
    }

    @Override
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @Override
    public String noLog() {
        return "ok";
    }
}
