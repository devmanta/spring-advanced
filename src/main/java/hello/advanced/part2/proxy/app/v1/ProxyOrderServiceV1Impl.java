package hello.advanced.part2.proxy.app.v1;

import org.springframework.beans.factory.annotation.Autowired;

public class ProxyOrderServiceV1Impl implements ProxyOrderServiceV1 {

    private final ProxyOrderRepositoryV1 orderRepository;

    @Autowired // 생략 가능
    public ProxyOrderServiceV1Impl(ProxyOrderRepositoryV1 orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
