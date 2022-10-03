package hello.advanced.part1.trace.strategy;


import hello.advanced.part1.trace.strategy.code.strategy.ContextV2;
import hello.advanced.part1.trace.strategy.code.strategy.Strategy;
import hello.advanced.part1.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.part1.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }

    //익명 함수
    @Test
    void strategyV2() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("서비스 로직 1 호출");
            }
        });
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("서비스 로직 2 호출");
            }
        });
    }

    //익명 함수 - 람다
    @Test
    void strategyV3() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(() -> log.info("서비스 로직 1 호출"));
        contextV2.execute(() -> log.info("서비스 로직 2 호출"));
    }



}
