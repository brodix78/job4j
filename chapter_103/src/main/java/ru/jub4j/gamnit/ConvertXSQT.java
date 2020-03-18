package ru.jub4j.gamnit;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class ConvertXSQT {

    String ln = System.lineSeparator();

    public void convert(File input, File output) {
        try (FileInputStream in = new FileInputStream(input);
             FileOutputStream out = new FileOutputStream(output)) {
            String xsl =
                    "<?xml version=\"1.0\"?>\n" +
                    "   <xsl:stylesheet version=\"1.0\"\n" +
                    "       xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" +
                    "       <xsl:output method=\"xml\" indent=\"yes\"/>" +
                    "           <xsl:template match=\"/\">\n" +
                    "               <entries>" +
                    "                   <xsl:for-each select=\"entries/entry\">\n" +
                    "                       <entry> <xsl:attribute name=\"field\"> <xsl:value-of select=\"field\"/> </xsl:attribute> </entry>\n" +
                    "               </xsl:for-each>\n" +
                    "           </entries>\n " +
                    "       </xsl:template>\n" +
                    "   </xsl:stylesheet>\n";
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(
                    new ByteArrayInputStream(xsl.getBytes()))
            );
            transformer.transform(new StreamSource(in), new StreamResult(output));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
