package ru.job4j.inheritance;

public class HtmlReport extends TextReport {
    public String generate(String name, String body) {
        return "<hl>" + name + "</hl>" + "<br/><span>" +
                body + "</span";
    }
}
