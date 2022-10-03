package hello.advanced.part2.proxy.app.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping//수동 등록 할거라서 이거 쓰고, @Controller는 자동스캔 대상이 되서
@ResponseBody
public class ProxyOrderControllerV2 {

    private final ProxyOrderServiceV2 orderService;

    public ProxyOrderControllerV2(ProxyOrderServiceV2 orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/v2/proxy/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v2/proxy/no-log")
    public String noLog() {
        return "ok";
    }

}
