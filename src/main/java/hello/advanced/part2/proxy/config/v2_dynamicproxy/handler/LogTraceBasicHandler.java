package hello.advanced.part2.proxy.config.v2_dynamicproxy.handler;

import hello.advanced.part1.trace.TraceStatus;
import hello.advanced.part1.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogTraceBasicHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;

    public LogTraceBasicHandler(Object target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;
        try{
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName();
            status = logTrace.begin(message);
            Object result = method.invoke(target, args);
            logTrace.end(status);
            return result;
        } catch(Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

}
