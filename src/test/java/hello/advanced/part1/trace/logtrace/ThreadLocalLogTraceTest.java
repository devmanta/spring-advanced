package hello.advanced.part1.trace.logtrace;

import hello.advanced.part1.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLocalLogTraceTest {

    ThreadLocalLogTrace logTrace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level2() {
        TraceStatus status1 = logTrace.begin("hello1");
        TraceStatus status2 = logTrace.begin("hello2");
        logTrace.end(status2);
        logTrace.end(status1);
    }

    @Test
    void begin_endException_level2() {
        TraceStatus status1 = logTrace.begin("hello1");
        TraceStatus status2 = logTrace.begin("hello2");
        logTrace.exception(status2, new IllegalStateException());
        logTrace.exception(status1, new IllegalStateException());
    }
}