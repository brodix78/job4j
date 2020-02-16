package ru.job4j.exam;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Conn {
    private int size;
    private String[] lastInGroup;
    private boolean groupClosed = true;
    ArrayList<String> group;

    private PriorityQueue<ArrayList<String>> groups =
            new PriorityQueue<>((o1, o2) -> o2.size() - o1.size());


    public String groups(String data) {
        groupClosed = true;
        int index = 0;
        int length = data.length();
        size = 1;
        while (index < length && data.charAt(index) != '\n') {
            if (data.charAt(index++) == ';') {
                size++;
            }
        }
        index = 0;
        int cell = 0;
        String[] row = new String[size];
        String element = "";
        while (index < length) {
            char ch = data.charAt(index++);
            if (ch == ';' || ch == '\n') {
                row[cell++] = element;
                element = "";
            } else {
                element = element + ch;
            }
            if (cell == size) {
                genGroups(row);
                element = "";
                row = new String[size];
                cell = 0;
            }
        }
        if (!element.isEmpty()) {
            row[cell] = element;
            genGroups(row);
            groups.add(group);
        }
        StringBuilder rsl = new StringBuilder();
        int groupNumber = 1;
        while (!groups.isEmpty()) {
            group = groups.poll();
            rsl.append("Группа ");
            rsl.append(groupNumber++);
            rsl.append(" содержит ");
            rsl.append(group.size());
            rsl.append(" элементов");
            rsl.append("\n");
            for (String r : group) {
                rsl.append(r);
                rsl.append("\n");
            }
            rsl.append("\n");
        }
        return rsl.toString();
    }

    private void genGroups(String[] row) {
        if (!groupClosed) {
            if (inGroup(row)) {
                group.add(String.join(";", row));
            } else {
                groups.add(group);
                groupClosed = true;
            }
        }
        if (groupClosed) {
            lastInGroup = row;
            groupClosed = false;
            group = new ArrayList<>();
            group.add(String.join(";", row));
        }
    }

    public boolean inGroup(String[] row) {
        boolean rsl = false;
        for (int i = 0; i < size; i++) {
            if (!row[i].isEmpty() && row[i].equals(lastInGroup[i])) {
                rsl = true;
                lastInGroup = row;
                break;
            }
        }
        return rsl;
    }
}