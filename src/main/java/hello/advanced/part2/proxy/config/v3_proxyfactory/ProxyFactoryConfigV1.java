package hello.advanced.part2.proxy.config.v3_proxyfactory;

import hello.advanced.part1.trace.logtrace.LogTrace;
import hello.advanced.part2.proxy.app.v1.ProxyOrderControllerV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderControllerV1Impl;
import hello.advanced.part2.proxy.app.v1.ProxyOrderRepositoryV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderRepositoryV1Impl;
import hello.advanced.part2.proxy.app.v1.ProxyOrderServiceV1;
import hello.advanced.part2.proxy.app.v1.ProxyOrderServiceV1Impl;
import hello.advanced.part2.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV1 {

    @Bean
    public ProxyOrderControllerV1 proxyOrderControllerV1(LogTrace logTrace) {
        ProxyOrderControllerV1 orderController = new ProxyOrderControllerV1Impl(proxyOrderServiceV1(logTrace));

        ProxyFactory proxyFactory = new ProxyFactory(orderController);
        proxyFactory.addAdvisor(getAdvisors(logTrace));
        ProxyOrderControllerV1 proxy = (ProxyOrderControllerV1) proxyFactory.getProxy();

        log.info("ProxyOrderControllerV1 proxy={}, target={}", proxy.getClass(), orderController.getClass());

        return proxy;
    }

    @Bean
    public ProxyOrderServiceV1 proxyOrderServiceV1(LogTrace logTrace) {
        ProxyOrderServiceV1 orderService = new ProxyOrderServiceV1Impl(proxyOrderRepositoryV1(logTrace));

        ProxyFactory proxyFactory = new ProxyFactory(orderService);
        proxyFactory.addAdvisor(getAdvisors(logTrace));
        ProxyOrderServiceV1 proxy = (ProxyOrderServiceV1) proxyFactory.getProxy();

        log.info("ProxyOrderServiceV1 proxy={}, target={}", proxy.getClass(), orderService.getClass());
        return proxy;
    }

    @Bean
    public ProxyOrderRepositoryV1 proxyOrderRepositoryV1(LogTrace logTrace) {

        ProxyOrderRepositoryV1 orderRepository = new ProxyOrderRepositoryV1Impl();

        ProxyFactory proxyFactory = new ProxyFactory(orderRepository);
        proxyFactory.addAdvisor(getAdvisors(logTrace));
        ProxyOrderRepositoryV1 proxy = (ProxyOrderRepositoryV1) proxyFactory.getProxy();

        log.info("ProxyOrderRepositoryV1 proxy={}, target={}", proxy.getClass(), orderRepository.getClass());
        return proxy;
    }

    private Advisor getAdvisors(LogTrace logTrace) {
        // pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }
}
