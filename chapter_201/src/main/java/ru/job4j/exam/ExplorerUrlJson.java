package ru.job4j.exam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ExplorerUrlJson extends Explorer implements Callable<List<Map<String, String>>>{

    private String source;
    private Converter converter;

    public ExplorerUrlJson(String source, Converter converter) {
        this.source = source;
        this.converter = converter;
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
        return converter.formatToMap(rsl.toString());
    }

    @Override
    public Explorer getInstance(String source) {
        return new ExplorerUrlJson(source, converter);
    }

    public static void main(String[] args) {
        Explorer explorer = new ExplorerUrlJson("http://www.mocky.io/v2/5c51b9dd3400003252129fb5", new JsonConverter());
        explorer.call();
    }

}
