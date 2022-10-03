package hello.advanced.part1.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback) {
        long start = System.currentTimeMillis();
        callback.call(); //위임!!
        long end = System.currentTimeMillis();
        log.info("실행시간={}", (end - start));
    }

}
