package hello.advanced.part2.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> classHello = Class.forName("hello.advanced.part2.proxy.jdkdynamic.ReflectionTest$Hello");// 내부 클래스는 $로 접근해야함!

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");
        Object invoke1 = methodCallA.invoke(target); // target 인스턴스에 있는 callA를 호출하는 것임
        log.info("invoke1={}", invoke1);

        Method methodCallB = classHello.getMethod("callB");
        Object invoke2 = methodCallB.invoke(target);
        log.info("invoke2={}", invoke2);
    }

    @Test
    void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //reflection은 근데 런타임에 동작되기 때문에 컴파일 시점에 에러를 잡을 수 없기 떄문에 주의해야한다.
        Class<?> classHello = Class.forName("hello.advanced.part2.proxy.jdkdynamic.ReflectionTest$Hello");// 내부 클래스는 $로 접근해야함!

        Hello target = new Hello();

        Method methodCallA = classHello.getMethod("callA");
        this.dynamicCall(methodCallA, target);

        Method methodCallB = classHello.getMethod("callB");
        this.dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
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
