package ru.job4j.exam;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Connections {
    private int size = 3;
    private String[] lastInGroup;
    private boolean groupClosed = true;
    private ArrayList<String> group;
    private PriorityQueue<ArrayList<String>> groups =
            new PriorityQueue<>((o1, o2) -> o2.size() - o1.size());

    public String groups(String data) {
        data.lines().forEach(line -> {
            if (!groupClosed) {
                if (inOpenGroup(line.split(";"))) {
                    group.add(line);
                } else {
                    groupClosed = true;
                    groups.add(group);
                }
            }
            if (groupClosed) {
                groupClosed = false;
                lastInGroup = line.split(";");
                group = new ArrayList<>();
                group.add(line);
            }
        });
        groups.add(group);
        groupClosed = true;
        StringBuilder rsl = new StringBuilder();
        int groupNumber = 1;
        while (!groups.isEmpty()) {
            group = groups.poll();
            rsl.append("Группа ");
            rsl.append(groupNumber++);
            rsl.append(" содержит ");
            rsl.append(group.size());
            rsl.append(" элементов");
            rsl.append(System.lineSeparator());
            for (String line : group) {
                rsl.append(line);
                rsl.append(System.lineSeparator());
            }
        }
        return rsl.toString();
    }

    public boolean inOpenGroup(String[] line) {
        boolean rsl = false;
        for (int i = 0; i < size; i++) {
            if (!line[i].isEmpty() && line[i].equals(lastInGroup[i])) {
                rsl = true;
                lastInGroup = line;
                break;
            }
        }
        return rsl;
    }
}