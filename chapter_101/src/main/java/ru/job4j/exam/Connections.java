package ru.job4j.exam;

import java.util.*;

public class Connections {
    private int size;

    private class Row {
        public String[] values;

        public Row(String[] row) {
            this.values = row;
        }

        @Override
        public String toString() {
            return String.join(";", this.values);
        }
    }

    private class LastInGroup {
        Row last;

        public LastInGroup(Row row) {
            last = row;
        }

        public boolean changeLast(Row row) {
            boolean rsl = false;
            for (int i = 0; i < size; i++) {
                if (!row.values[i].isEmpty() && row.values[i].equals(last.values[i])) {
                    rsl = true;
                    last = row;
                    break;
                }
            }
            return rsl;
        }
    }

    public String groups(String data) {
        int index = 0;
        int length = data.length();
        LinkedList<Row> rows = new LinkedList<>();
        this.size = 1;
        while (index < length && data.charAt(index) != '\n') {
            if (data.charAt(index++) == ';') {
                this.size++;
            }
        }
        index = 0;
        int cell = 0;
        String[] cells = new String[this.size];
        String row = "";
        while (index < length) {
            char ch = data.charAt(index++);
            if (ch == ';' || ch == '\n') {
                cells[cell++] = row;
                row = "";
            } else {
                row = row + ch;
            }
            if (index == length) {
                cells[cell++] = row;
            }
            if (cell == this.size) {
                rows.add(new Row(cells));
                row = "";
                cells = new String[this.size];
                cell = 0;
            }
        }
        Iterator<Row> it = rows.iterator();
        PriorityQueue<ArrayList<Row>> groups =
                new PriorityQueue<>((o1, o2) -> o2.size() - o1.size());
        LastInGroup last = null;
        ArrayList<Row> group = null;
        Row tmp;
        boolean groupClosed = true;
        while (it.hasNext()) {
            tmp = it.next();
            if (!groupClosed) {
                if (last.changeLast(tmp)) {
                    group.add(tmp);
                } else {
                    groups.add(group);
                    groupClosed = true;
                }
            }
            if (groupClosed) {
                groupClosed = false;
                last = new LastInGroup(tmp);
                group = new ArrayList<>();
                group.add(tmp);
            }
        }
        if (!groupClosed) {
            groups.add(group);
        }
        StringBuilder rsl = new StringBuilder();
        int groupNumber = 1;
        while (!groups.isEmpty()) {
            ArrayList<Row> rowGroup = groups.poll();
            rsl.append("Группа " + groupNumber++
                    + " содержит " + rowGroup.size()
                    + " элементов" + "\n");
            for (Row r : rowGroup) {
                rsl.append(r.toString() + "\n");
            }
            rsl.append("\n");
        }
        return rsl.toString();
    }
}