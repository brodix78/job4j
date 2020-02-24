package ru.job4j.socket;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BotServerTest {

    String ln = System.lineSeparator();

    @Test
    public void whenExitThenSeeYou() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                String.format("exit%s", ln).getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotServer server = new BotServer(socket);
        server.botStart();
        assertThat(out.toString(), is(
                String.format("See you later!%s%s", ln, ln
        )));
    }

    @Test
    public void whenTiredThenSeeYou() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                String.format("hello%sexit%S", ln, ln).getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotServer server = new BotServer(socket);
        server.botStart();
        assertThat(out.toString(), is(
                String.format("Hello, dear friend. I'm an oracle.%s%sSee you later!%s%s", ln, ln, ln, ln)
                ));
    }

    @Test
    public void whenTiredThenPhrase() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                String.format("I'm so tired%sexit%S", ln, ln).getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotServer server = new BotServer(socket);
        server.botStart();
        assertThat(out.toString(), is(
                String.format("Find the power inside your heart.%sBe strong, "
                        + "the life is too interesting to be tired."
                        + "%s%sSee you later!%s%s", ln, ln, ln, ln, ln)
        ));
    }

    @Test
    public void whenAskingElseThenIHope() throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                String.format("I'm so great%sexit%S", ln, ln).getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        BotServer server = new BotServer(socket);
        server.botStart();
        assertThat(out.toString(), is(
                String.format("I hope you'll find the answer by your self..."
                        + "%s%sSee you later!%s%s", ln, ln, ln, ln)
        ));
    }
}
