package ru.job4j.gamnit;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConvertXSQT {

    String ln = System.lineSeparator();

    public void convert(File input, File output) {
        try (FileInputStream in = new FileInputStream(input)) {
            String xsl = readPattern("./diffrent/xsl.pat");
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(
                    new ByteArrayInputStream(xsl.getBytes()))
            );
            transformer.transform(new StreamSource(in), new StreamResult(output));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readPattern(String file) {
        String rsl = null;
        try {
            rsl = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
