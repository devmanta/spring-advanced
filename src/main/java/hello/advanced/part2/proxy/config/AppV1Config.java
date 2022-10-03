package hello.advanced.part2.proxy.config;

import hello.advanced.part2.proxy.app.v1.ProxyOrderControllerV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderControllerV1Impl;
import hello.advanced.part2.proxy.app.v1.ProxyOrderRepositoryV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderRepositoryV1Impl;
import hello.advanced.part2.proxy.app.v1.ProxyOrderServiceV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderServiceV1Impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV1Config {

    @Bean
    public ProxyOrderControllerV1 proxyOrderControllerV1() {
        return new ProxyOrderControllerV1Impl(proxyOrderServiceV1());
    }

    @Bean
    public ProxyOrderServiceV1 proxyOrderServiceV1() {
        return new ProxyOrderServiceV1Impl(proxyOrderRepositoryV1());
    }

    @Bean
    public ProxyOrderRepositoryV1 proxyOrderRepositoryV1() {
        return new ProxyOrderRepositoryV1Impl();
    }

}
