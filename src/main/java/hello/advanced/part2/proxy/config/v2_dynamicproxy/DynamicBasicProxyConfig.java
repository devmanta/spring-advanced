package hello.advanced.part2.proxy.config.v2_dynamicproxy;

import hello.advanced.part1.trace.logtrace.LogTrace;
import hello.advanced.part2.proxy.app.v1.ProxyOrderControllerV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderControllerV1Impl;
import hello.advanced.part2.proxy.app.v1.ProxyOrderRepositoryV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderRepositoryV1Impl;
import hello.advanced.part2.proxy.app.v1.ProxyOrderServiceV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderServiceV1Impl;
import hello.advanced.part2.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import java.lang.reflect.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicBasicProxyConfig {

    @Bean
    public ProxyOrderControllerV1 proxyOrderControllerV1(LogTrace logTrace) {
        ProxyOrderControllerV1 proxyOrderControllerV1 = new ProxyOrderControllerV1Impl(proxyOrderServiceV1(logTrace));

        ProxyOrderControllerV1 proxy = (ProxyOrderControllerV1) Proxy.newProxyInstance(proxyOrderControllerV1.getClass().getClassLoader(),
            new Class[]{ProxyOrderControllerV1.class},
            new LogTraceBasicHandler(proxyOrderControllerV1, logTrace)); // LogTraceBasicHandler 이거는 빈등록 안하고 각각 인스턴스를 만들었다. 왜냐면 각각 target이 다르기 때문에 LogTraceBasicHandler에 있는 전역변수가 달라져서

        return proxy;
    }

    @Bean
    public ProxyOrderServiceV1 proxyOrderServiceV1(LogTrace logTrace) {
        ProxyOrderServiceV1 proxyOrderServiceV1 = new ProxyOrderServiceV1Impl(proxyOrderRepositoryV1(logTrace));

        ProxyOrderServiceV1 proxy = (ProxyOrderServiceV1) Proxy.newProxyInstance(proxyOrderServiceV1.getClass().getClassLoader(),
            new Class[]{ProxyOrderServiceV1.class},
            new LogTraceBasicHandler(proxyOrderServiceV1, logTrace));

        return proxy;
    }

    @Bean
    public ProxyOrderRepositoryV1 proxyOrderRepositoryV1(LogTrace logTrace) {
        ProxyOrderRepositoryV1 proxyOrderRepositoryV1 = new ProxyOrderRepositoryV1Impl();

        ProxyOrderRepositoryV1 proxy = (ProxyOrderRepositoryV1) Proxy.newProxyInstance(proxyOrderRepositoryV1.getClass().getClassLoader(),
            new Class[]{ProxyOrderRepositoryV1.class},
            new LogTraceBasicHandler(proxyOrderRepositoryV1, logTrace));

        return proxy;
    }

}
