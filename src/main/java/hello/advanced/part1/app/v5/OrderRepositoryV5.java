package hello.advanced.part1.app.v5;

import hello.advanced.part1.trace.callback.TraceTemplate;
import hello.advanced.part1.trace.logtrace.LogTrace;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate traceTemplate;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    public void save(String itemId) {
        this.traceTemplate.execute("OrderRepositoryV4.save()", ()->{
            if("ex".equals(itemId)) {
                throw new IllegalStateException("예외 발생!!");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

}
