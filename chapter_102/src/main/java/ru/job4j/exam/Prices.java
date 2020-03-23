package ru.job4j.exam;

import java.util.*;

public class Prices {

    private HashMap<String, PriorityQueue<Price>> merge = new HashMap<>();
    private ArrayList<Price> prices;


    public List<Price> prices(List<Price> currentPrices, List<Price> newPrices) {
        List<Price> rsl = new ArrayList<>();
        this.prices = new ArrayList<>(newPrices);
        makeMerge();
        this.prices = new ArrayList<>(currentPrices);
        makeMerge();
        for (String key:merge.keySet()) {
            ArrayList<Price> temp = new ArrayList<>();
            Price previous = merge.get(key).poll();
            Price price;
            while ((price = merge.get(key).poll()) != null) {
                if (price.begin.getTime() != 0) {
                    if (price.value == previous.value
                            && previous.end.getTime() == price.begin.getTime()) {
                        previous.end = price.end;
                    } else {
                        temp.add(previous);
                        previous = price;
                    }
                }
            }
            temp.add(previous);
            rsl.addAll(temp);
        }
        return rsl;
    }

    private void makeMerge() {
        int i = 0;
        while (i < prices.size()) {
            String key = String.format("%s-%s-%s", prices.get(i).product_code, prices.get(i).number, prices.get(i).depart);
            if (!merge.containsKey(key)) {
                merge.put(key, new PriorityQueue<>((p1, p2) -> {
                    if (p1.begin.getTime() >= p2.begin.getTime() && p1.end.getTime() <= p2.end.getTime()) {
                        p1.begin = new Date(0);
                        p1.end = new Date(0);
                    } else if (p1.begin.getTime() >= p2.begin.getTime() && p1.begin.getTime() <= p2.end.getTime()) {
                        p1.begin = p2.end;
                    } else if (p1.end.getTime() >= p2.begin.getTime() && p1.end.getTime() <= p2.end.getTime()) {
                        p1.end = p2.begin;
                    } else if (p1.begin.getTime() < p2.begin.getTime() && p1.end.getTime() > p2.end.getTime()) {
                        Price pr = new Price();
                        pr.begin = p2.end;
                        pr.end = p1.end;
                        pr.depart = p1.depart;
                        pr.number = p1.number;
                        pr.value = p1.value;
                        pr.product_code = p1.product_code;
                        prices.add(pr);
                        p1.end = p2.begin;
                    }
                    return (int) (p1.begin.getTime() - p2.begin.getTime());
                }));
            }
            merge.get(key).add(prices.get(i++));
        }
    }
}