package hello.advanced.part2.proxy.pureproxy.concreteproxy;

import hello.advanced.part2.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.advanced.part2.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import hello.advanced.part2.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteLogicTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);

        client.execute();
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);

        client.execute();
    }
}
