package ru.job4j.inout;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ChatBot {

    public static void main(String[] args) {
        if (args.length != 1 || !args[0].endsWith(".txt")) {
            System.out.println("Usage is: char.jar textFile.txt");
            return;
        }
            File txt = new File(args[0]);
            ChatBot chat = new ChatBot();
            PrintWriter output = new PrintWriter(System.out, true);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            chat.botLife(input, output, txt);
    }

    public void botLife(BufferedReader input, PrintWriter output, File book) {
        ArrayList<String> phrases = phrasesCollect(book);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(book.getParent() + "/bot.log")))) {
            boolean botAlive = true;
            boolean botSleeps = false;
            while (botAlive) {
                output.printf("User: ");
                String userSaid = input.readLine();
                writer.write("User: " + userSaid + "\n");
                if (userSaid.toLowerCase().equals("закончить")) {
                    botSleeps = true;
                    botAlive = false;
                }
                if (botSleeps) {
                    if (userSaid.toLowerCase().equals("продолжить")) {
                        botSleeps = false;
                        String bot = "Bot: все, не сплю.\n";
                        output.print(bot);
                        writer.write(bot);
                    }
                } else {
                    if (userSaid.toLowerCase().equals("стоп")) {
                        botSleeps = true;
                    } else {
                        Random random = new Random();
                        String answer = String.format("Bot: %s%s",
                                phrases.get(random.nextInt(phrases.size())).replaceAll("\n", " "),
                                System.lineSeparator());
                        output.print(answer);
                        writer.write(answer);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> phrasesCollect(File book) {
        ArrayList<String> phrases = new ArrayList<>();
        String endSymbols = ".;!?";
        StringBuilder phrase = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(book))) {
            int sign;
            boolean findNew = false;
            long start = 0;
            long index = 0;
            while ((sign = reader.read()) != -1) {
                if (!findNew && sign != ' ' && sign != '\n') {
                    phrase.setLength(0);
                    start = index;
                    findNew = true;
                }
                phrase.append((char) sign);
                if (findNew && endSymbols.contains(Character.toString((char) sign))) {
                    if (index - start > 2) {
                        phrases.add(phrase.toString());
                    }
                    findNew = false;
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phrases;
    }
}
