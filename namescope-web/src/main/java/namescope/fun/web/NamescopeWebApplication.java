package namescope.fun.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("namescope.fun")
public class NamescopeWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NamescopeWebApplication.class, args);
    }

}
