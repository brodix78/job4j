package ru.job4j.isp;

import java.util.LinkedList;

public class Item {
    private Item parent;
    private String content;
    private String action;
    private LinkedList<Item> kids;

    public Item(Item parent, String content, String action) {
        this.parent = parent;
        this.content = content;
        this.action = action;
    }

    public Item(String content) {
        this.content = content;
    }

    public Item getParent() {
        return parent;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LinkedList<Item> getKids() {
        return kids;
    }

    public Item addKid(String content, String action) {
        Item kid = new Item(this, content, action);
        kids.add(kid);
        return kid;
    }

    public boolean removeKid(Item kid) {
        return kids.remove(kid);
    }
}