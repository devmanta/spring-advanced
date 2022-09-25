package hello.advanced.trace.strategy;


import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void testV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long start = System.currentTimeMillis();
        log.info("logic1 서비스 호출");
        long end = System.currentTimeMillis();
        log.info("실행시간={}", (end - start));
    }

    private void logic2() {
        long start = System.currentTimeMillis();
        log.info("logic2 서비스 호출");
        long end = System.currentTimeMillis();
        log.info("실행시간={}", (end - start));
    }

    @Test
    void strategyV1() {
        StrategyLogic1 logic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(logic1);
        context1.execute();

        StrategyLogic2 logic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(logic2);
        context2.execute();
    }



}
