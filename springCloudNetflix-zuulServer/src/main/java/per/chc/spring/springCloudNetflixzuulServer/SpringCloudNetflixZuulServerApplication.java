package per.chc.spring.springCloudNetflixzuulServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;

@Controller
@EnableZuulProxy
@SpringBootApplication
public class SpringCloudNetflixZuulServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudNetflixZuulServerApplication.class, args);
	}
}
