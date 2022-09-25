package hello.advanced.trace.template;


import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodTestV0() {
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

    //추상클래스로 빼내기 (템플릿 메서드 사용)>>
    //템플릿 메서드 패턴은 다형성을 사용해서 변하지 않는 부분과 변하는 부분을 분리하는 방법이다!
    @Test
    void templateMethodTestV1() {
        AbstractTemplate t1 = new SubClassLogic1();
        t1.execute();

        AbstractTemplate t2 = new SubClassLogic2();
        t2.execute();
    }

    //익명클래스 사용
    @Test
    void templateMethodTestV2() {
        AbstractTemplate t1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("서비스 로직1");
            }
        };
        t1.execute();

        AbstractTemplate t2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("서비스 로직2");
            }
        };
        t2.execute();
    }

}
