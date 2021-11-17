package ddd.lab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author ge
 * @date 2021/11/15
 */
@SpringBootTest
class HelloWorldClientTest {
    @Autowired
    private HelloWorldClient helloWorldClient;

    @Test
    void should_greet_correctly() {
        String name = "frank.lsl";
        HelloReply helloReply = helloWorldClient.greet(name);

        assertNotNull(helloReply);
        assertEquals(helloReply.getMessage(), "Hello " + name);
    }
}