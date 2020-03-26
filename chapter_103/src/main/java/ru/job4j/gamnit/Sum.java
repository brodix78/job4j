package ru.job4j.gamnit;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Sum {

    long rsl = 0;

    DefaultHandler handler = new DefaultHandler() {
        public void startElement(String uri, String localName,String qName,
                                 Attributes attributes) {
            if (qName.equalsIgnoreCase("entry")) {
                rsl += Integer.parseInt(attributes.getValue("field"));
            }
        }
    };

    public long sumFields(String file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(file, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
