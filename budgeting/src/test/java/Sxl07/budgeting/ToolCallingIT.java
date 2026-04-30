package Sxl07.budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class ToolCallingIT {
    @Autowired
    OpenAiChatModel openAiChatModel;

    static class MathTools {
        @Tool(description = "Soma dois numeros inteiros, a e b")
        public int sum(int a, int b) {
            return a + b;
        }

        @Tool(description = "Subtrai dois numeros inteiros, a e b")
        public int subtract(int a, int b) {
            return a - b;
        }
    }
    

    @Test
    void should_executeSum_when_prompted() {
        var chatClient = ChatClient.builder(openAiChatModel).defaultSystem("Voce é um matematico").defaultTools(new MathTools()).build();

        var response = chatClient.prompt("some 10 mais 20. depois subtraia 30 do resultado anterior. exiba o resultado").call().content();


        assertThat(response).contains("0");
        System.out.println(response);
        }

}
