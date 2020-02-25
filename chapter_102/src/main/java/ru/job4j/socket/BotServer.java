package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class BotServer {

    private Socket socket;
    private HashMap<String, String> answers;

    public BotServer(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) {
        int port = -1;
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
                try (final Socket socket = new ServerSocket(port).accept()) {
                    BotServer server = new BotServer(socket);
                } catch (NumberFormatException | IOException nfe) {
                    System.out.println("Usage is: botserver.jar #port");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (port == -1) {
            System.out.println("Usage is: botserver.jar #port");
        }
    }

    public void botStart() throws IOException {
        answersBase();
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String ask;
        do {
            System.out.println("Wait command...");
            ask = in.readLine();
            System.out.println(ask);
            if (ask != null) {
                out.println(answer(ask).replaceAll("@@", System.lineSeparator()));
                out.println();
            }
        } while (!(ask != null && "exit".equals(ask.toLowerCase())));
        this.socket.close();
    }

    private String answer(String ask) {
        ask = ask.toLowerCase();
        String answer = null;
        switch (ask) {
            case "hello":
                answer = "Hello, dear friend. I'm an oracle.";
                break;
            case "exit":
                answer = "See you later!";
                break;
            case "":
                answer = "Do you really what to know anything?";
                break;
            default:
                for (String key : this.answers.keySet()) {
                    if (ask.contains(key)) {
                        answer = this.answers.get(key);
                        break;
                    }
                }
                break;
        }
        if (answer == null) {
            answer = "I hope you'll find the answer by your self...";
        }
        return answer;
    }

    private void answersBase() {
        this.answers = new HashMap<>();
        try (BufferedReader file = new BufferedReader(new FileReader(System.getProperty("user.dir")
                + "/src/main/java/ru/job4j/socket/answers.txt"))) {
            file.lines().forEach(line -> {
                String[] pair = line.split("::");
                this.answers.put(pair[0], pair[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}