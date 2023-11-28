package subjects.testing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckPoint4Test {
    @Test
    public void requestRandomTrivia() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://numbersapi.com/random/trivia?json";
        HttpResponse<String> answer = CheckPoint3.makeGetRequest(url);
        Map<String, String> answerJson = objectMapper.readValue(answer.body(), new TypeReference<>(){});
        int expectedStatusCode = 200;
        assertThat(answer.statusCode()).isEqualTo(expectedStatusCode);
        assertThat(answerJson.containsKey("text")).isTrue();
        assertThat(answerJson.containsKey("number")).isTrue();
        assertThat(answerJson.containsKey("found")).isTrue();
        assertThat(answerJson.containsKey("type")).isTrue();
        assertThat(answerJson.get("type").equals("trivia")).isTrue();
    }

    @Test
    public void requestRandomYear() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://numbersapi.com/random/year?json";
        HttpResponse<String> answer = CheckPoint3.makeGetRequest(url);
        Map<String, String> answerJson = objectMapper.readValue(answer.body(), new TypeReference<>(){});
        int expectedStatusCode = 200;
        assertThat(answer.statusCode()).isEqualTo(expectedStatusCode);
        assertThat(answerJson.containsKey("text")).isTrue();
        assertThat(answerJson.containsKey("number")).isTrue();
        assertThat(answerJson.containsKey("found")).isTrue();
        assertThat(answerJson.containsKey("type")).isTrue();
        assertThat(answerJson.get("type").equals("year")).isTrue();
    }

    @Test
    public void requestRandomDate() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://numbersapi.com/random/date?json";
        HttpResponse<String> answer = CheckPoint3.makeGetRequest(url);
        Map<String, String> answerJson = objectMapper.readValue(answer.body(), new TypeReference<>(){});
        int expectedStatusCode = 200;
        assertThat(answer.statusCode()).isEqualTo(expectedStatusCode);
        assertThat(answerJson.containsKey("text")).isTrue();
        assertThat(answerJson.containsKey("number")).isTrue();
        assertThat(answerJson.containsKey("found")).isTrue();
        assertThat(answerJson.containsKey("type")).isTrue();
        assertThat(answerJson.get("type").equals("date")).isTrue();
    }

    @Test
    public void requestRandomMath() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://numbersapi.com/random/math?json";
        HttpResponse<String> answer = CheckPoint3.makeGetRequest(url);
        Map<String, String> answerJson = objectMapper.readValue(answer.body(), new TypeReference<>(){});
        int expectedStatusCode = 200;
        assertThat(answer.statusCode()).isEqualTo(expectedStatusCode);
        assertThat(answerJson.containsKey("text")).isTrue();
        assertThat(answerJson.containsKey("number")).isTrue();
        assertThat(answerJson.containsKey("found")).isTrue();
        assertThat(answerJson.containsKey("type")).isTrue();
        assertThat(answerJson.get("type").equals("math")).isTrue();
    }

    @Test
    public void requestTrivia() throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        Random random = new Random();
        String url = "http://numbersapi.com/" + random.nextInt() +"?json";
        HttpResponse<String> answer = CheckPoint3.makeGetRequest(url);
        Map<String, String> answerJson = objectMapper.readValue(answer.body(), new TypeReference<>(){});
        int expectedStatusCode = 200;
        assertThat(answer.statusCode()).isEqualTo(expectedStatusCode);
        assertThat(answerJson.containsKey("text")).isTrue();
        assertThat(answerJson.containsKey("number")).isTrue();
        assertThat(answerJson.containsKey("found")).isTrue();
        assertThat(answerJson.containsKey("type")).isTrue();
        assertThat(answerJson.get("type").equals("trivia")).isTrue();
    }
}
