package namescope.fun.engine.config;

import com.volcengine.ark.runtime.service.ArkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@Slf4j
public class ArkServiceConfig {

    @Value("${volcano.apiKey}")
    private String apiKey;

    @Value("${volcano.baseUrl}")
    private String baseUrl;

    @Bean
    public ArkService arkService() {

        log.info("[ArkService Config] apiKey: {}, baseUrl: {}", apiKey, baseUrl);
        return ArkService.builder()
                .apiKey(apiKey)
                .baseUrl(baseUrl)
                .timeout(Duration.ofSeconds(120))
                .connectTimeout(Duration.ofSeconds(30))
                .retryTimes(1)
                .build();
    }
}
