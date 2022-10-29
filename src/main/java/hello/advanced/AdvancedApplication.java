package hello.advanced;

import hello.advanced.part1.trace.logtrace.LogTrace;
import hello.advanced.part1.trace.logtrace.ThreadLocalLogTrace;
import hello.advanced.part2.proxy.config.AppV1Config;
import hello.advanced.part2.proxy.config.AppV2Config;
import hello.advanced.part2.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello.advanced.part2.proxy.config.v1_proxy.InterfaceProxyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@SpringBootApplication
//@Import(AppV1Config.class) //나중에 v2, v3 로 바꿀 때, v2, v3만 적용하고 싶어서
//@Import({AppV1Config.class, AppV2Config.class})
//@Import({InterfaceProxyConfig.class})
@Import({ConcreteProxyConfig.class})
@SpringBootApplication(scanBasePackages = "hello.advanced.part2.proxy.app") //이렇게 명시하면 이 하위 패키지만 componentScan대상이 된다.
public class AdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

}
