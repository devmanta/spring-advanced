package hello.advanced.part1.trace.threadlocal;

import hello.advanced.part1.trace.threadlocal.code.FieldService;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void fieldNoProblem() {
        //이건 동시성 문제 발생하지 않는다.
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = new Runnable() {
            @Override
            public void run() {
                fieldService.logic("userB");
            }
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        this.sleep(2000); //동시성 문제 발생 X

        threadB.start();
        this.sleep(3000); //메인 스레드 종료 대기

        log.info("main finish");
    }

    @Test
    void fieldNoProblemWithCountDownLatch() throws InterruptedException {
        //CountDownLatch를 활용해서 Thread 종료 대기 하기
        log.info("main start");

        CountDownLatch latch = new CountDownLatch(2);

        Runnable userA = () -> {
            fieldService.logic("userA");
            latch.countDown();
        };

        Runnable userB = new Runnable() {
            @Override
            public void run() {
                fieldService.logic("userB");
                latch.countDown();
            }
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        this.sleep(2000); //동시성 문제 발생 X
        threadB.start();

        latch.await();
        log.info("main finish");
    }

    @Test
    void fieldWithSyncProblem() {
        //이건 동시성 문제 발생
        log.info("main start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = new Runnable() {
            @Override
            public void run() {
                fieldService.logic("userB");
            }
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        this.sleep(2000); //동시성 문제 발생 X
        this.sleep(100); //동시성 문제 발생

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
