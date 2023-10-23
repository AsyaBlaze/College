package subjects.testing;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class CheckPoint3 {
    public static void main(String[] args) throws IOException, InterruptedException {


        //POST-запрос питомца
        String url = "/pet/12";
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(new Pet(12, "Jessy"));
        makePostRequest(url, json);

        // GET-запрос для нахождения питомца с id 1
        url = "/pet/12";
        java.net.http.HttpResponse<String> response1 = makeGetRequest(url);
        System.out.println(response1.body());
    }

    private static java.net.http.HttpResponse<String> makeGetRequest(String url) throws IOException, InterruptedException {
        String site = "https://petstore.swagger.io/v2";
        java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(site + url))
                .GET()
                .build();
        return client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
    }

    private static void makePostRequest(String url, String body) throws IOException, InterruptedException {
        URL uri = new URL("https://petstore.swagger.io/v2" + url);
        HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
        connection.setRequestMethod("POST");
//        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes("UTF-8");
            os.write(input,0, input.length);
        }
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
    }
}

class Category {
    private int id = 0;
    private String name = "sting";

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Tag {
    private int id = 0;
    private String name = "sting";

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

enum Status {
    available,
    pending,
    sold
}

class Pet {
    private int id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private Status status;

    public Pet(int id, String name) {
        this.id = id;
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}