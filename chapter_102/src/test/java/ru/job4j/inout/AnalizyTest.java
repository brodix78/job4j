package ru.job4j.inout;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void simplyTesting() throws IOException {
        String log = "sys.log";
        String unv = "unavailable.csv";
        File logFile = folder.newFile(log);
        File unvFile = folder.newFile(unv);
        try (PrintWriter out = new PrintWriter(logFile)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(logFile.getAbsolutePath(), unvFile.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(unvFile))) {
            assertThat(in.readLine(), is("10:57:01;10:59:01"));
            assertThat(in.readLine(), is("11:01:02;11:02:02"));
            Assert.assertNull(in.readLine());
        } catch (Exception om) {
            om.printStackTrace();
        }
    }
}

