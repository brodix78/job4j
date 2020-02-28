package ru.job4j.graver;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class BookCreate {

    public class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public HashMap<String, Point[]> letters = new HashMap<String, Point[]>();

    public static void main(String[] args) {
        BookCreate book = new BookCreate();
        book.blockGenerator(new File("/home/ilya/projects/job4j/cncfour/src/main/java/0_9.NC"));
        book.arcCreate();
    }

    private void blockGenerator(File dataNC) {
        try (BufferedReader data = new BufferedReader(new FileReader(dataNC));
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/home/ilya/projects/job4j/cncfour/src/main/java/book.ar"))) {
            String let = null;
            ArrayList<String> letter = new ArrayList<>();
            String line;
            while ((line = data.readLine()) != null) {
                if (line.startsWith("(")) {
                    if (let != null) {
                        letters.put(let, signConverter(letter));
                    }
                    let = line.substring(1);
                    letter = new ArrayList<>();
                } else if (line.startsWith("X") || line.startsWith("Y")){
                    letter.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void arcCreate() {
        for(String key:letters.keySet()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(String.format("/home/ilya/bookArc/%s.ar", key)))) {
                for (Point point : letters.get(key)) {
                    out.writeDouble(point.x);
                    out.writeDouble(point.x);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Point[] signConverter(ArrayList<String> letter) {
        Point previous = null;
        LinkedList<Point> sign = new LinkedList<>();
        for (String pointStr : letter) {
            pointStr = pointStr.replace("X.", "X0.");
            pointStr = pointStr.replace("Y.", "Y0.");
            String[] p = pointStr.split(" ");
            double x;
            double y;
            if (p[0].startsWith("X")) {
                x = Double.parseDouble(p[0].substring(1));
                if (p.length == 2) {
                    y = Double.parseDouble(p[1].substring(1));
                } else {
                    y =previous.y;
                }
            } else {
                y = Double.parseDouble(p[0].substring(1));
                x = previous.x;
            }
            sign.add(new Point(x, y));
            previous = new Point(x, y);
        }
        double xmin = sign.get(0).x;
        double ymin = sign.get(0).y;
        double xmax = xmin;
        double ymax = ymin;
        for (Point point:sign) {
            if (xmin > point.x) {
                xmin = point.x;
            }
            if (ymin > point.y) {
                ymin = point.y;
            }
            if (xmax < point.x) {
                xmax = point.x;
            }
            if (ymax < point.y) {
                ymax = point.y;
            }
        }
        for (Point point:sign) {
            point.x -= xmin;
            point.y -= ymin;
        }
        sign.addFirst(new Point(xmax - xmin, ymax - ymin));
        return sign.toArray(Point[]::new);
    }
}