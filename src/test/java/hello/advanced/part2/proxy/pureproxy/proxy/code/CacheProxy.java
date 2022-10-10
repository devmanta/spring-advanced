package hello.advanced.part2.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 접근 제어 proxy 예제
 */
@Slf4j
public class CacheProxy implements Subject{

    private Subject target;
    String cacheValue;

    public CacheProxy(Subject subject) {
        this.target = subject;
    }

    @Override
    public String operation() {
        log.info("CALL CacheProxy.operation");

        // 만약에 cacheValue에 값이 있으면 실제 Subject.operation을 전혀 호출 하지 않는다! -> 접근 제어!
        if(cacheValue == null) {
            cacheValue = target.operation();
        }

        return cacheValue;
    }
}
