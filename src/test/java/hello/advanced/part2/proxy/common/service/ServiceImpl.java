package hello.advanced.part2.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceImpl implements ServiceInterface {

    @Override
    public void save() {
        log.info("CALL save");
    }

    @Override
    public void find() {
        log.info("CALL find");
    }
}
