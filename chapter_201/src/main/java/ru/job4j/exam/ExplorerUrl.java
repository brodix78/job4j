package ru.job4j.exam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.transform.Source;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ExplorerUrl extends Explorer implements Callable<List<Map<String, String>>>{

    private String source;
    private Converter converter;

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
            System.out.println(converter.formatToMap(table.text()));
            rsl.append(table.text());
        }
        return converter.formatToMap(rsl.toString());
    }

    @Override
    public Explorer getInstance(String source) {
        return new ExplorerUrl(source, converter);
    }

    public static void main(String[] args) {
        Collector<Camera> collector = new Collector<>(new CameraFactory(), new JsonConverter<Camera>());
        collector.addData(new ExplorerUrl(new JsonConverter<Camera>()), List.of("http://www.mocky.io/v2/5c51b9dd3400003252129fb5"));
        System.out.println(collector.getDownloadedData(new JsonConverter<Camera>()));
    }

}
