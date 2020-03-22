package ru.job4j.exam;


import java.util.*;

public class Pricesif {
/*
    HashMap<String, PriorityQueue<PriceLabel>> sortLine = new HashMap<>();


    public List<PriceIfEdition> prices(List<PriceIfEdition> currentPrices, List<PriceIfEdition> newPrices) {
        List<PriceIfEdition> rsl = new ArrayList<>();
        makeSortLine(currentPrices, true);
        makeSortLine(newPrices, false);
        for(String key:sortLine.keySet()) {
            boolean upLayer = false;
            long val = 0;
            PriceLabel pl;
            PriceIfEdition price = new PriceIfEdition(key, sortLine.get(key).poll());
            while ((pl = sortLine.get(key).poll()) != null) {
                if (pl.periodStart) {
                    if (!upLayer) {
                        if (val != 0 && pl.date.getTime() - price.end.getTime() > 1000L) {
                            if (val != price.value) {
                                if ((price.end.getTime() - price.begin.getTime()) > 1000L) {
                                    rsl.add(price);
                                }
                                price.value = val;
                                price.begin = new Date(price.end.getTime() + 1000L);
                            }
                            price.end = new Date(pl.date.getTime() - 1000L);
                        }
                        if (pl.value != price.value) {
                            if ((price.end.getTime() - price.begin.getTime()) > 1000L) {
                                rsl.add(price);
                            }
                            price = new PriceIfEdition(key, pl);
                        }
                    }
                    if (!pl.current) {
                        upLayer = true;
                    } else {
                        val = pl.value;
                    }
                }
                if (!pl.periodStart) {
                    if (upLayer) {
                        if (!pl.current) {
                            upLayer = false;
                            price.end = pl.date;
                        }
                    } else {
                        if (price.value == pl.value) {
                            price.end = pl.date;
                        } else {
                            if (pl.date.getTime() - price.end.getTime() > 1000L) {
                                rsl.add(price);
                                price.value = pl.value;
                                price.begin = new Date(price.end.getTime() + 1000L);
                                price.end = pl.date;
                                rsl.add(price);
                            }
                        }
                    }
                    val = 0;
                }
            }
        }
        return rsl;
    }
        /*Загоняю входящие диапазоны цен в карту, где String ключ имеет вид 122-1-1 (Product_code-Number-Depart)
        / В качестве значения PriorityQueue<priceLabel> с сортировкой по date c (начало загоняется c periodStart = true,
        / конец c false), для обозначения currentPrices поле current = true, для новых цен false.
        / Затем перебираю всю sortLine и все PriorityQueue обрабатываю как очередь формируя новые объекты Price, сразу помещая их в
        / rsl.
        */
/*
    private void makeSortLine(List<PriceIfEdition> prices, boolean current) {
        for (PriceIfEdition price:prices) {
            String key = String.format("%s-%s-%s", price.product_code, price.number, price.depart);
            if (!sortLine.containsKey(key)) {
                sortLine.put(key, new PriorityQueue<>((o1, o2)
                        -> (int) ((o2.date.getTime() - o1.date.getTime()))
                        + (!o2.current ? 1 : 0)
                        - ((!o1.current ? 1 : 0))));
            }
            sortLine.get(key).add(new PriceLabel(price, current, true));
            sortLine.get(key).add(new PriceLabel(price, current, false));
        }
    }

    private void makeLine(List<PriceIfEdition> prices, boolean current) {
        for (PriceIfEdition price:prices) {
            String key = String.format("%s-%s-%s", price.product_code, price.number, price.depart);
            if (!sortLine.containsKey(key)) {
                sortLine.put(key, new PriorityQueue<>((o1, o2)
                        -> (int) ((o2.date.getTime() - o1.date.getTime()))
                        + (!o2.current ? 1 : 0)
                        - ((!o1.current ? 1 : 0))));
            }
            sortLine.get(key).add(new PriceLabel(price, current, true));
            sortLine.get(key).add(new PriceLabel(price, current, false));
        }
    }

*/
}
