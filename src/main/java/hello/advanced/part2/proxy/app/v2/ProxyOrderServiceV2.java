package hello.advanced.part2.proxy.app.v2;

public class ProxyOrderServiceV2 {

    private final ProxyOrderRepositoryV2 orderRepository;

    public ProxyOrderServiceV2(ProxyOrderRepositoryV2 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }

}
