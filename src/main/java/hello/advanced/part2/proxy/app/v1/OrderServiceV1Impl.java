package hello.advanced.part2.proxy.app.v1;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceV1Impl implements OrderServiceV1{

    private final OrderRepositoryV1 orderRepository;

    @Autowired // 생략 가능
    public OrderServiceV1Impl(OrderRepositoryV1 orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
