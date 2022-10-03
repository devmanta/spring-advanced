package hello.advanced.part1.app.v5;

import hello.advanced.part1.trace.callback.TraceCallback;
import hello.advanced.part1.trace.callback.TraceTemplate;
import hello.advanced.part1.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace) {
        this.orderService = orderService;
        this.template = new TraceTemplate(logTrace); //스프링 빈으로 등록해서 사용해도 됨. 근데 이거는 수백개 이렇게 new 해도 메모리에 크게 지장이 없음.
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return template.execute("OrderControllerV5.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        });
    }

}
