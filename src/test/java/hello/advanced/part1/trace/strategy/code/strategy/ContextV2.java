package hello.advanced.part1.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long start = System.currentTimeMillis();
        strategy.call(); //위임!!
        long end = System.currentTimeMillis();
        log.info("실행시간={}", (end - start));
    }
}
