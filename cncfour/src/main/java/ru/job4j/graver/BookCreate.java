package ru.job4j.graver;

import java.io.*;
import java.util.*;


public class BookCreate {

    public HashMap<String, Float[]> letters = new HashMap<>();

    public static void main(String[] args) {
        BookCreate book = new BookCreate();
        book.blockGenerator(new File("./graver/in/0_9.NC"));
        book.arcCreate();
    }

    private void blockGenerator(File dataNC) {
        try (BufferedReader data = new BufferedReader(new FileReader(dataNC))) {
            String let = null;
            ArrayList<String> letter = new ArrayList<>();
            String line;
            int index = 0;
            while ((line = data.readLine()) != null) {
                System.out.println(index++);
                line = line.replace(". ", " ");
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
        for(String ch:letters.keySet()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(String.format("./graver/book/%s.ar", ch)))) {
                out.writeObject(letters.get(ch));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Float[] signConverter(ArrayList<String> letter) {
        float previousX = 0;
        float previousY = 0;
        LinkedList<Float> sign = new LinkedList<>();
        for (String pointStr : letter) {
            pointStr = pointStr.replace("X.", "X0.");
            pointStr = pointStr.replace("Y.", "Y0.");
            pointStr = pointStr.replace("X-.", "X-0.");
            pointStr = pointStr.replace("Y-.", "Y-0.");
            String[] p = pointStr.split(" ");
            float x;
            float y;
            if (p[0].startsWith("X")) {
                x = Float.parseFloat(p[0].substring(1));
                if (p.length == 2) {
                    y = Float.parseFloat(p[1].substring(1));
                } else {
                    y = previousY;
                }
            } else {
                y = Float.parseFloat(p[0].substring(1));
                x = previousX;
            }
            sign.add(x);
            sign.add(y);
            previousX = x;
            previousY = y;
        }
        float xmin = sign.get(0);
        float ymin = sign.get(1);
        float xmax = xmin;
        float ymax = ymin;
        int i = 0;
        while (i < sign.size()) {
            if (xmin > sign.get(i)) {
                xmin = sign.get(i);
            } else if (xmax < sign.get(i)) {
                xmax = sign.get(i);
            }

            if (ymin > sign.get(++i)) {
                ymin = sign.get(i);
            } else if (ymax < sign.get(i)) {
                ymax = sign.get(i);
            }
            i++;
        }
        i = 0;
        while (i < sign.size()) {
            sign.set(i, sign.get(i++) - xmin);
            sign.set(i, sign.get(i++) - ymin);
        }
        sign.addFirst(ymax - ymin);
        sign.addFirst(xmax - xmin);
        return sign.toArray(Float[]::new);
    }
}