package hello.advanced.part2.proxy.app.v3;

import org.springframework.stereotype.Repository;

@Repository
public class ProxyOrderRepositoryV3 {

    public void save(String itemId) {
        if("ex".equals(itemId)) {
            throw new IllegalArgumentException("예외 발생!");
        }

        this.sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

}
