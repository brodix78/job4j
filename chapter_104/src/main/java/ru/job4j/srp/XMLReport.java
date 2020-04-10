package ru.job4j.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class XMLReport implements Generator{

    @XmlRootElement
    public static class Employees {
        List<Employee> employee;

        public Employees(List<Employee> emp) {
            this.employee = emp;
        }

        public Employees() {}

        public void setEmployee(List<Employee> employee) {
            this.employee = employee;
        }

        public List<Employee> getEmployee() {
            return employee;
        }
    }

    @Override
    public String report(List<Employee> employee) {
        String report = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Employees en = new Employees(employee);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(en, sw);
            report = sw.toString();
            System.out.println(report);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return report;
    }
}
