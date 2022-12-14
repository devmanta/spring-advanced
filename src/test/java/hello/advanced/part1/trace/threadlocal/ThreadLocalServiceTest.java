package hello.advanced.part1.trace.threadlocal;

import hello.advanced.part1.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void doTest() {
        //이건 동시성 문제 발생
        log.info("main start");

        Runnable userA = () -> {
            service.logic("userA");
        };

        Runnable userB = new Runnable() {
            @Override
            public void run() {
                service.logic("userB");
            }
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();

        this.sleep(100); //동시성

        threadB.start();
        this.sleep(3000); //메인 스레드 종료 대기

        log.info("main finish");
    }


    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

}
