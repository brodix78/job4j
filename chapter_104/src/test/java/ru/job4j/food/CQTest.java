package ru.job4j.food;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CQTest {

    @Test
    public void whenAddFresh() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE, 30);
        Date exp = c.getTime();
        Meat pork = new Meat("Fresh pork", "Pork", now, exp, 300);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControllQuality cq= new ControllQuality(List.of(warehouse, shop, trash));
        HashMap<Food, Double> food = new HashMap<>();
        food.put(pork, (double) 100);
        cq.addToStorage(food);
        assertThat(warehouse.foodInStorage().containsKey(pork), is(true));
        assertThat(shop.foodInStorage().isEmpty(), is(true));
        assertThat(trash.foodInStorage().isEmpty(), is(true));
    }

    @Test
    public void whenAddToShopWithDiscount() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -5);
        Date cr = c.getTime();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
        Date exp = c.getTime();
        Bread bread = new Bread("French roll", "white", cr, exp, 40);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControllQuality cq= new ControllQuality(List.of(warehouse, shop, trash));
        HashMap<Food, Double> food = new HashMap<>();
        food.put(bread, (double) 100);
        cq.addToStorage(food);
        ArrayList<Food> inShop = new ArrayList<>(shop.foodInStorage().keySet());
        assertThat(inShop.contains(bread), is(true));
        assertThat(inShop.get(0).getDiscount(), is(true));
        assertThat(warehouse.foodInStorage().isEmpty(), is(true));
        assertThat(trash.foodInStorage().isEmpty(), is(true));
    }

    @Test
    public void whenAddExpired() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -5);
        Date cr = c.getTime();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date exp = c.getTime();
        Meat pork = new Meat("Fresh pork", "Pork", cr, exp, 300);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControllQuality cq= new ControllQuality(List.of(warehouse, shop, trash));
        HashMap<Food, Double> food = new HashMap<>();
        food.put(pork, (double) 100);
        cq.addToStorage(food);
        assertThat(trash.foodInStorage().containsKey(pork), is(true));
        assertThat(shop.foodInStorage().isEmpty(), is(true));
        assertThat(warehouse.foodInStorage().isEmpty(), is(true));
    }
}
