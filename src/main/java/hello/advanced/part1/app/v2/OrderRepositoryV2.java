package hello.advanced.part1.app.v2;

import hello.advanced.part1.trace.TraceId;
import hello.advanced.part1.trace.TraceStatus;
import hello.advanced.part1.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

    private final HelloTraceV2 trace;

    public void save(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId, "OrderRepositoryV2.save()");
            if("ex".equals(itemId)) {
                throw new IllegalStateException("예외 발생!!");
            }
            this.sleep(1000);
            trace.end(status);
        } catch(Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

}
