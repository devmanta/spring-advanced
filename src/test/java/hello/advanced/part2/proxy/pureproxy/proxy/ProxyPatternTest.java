package hello.advanced.part2.proxy.pureproxy.proxy;

import hello.advanced.part2.proxy.pureproxy.proxy.code.CacheProxy;
import hello.advanced.part2.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.advanced.part2.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() {
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient proxyPatternClient = new ProxyPatternClient(cacheProxy);
        proxyPatternClient.execute();
        proxyPatternClient.execute();
        proxyPatternClient.execute();
    }

}
