package subjects.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CheckPoint3 {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("\nДобавляем пользователя...");
        String url = "https://petstore.swagger.io/v2/user";
        User user = new User(3, "kiko", "Janin", "Kirova", "kiko567@numa.com", "lipetsk1997");
        makePostOrPutRequest(url, new ObjectMapper().writeValueAsString(user), "POST");

        System.out.println("\nПроверяем добавление пользователя...");
        url = "https://petstore.swagger.io/v2/user/kiko";
        makeGetRequest(url);

        System.out.println("\nОбновляем пароль...");
        user.setPassword("KiUlOp02");
        makePostOrPutRequest(url, new ObjectMapper().writeValueAsString(user), "PUT");

        System.out.println("\nПроверяем обновленный пароль...");
        makeGetRequest(url);

        System.out.println("\nУдаляем пользователя...");
        makeDeleteRequest(url);

        System.out.println("\nПроверяем удалился ли пользователь...");
        makeGetRequest(url);

        System.out.println("\n\n----Работа с заказами----");
        System.out.println("\nДобавляем заказ...");
        url = "https://petstore.swagger.io/v2/store/order";
        Order order = new Order(5, 21, 1, "2023-11-05T14:25:18.747Z", Status.approved, false);
        makePostOrPutRequest(url, new ObjectMapper().writeValueAsString(order), "POST");

        System.out.println("\nПроверяем добавление заказа...");
        url = "https://petstore.swagger.io/v2/store/order/5";
        makeGetRequest(url);

        System.out.println("\nУдаляем заказ");
        makeDeleteRequest(url);

        System.out.println("\nПроверяем удалился ли заказ...");
        makeGetRequest(url);

        System.out.println("\nДелаем инвентаризацию...");
        url = "https://petstore.swagger.io/v2/store/inventory";
        makeGetRequest(url);
    }

    public static void makeDeleteRequest(String url) throws IOException {
        URL uri = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("accept", "application/json");
        connection.connect();
        System.out.println("Delete Response Code for " + url + ": " + connection.getResponseCode());
    }

    public static HttpResponse<String> makeGetRequest(String url) throws IOException, InterruptedException {
        String site = url;
        java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(site))
                .GET()
                .build();

        return client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
    }

    public static void makePostOrPutRequest(String url, String body, String requestType) throws IOException, InterruptedException {
        URL uri = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
        connection.setRequestMethod(requestType);
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes("UTF-8");
            os.write(input,0, input.length);
        }
        System.out.println("Response Code for " + requestType +"-request " + url + ": " + connection.getResponseCode());
    }

}

enum Status {
    placed,
    approved,
    delivered
}

class Order {
    private long id;
    private long petId;
    private int quantity;
    private String shipDate;
    private Status status;
    private boolean complete;

    public Order() {
    }

    public Order(long id, long petId, int quantity, String shipDate, Status status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

class User {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public User() {
    }

    public User(long id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public User(long id, String username, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}