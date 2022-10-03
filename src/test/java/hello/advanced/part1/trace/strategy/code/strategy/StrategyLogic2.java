package hello.advanced.part1.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrategyLogic2 implements  Strategy{

    @Override
    public void call() {
        log.info("StrategyLogic2 서비스 로직 2 실행");
    }
}
