package ru.job4j.socket;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BotClientTest {

    String ln = System.lineSeparator();

    @Test
    public void whenExitThenExitWithoutLoop() throws IOException {
        Socket socket = mock(Socket.class);
        String outs;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                String.format("By-by%s%s", ln, ln).getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Scanner scanner = new Scanner(String.format("exit%s", ln));
        BotClient client = new BotClient(socket, scanner);
        client.clientStart();
    }
}
