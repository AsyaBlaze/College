package job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader (new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String message = input.readLine().split("msg=|\\s")[2];
                    if (message.equals("Bye") || message.equals("Exit")) {
                        output.write("Bye".getBytes());
                        server.close();
                    } else if (message.equals("Hello")) {
                        output.write("Hello, world!".getBytes());
                    } else {
                        message = lineAddSpacesBeforeUpperLetters(message);
                        output.write(message.getBytes());
                    }
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {

                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        } catch (IOException e) {
            UsageLog4j.getLogger().error("Exception while using Socket", e);
        }
    }

    private static String lineAddSpacesBeforeUpperLetters(String line) {
        StringBuilder builder = new StringBuilder();
        char[] chars = line.toCharArray();
        builder.append(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                builder.append(" ");
            }
            builder.append(chars[i]);
        }
        return builder.toString();
    }
}
