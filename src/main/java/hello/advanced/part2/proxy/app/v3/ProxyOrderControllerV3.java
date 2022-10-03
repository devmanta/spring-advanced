package hello.advanced.part2.proxy.app.v3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProxyOrderControllerV3 {

    private final ProxyOrderServiceV3 orderService;

    public ProxyOrderControllerV3(ProxyOrderServiceV3 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v3/proxy/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v3/proxy/no-log")
    public String noLog() {
        return "ok";
    }

}
