package hello.advanced.part1.trace.strategy;

import hello.advanced.part1.trace.strategy.code.template.Callback;
import hello.advanced.part1.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {


    @Test
    void callbackTestV1() {
        TimeLogTemplate template = new TimeLogTemplate();
        //익명 함수
        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        });

        //람다
        template.execute(()-> log.info("비즈니스 로직 2 실행"));
    }

}
