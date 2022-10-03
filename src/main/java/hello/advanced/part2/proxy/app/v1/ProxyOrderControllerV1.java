package hello.advanced.part2.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping //@Controller, @RequestMapping 이 있으면 스프링에mvc에서 controller로 인식한다.
@ResponseBody
public interface ProxyOrderControllerV1 {

    @GetMapping("/v1/proxy/request")
    String request(@RequestParam("itemId") String itemId); //인터페이스에서는 @RequestParam("쿼리스트링 변수명") 을 넣어줘야한다. 컴파일할 때 뭐 인식 안 될 수고 있고 등등..

    @GetMapping("/v1/proxy/no-log")
    String noLog();

}
