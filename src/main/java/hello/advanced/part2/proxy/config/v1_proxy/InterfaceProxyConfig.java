package hello.advanced.part2.proxy.config.v1_proxy;

import hello.advanced.part1.trace.logtrace.LogTrace;
import hello.advanced.part2.proxy.app.v1.ProxyOrderControllerV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderControllerV1Impl;
import hello.advanced.part2.proxy.app.v1.ProxyOrderRepositoryV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderRepositoryV1Impl;
import hello.advanced.part2.proxy.app.v1.ProxyOrderServiceV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderServiceV1Impl;
import hello.advanced.part2.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.advanced.part2.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.advanced.part2.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public ProxyOrderControllerV1 proxyOrderController(LogTrace logTrace) { //logTrace가 빨간 줄 날 수도 있음. 왜냐면 logTrace는 SpringBootApplication 메소드에서 bean 생성하기 때문에
        ProxyOrderControllerV1Impl controllerImpl = new ProxyOrderControllerV1Impl(proxyOrderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }

    @Bean
    public ProxyOrderServiceV1 proxyOrderService(LogTrace logTrace) {
        ProxyOrderServiceV1Impl serviceImpl = new ProxyOrderServiceV1Impl(proxyOrderRepository(logTrace));
        return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
    }

    @Bean
    public ProxyOrderRepositoryV1 proxyOrderRepository(LogTrace logTrace) {
        ProxyOrderRepositoryV1Impl repositoryImpl = new ProxyOrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
    }

}
