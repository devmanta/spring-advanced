package hello.advanced.part1.trace.logtrace;

import hello.advanced.part1.trace.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String msg);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);

}
