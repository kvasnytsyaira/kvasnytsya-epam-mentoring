import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "kvasnytsya-epam-mentoring/rest")
//@ComponentScan({"event-service-api", "event-service-dto","event-service-impl","event-service-rest"})
@ComponentScan(basePackages = "com.org")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
