package ru.job4j.exam;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ConFiles {

    private LinkedList<ArrayList<String>> groups = new LinkedList<>();
    private HashMap<String, ArrayList<String>> selector = new HashMap<>();
    String ln = System.lineSeparator();

    public void connections(File data) {
        try (BufferedReader in = new BufferedReader(new FileReader(data));
        PrintWriter out = new PrintWriter(new FileWriter(new File(data.getParent() + "/groups.txt")))) {
            String line;
            while ((line = in.readLine()) != null) {
                groupsGenerator(line);
            }
            groups = groups.stream().filter(group -> group.size() > 1)
                    .sorted((o1, o2) -> o2.size() - o1.size())
                    .collect(Collectors.toCollection(LinkedList::new));
            int groupNumber = 1;
            StringBuilder rsl = new StringBuilder();
            rsl.append(String.format("Количество групп: %s%s", groups.size(), ln));
            for (ArrayList<String> group:groups) {
                rsl.append(String.format("Группа %s содержит %s элементов %s", groupNumber++, group.size(), ln));
                for (String values : group) {
                    rsl.append(values);
                    rsl.append(ln);
                }
                out.println(rsl.toString());
                rsl.setLength(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void groupsGenerator(String lineIn) {
        ArrayList<String> group = null;
        String line = lineIn.replace("\"", "");
        for (String value:line.split(";")) {
            if (!value.isEmpty() && selector.containsKey(value)) {
                group = selector.get(value);
                group.add(lineIn);
                break;
            }
        }
        if (group == null) {
            group = new ArrayList<>(List.of(lineIn));
            groups.add(group);
        }
        for (String value:line.split(";")) {
            if (!value.isEmpty()) {
                selector.putIfAbsent(value, group);
            }
        }
    }

    public static void main(String[] args) {
        ConFiles job = new ConFiles();
        job.connections(new File("/home/ilya/lng.csv"));
    }
}

