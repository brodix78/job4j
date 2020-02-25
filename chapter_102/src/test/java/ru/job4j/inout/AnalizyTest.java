package ru.job4j.inout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    public ArrayList<String> outData(String[] inData) throws IOException {
        File logFile = folder.newFile("sys.log");
        File unvFile = folder.newFile("unavailable.csv");
        Analizy analizy = new Analizy();
        ArrayList<String> outData = new ArrayList<>();
        try (PrintWriter out = new PrintWriter(logFile);
             BufferedReader in = new BufferedReader(new FileReader(unvFile))) {
            for (String line:inData) {
                out.println(line);
            }
            analizy.unavailable(logFile.getPath(), unvFile.getPath());
            String line;
            while ((line = in.readLine()) != null) {
                outData.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outData;
    }

    @Test
    public void simplyTesting() throws IOException {
        List<String> rsl = outData(new String[] {"200 10:56:01", "500 10:57:01",
                "400 10:58:01", "200 10:59:01", "500 11:01:02", "200 11:02:02"});
        assertThat(rsl.get(0), is("10:57:01;10:59:01"));
        assertThat(rsl.get(1), is("11:01:02;11:02:02"));
        assertThat(rsl.size(), is(2));
    }
}

