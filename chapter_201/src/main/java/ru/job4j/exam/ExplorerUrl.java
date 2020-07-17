package ru.job4j.exam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ExplorerUrl extends Explorer implements Callable<List<Map<String, String>>>{

    private String source;
    private final Converter converter;

    public ExplorerUrl(Converter converter) {
        this.converter = converter;
    }

    public ExplorerUrl(String source, Converter converter) {
        this.converter = converter;
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
        Elements body = doc.select("body");
        for (Element table : body) {
            rsl.append(table.text());
        }
        return converter.formatToMaps(rsl.toString());
    }

    @Override
    public Explorer getInstance(String source) {
        return new ExplorerUrl(source, converter);
    }

}
