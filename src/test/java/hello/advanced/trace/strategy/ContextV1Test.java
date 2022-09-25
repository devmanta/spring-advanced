package hello.advanced.trace.strategy;


import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
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

    @Test
    void strategyV2() {
        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
                log.info("전략 서비스 로직 1 호출");
            }
        };
        log.info("strategy1={}", strategy1.getClass());
        ContextV1 context1 = new ContextV1(strategy1);
        context1.execute();

        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("전략 서비스 로직 2 호출");
            }
        };
        log.info("strategy2={}", strategy2.getClass());
        ContextV1 context2 = new ContextV1(strategy2);
        context2.execute();
    }

    @Test
    void strategyV3() {
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("전략 서비스 로직 1 호출");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("전략 서비스 로직 2 호출");
            }
        });
        context2.execute();
    }

    @Test
    void strategyV4() {
        ContextV1 context1 = new ContextV1(() -> log.info("전략 서비스 로직 1 호출"));
        context1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("전략 서비스 로직 2 호출"));
        context2.execute();
    }



}
