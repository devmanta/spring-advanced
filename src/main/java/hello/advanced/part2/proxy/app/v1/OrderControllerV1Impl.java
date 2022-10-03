package hello.advanced.part2.proxy.app.v1;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderControllerV1Impl implements OrderControllerV1{

    private final OrderServiceV1 orderService;

    @Autowired //생성자 1개 일 때는 생략 가능
    public OrderControllerV1Impl(OrderServiceV1 orderService) {
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
