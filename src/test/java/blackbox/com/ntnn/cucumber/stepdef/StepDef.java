package blackbox.com.ntnn.cucumber.stepdef;

import com.ntnn.cucumber.config.RabbitMqProperties;
import com.ntnn.cucumber.repository.model.CommonModel;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StepDef extends SpringIntegrationTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    private final static CountDownLatch waiter = new CountDownLatch(1);
    private String res;

    @Before
    public void init() {
    }

    @SneakyThrows
    @Given("^push any message to queue input \"([^\"]*)\"$")
    public void pushAnyMessageToQueueInput(String message) {
        String mess = readFile("files/" + message);

        rabbitTemplate.convertAndSend("queue.input", mess);

    }

    @SneakyThrows
    @When("^receive message from queue output$")
    public void receive_message_from_queue_output() {
        waiter.await(1000, TimeUnit.MILLISECONDS);
        res = (String) rabbitTemplate.receiveAndConvert("queue.output");
    }
    @Then("^validate message$")
    public void validate_message() {
        Assert.assertEquals("SGVsbG8=", res);
    }

    @SneakyThrows
    private String readFile(String fileName) {
        String ms = "";
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        FileReader fileReader = new FileReader(classPathResource.getFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        return bufferedReader.lines().map(line -> ms + line).collect(Collectors.joining());
    }


}
