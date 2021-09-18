package blackbox.com.ntnn.cucumber.stepdef;

import com.ntnn.cucumber.config.RabbitMqProperties;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@CucumberContextConfiguration
@SpringBootTest(classes = BlackBoxTest.class)
@EnableConfigurationProperties({RabbitMqProperties.class})
@PropertySource("classpath:application-test.yml")
public class SpringIntegrationTest {
}
