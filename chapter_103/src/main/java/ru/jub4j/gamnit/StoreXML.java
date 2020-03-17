package ru.jub4j.gamnit;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

public class StoreXML {
    File target;

    public StoreXML(File target) {
        this.target = target;
    }

    @XmlRootElement(name = "entries")
    public static class Entries {
        List<Entry> entry;

        public List<Entry> getEntry() {
            return entry;
        }

        public void setEntry(List<Entry> entry) {
            this.entry = entry;
        }
    }

    public void save (List<Entry> list) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Entries en = new Entries();
            en.setEntry(list);
            jaxbMarshaller.marshal(en, target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StoreSQL sql = new StoreSQL(new Config(), 100);
        StoreXML xml =  new StoreXML(new File("/home/ilya/da.xml"));
        xml.save(sql.load());
    }
}