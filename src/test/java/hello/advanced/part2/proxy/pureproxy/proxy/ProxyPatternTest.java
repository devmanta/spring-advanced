package hello.advanced.part2.proxy.pureproxy.proxy;

import hello.advanced.part2.proxy.pureproxy.proxy.code.ProxyPatternSubject;
import hello.advanced.part2.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternSubject proxyPatternSubject = new ProxyPatternSubject(realSubject);
        proxyPatternSubject.execute();
        proxyPatternSubject.execute();
        proxyPatternSubject.execute();
    }

}
