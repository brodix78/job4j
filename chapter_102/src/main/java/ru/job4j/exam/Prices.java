package ru.job4j.exam;

import java.util.*;

public class Prices {

    public class Price {
        long id;
        String product_code;
        int number;
        int depart;
        Date begin;
        Date end;
        long value;
    }

    private class priceLabel {
        boolean current;
        boolean periodStart;
        Date date;
        long value;
    }

    public List<Price> prices(List<Price> currentPrices, List<Price> newPrices) {
        List<Price> rsl = new ArrayList<>();
        HashMap<String, PriorityQueue<priceLabel>> sortLine = new HashMap<>();
        for (Price price:currentPrices) {
        }
        for (Price price:newPrices) {
        }
        for(String key:sortLine.keySet()){
        }
        /*Загоняю входящие диапазоны цен в карту, где String ключ имеет вид 122-1-1 (Product_code-Number-Depart)
        / В качестве значения PriorityQueue<priceLabel> с сортировкой по date c (начало загоняется c periodStart = true,
        / конец c false), для обозначения currentPrices поле current = true, для новых цен false.
        / Затем перебираю всю sortLine и все PriorityQueue обрабатываю как очередь формируя новые объекты Price, сразу помещая их в
        / rsl.
        */
        return rsl;
    }


}
