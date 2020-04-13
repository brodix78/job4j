package ru.job4j.isp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;

public class Menu extends Item{

    private HashMap<String, String> indexMenu;
    private String menuAll;

    public Menu(String content) {
        super(content);
    }

    private ByteArrayInputStream input;
    private PrintStream output;

    public String menu () throws IOException {
        String rsl = null;
        if (indexMenu == null || menuAll == null) {
            refreshMenu();
        }
        output.print(menuAll);
        String in = input.readAllBytes().toString();
        if (!Character.isDigit(in.charAt(in.length()-1))) {
            in = in + ".";
        }
        return indexMenu.get(in);
    }

    private void refreshMenu() {
        StringBuilder menu = new StringBuilder();
        indexMenu = new HashMap<>();
        buildMenu(menu, this.getKids(), "", "",0, "  ");
        menuAll = menu.toString();
    }

    private void buildMenu (StringBuilder menu, LinkedList<Item> kids,
                            String numBegin, String lineBegin,
                            int level, String delim) {
        int index = 1;
        for (Item item : kids) {
            String number = String.format("%s%s.", numBegin, index++);
            menu.append(String.format("%s%s %s%n", lineBegin, item.getContent(),number));
            indexMenu.put(number, item.getAction());
            if (item.getKids() != null) {
                buildMenu(menu, item.getKids(), number, lineBegin + delim, level + 1, delim);
            }
        }
    }
}
