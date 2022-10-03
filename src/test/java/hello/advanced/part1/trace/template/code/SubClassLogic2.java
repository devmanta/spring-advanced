package hello.advanced.part1.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic2 extends AbstractTemplate{

    @Override
    protected void call() {
      log.info("서비스 로직2 실행");
    }
    
}
