package hello.advanced.part2.proxy.config.v1_proxy;

import hello.advanced.part1.trace.logtrace.LogTrace;
import hello.advanced.part2.proxy.app.v2.ProxyOrderControllerV2;
import hello.advanced.part2.proxy.app.v2.ProxyOrderRepositoryV2;
import hello.advanced.part2.proxy.app.v2.ProxyOrderServiceV2;
import hello.advanced.part2.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.advanced.part2.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.advanced.part2.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {


    @Bean
    public ProxyOrderControllerV2 proxyOrderControllerV2(LogTrace logTrace) {
        ProxyOrderControllerV2 proxyOrderControllerV2 = new ProxyOrderControllerV2(proxyOrderServiceV2(logTrace));
        return new OrderControllerConcreteProxy(proxyOrderControllerV2, logTrace);
    }

    @Bean
    public ProxyOrderServiceV2 proxyOrderServiceV2(LogTrace logTrace) {
        ProxyOrderServiceV2 proxyOrderServiceV2 = new ProxyOrderServiceV2(proxyOrderRepositoryV2(logTrace));
        return new OrderServiceConcreteProxy(proxyOrderServiceV2, logTrace);
    }

    @Bean
    public ProxyOrderRepositoryV2 proxyOrderRepositoryV2(LogTrace logTrace) {
        ProxyOrderRepositoryV2 proxyOrderRepositoryV2 = new ProxyOrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(proxyOrderRepositoryV2, logTrace);
    }

}
