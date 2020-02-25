package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BotClient {

    private Socket socket;
    private Scanner console;

    public static void main(String[] args) {
        int port = -1;
        if (args.length == 2) {
            try {
                port = Integer.parseInt(args[1]);
                try (final Socket socket = new Socket(args[0], port)) {
                    Scanner console = new Scanner(System.in);
                    BotClient server = new BotClient(socket, console);
                } catch (NumberFormatException | IOException nfe) {
                    System.out.println("Usage is: botclient.jar hostName #port");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (port == -1) {
            System.out.println("Usage is: botclient.jar hostName #port");
        }
    }

    public BotClient(Socket socket, Scanner scanner) {
        this.socket = socket;
        this.console = scanner;
    }

    public void clientStart() throws IOException {
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        String str;
        String ask;
        do {
            ask = console.nextLine();
            out.println(ask);
            while (!(str = in.readLine()).isEmpty()) {
                System.out.println(str);
            }
        } while (ask != null && !"exit".equals(ask.toLowerCase()));
    }

}
