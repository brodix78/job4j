package ru.job4j.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ExplorerUrlJson extends Explorer implements Callable<List<Map<String, String>>>{

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
        System.out.println(rsl);
        return null;
    }

    @Override
    public Explorer getInstance(String source) {
        return new ExplorerUrlJson(source);
    }

    public HashMap<String, String> formatToMap(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(json, List.class;
        return new HashMap<String, String>(mapper.readValue(json, Map.class));
    }

    public String mapToFormat(HashMap<String, String> map) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(map);
    }


    public static void main(String[] args) {
        Explorer explorer = new ExplorerUrlJson("http://www.mocky.io/v2/5c51b9dd3400003252129fb5");
        explorer.call();
    }

}
