package ru.job4j.exam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SqlRuScan implements SiteScan {

    private Date scanned;
    private Date scaning;
    private Date topicDate;
    private List<Vacancy> vacancies;
    protected final Logger Log = LoggerFactory.getLogger(SqlTable.class);

    public SqlRuScan() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cal.set(Calendar.DAY_OF_YEAR, 1);
        this.scanned = cal.getTime();
        this.scaning = scanned;
    }

    public List<Vacancy> getFromSite() {
        vacancies= new ArrayList<>();
        getFromPage("https://www.sql.ru/forum/job-offers");
        int index = 2;
        while (topicDate.getTime() > scanned.getTime()) {
            getFromPage(String.format("https://www.sql.ru/forum/job-offers/%s", index++));
        }
        scanned = scaning;
        return vacancies;
    }

    private void getFromPage(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        Elements tables = doc.select("table");
        for (Element table : tables) {
            if (table.cssSelector().contains("forumTable")) {
                Elements trs = table.select("tr");
                for (Element tr : trs) {
                    Elements links = tr.select("a[href]");
                    Elements tds = tr.select("td");
                    if (tds.hasText() && !tds.get(1).text().contains("Важно:")
                            && dateCheck(tds) && links.hasText()
                            && links.get(0).text().toLowerCase().contains("java")
                            && !links.get(0).text().toLowerCase().contains("javascript")
                            && !links.get(0).text().toLowerCase().contains("java script")) {
                        vacancies.add(new Vacancy(links.get(0).text(),
                                getText(links.attr("abs:href")),
                                links.attr("abs:href")));
                    }
                }
            }
        }
    }

    private boolean dateCheck(Elements td) {
        Date createDate = null;
        String[] months = {"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"};
        String textDate = td.get(td.size() - 1).text();
        for (int i = 0; i < 12; i++) {
            if (textDate.contains(months[i])) {
                textDate = textDate.replace(months[i], String.format("%02d", i + 1));
                break;
            }
        }
        if (textDate.contains("сегодня")) {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd MM yy");
            textDate = textDate.replace("сегодня", dateFormat.format(date));
        }
        try {
            createDate = new SimpleDateFormat("dd MM yy, hh:mm").parse(textDate);
            topicDate = createDate;
            if (scaning.getTime() < createDate.getTime()) {
                scaning = createDate;
            }
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        return createDate != null && createDate.getTime() > scanned.getTime();
    }

    private String getText(String url) {
        String rsl = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
        Elements tables = doc.select("table");
        for (Element table : tables) {
            if (table.cssSelector().contains("msgTable")) {
                Elements tds = table.select("td");
                rsl = tds.get(2).text();
                break;
            }
        }
        return rsl;
    }
}
