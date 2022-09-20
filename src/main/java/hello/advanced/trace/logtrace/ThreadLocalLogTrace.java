package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalLogTrace implements LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();

    private void syncTraceId() {
        TraceId traceId = traceIdHolder.get();
        if(traceId == null) {
            traceIdHolder.set(new TraceId());
        } else {
            traceIdHolder.set(traceId.createNextId());
        }
    }

    @Override
    public TraceStatus begin(String msg) {
        this.syncTraceId();
        TraceId traceId = traceIdHolder.get();
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), msg);
        return new TraceStatus(traceId, startTimeMs, msg);
    }

    @Override
    public void end(TraceStatus status) {
        this.complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        this.complete(status, e);
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < level; i++) {
            if(i == level -1) {
                sb.append("|").append(prefix);
            } else {
                sb.append("|    ");
            }
        }

        return sb.toString();
    }

    private void complete(TraceStatus status, Exception e) {
        long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();

        if(e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString());
        }
        this.releaseTranceId();
    }

    private void releaseTranceId() {
        TraceId traceId = traceIdHolder.get();
        if(traceId.isFirstLevel()) {
            //threadLocal을 다 사용하면 꼭!! remove를 해줘야한다! -> threadLocal에 값을 다 썼기 때문에 저장되어 있는 값을 destroy!!
            traceIdHolder.remove(); // remove하면 해당 thread에 저장한 데이터는 다 없어진다!
        } else {
            traceIdHolder.set(traceId.createPreviousId());
        }
    }

}
