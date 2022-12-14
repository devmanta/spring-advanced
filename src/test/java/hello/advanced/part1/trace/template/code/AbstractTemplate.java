package hello.advanced.part1.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute() {
        long start = System.currentTimeMillis();
        call();
        long end = System.currentTimeMillis();
        log.info("실행시간={}", (end - start));
    }

    protected abstract void call();


}
