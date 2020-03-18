package ru.job4j.gamnit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entry {
    int field;

    public Entry(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public Entry() {}

    @XmlElement
    public void setField(int field) {
        this.field = field;
    }
}