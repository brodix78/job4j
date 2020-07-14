package ru.job4j.exam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ExplorerUrlJson implements Callable<List<Map<String, String>>>{

    private String source;

    public ExplorerUrlJson(String source) {
        this.source = source;
    }

    @Override
    public List<Map<String, String>> call() {
        Document doc = null;
        StringBuilder rsl = new StringBuilder();
        try {
            doc = Jsoup.connect(source).ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements tables = doc.select("body");
        for (Element table : tables) {
            rsl.append(String.format("%s", table.text()));

        }
        return null;
    }
}
