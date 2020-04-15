package ru.job4j.isp;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu extends Item{

    private HashMap<String, Action> indexMenu;
    private String menuAll;
    private String delim;

    public Menu(String content, String delim) {
        super(content, null);
        this.delim = delim;
    }

    boolean add(String parentName, Item child) {
        boolean rsl = false;
        for (Item item : this.getKids()) {
            if (item.getContent().equals(parentName)) {
                item.addKid(child);
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    public String menu () {
        String rsl = null;
        if (indexMenu == null || menuAll == null) {
            refreshMenu();
        }
        return menuAll;
    }

    public Action actionSelect(String itemNum) {
        if (indexMenu == null || menuAll == null) {
            refreshMenu();
        }
        return indexMenu.get(itemNum);
    }

    private void refreshMenu() {
        StringBuilder menu = new StringBuilder();
        indexMenu = new HashMap<>();
        buildMenu(menu, this.getKids(), "", "");
        menuAll = menu.toString();
    }

    private void buildMenu (StringBuilder menu, ArrayList<Item> kids,
                            String numBegin, String lineBegin) {
        int index = 1;
        for (Item item : kids) {
            String number = String.format("%s%s", numBegin, index++);
            menu.append(String.format("%s%s %s%n", lineBegin, item.getContent(),number));
            indexMenu.put(number, item.getAction());
            if (item.getKids() != null) {
                buildMenu(menu, item.getKids(), number + ".", lineBegin + this.delim);
            }
        }
    }
}
