package hello.advanced.part2.proxy.app.v1;

public class ProxyOrderRepositoryV1Impl implements ProxyOrderRepositoryV1 {

    @Override
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
