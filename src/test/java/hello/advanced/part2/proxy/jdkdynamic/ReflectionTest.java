package hello.advanced.part2.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        log.info("start");
        String s1 = target.callA();
        log.info("s1={}", s1);
        log.info("end");

        log.info("start");
        String s2 = target.callB();
        log.info("s2={}", s2);
        log.info("end");
    }


    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
