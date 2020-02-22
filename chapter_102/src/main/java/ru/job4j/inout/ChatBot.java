package ru.job4j.inout;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ChatBot {

    private File book;
    private ArrayList<PhraseIndex> phrasesIndex;

    private static class PhraseIndex {
        long start;
        int length;

        public PhraseIndex(long start, int length) {
            this.start = start;
            this.length = length;
        }
    }

    private void botLife(BufferedReader input, PrintWriter output) {
        phrasesCollect();
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
                        String answer = "Bot: " + botAnswer() + "\n";
                        output.print(answer);
                        writer.write(answer);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String botAnswer() {
        Random random = new Random();
        PhraseIndex phrase = phrasesIndex.get(random.nextInt(phrasesIndex.size()));
        char[] answer = new char[phrase.length];
        try (BufferedReader reader = new BufferedReader(new FileReader(this.book))) {
            reader.skip(phrase.start);
            reader.read(answer, 0, phrase.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String rsl = new String(answer);
        return rsl.replaceAll("\n", " ");
    }

    private void phrasesCollect() {
        this.phrasesIndex = new ArrayList<>();
        String endSymbols = ".;!?";
        try (BufferedReader reader = new BufferedReader(new FileReader(this.book))) {
            int sign;
            boolean findNew = false;
            long start = 0;
            long index = 0;
            while ((sign = reader.read()) != -1) {
                if (!findNew && sign != ' ' && sign != '\n') {
                    start = index;
                    findNew = true;
                }
                if (findNew && endSymbols.contains(Character.toString((char) sign))) {
                    if (index - start > 2) {
                        this.phrasesIndex.add(new PhraseIndex(start, (int) (index - start + 1)));
                    }
                    findNew = false;
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length == 1 && args[0].endsWith(".txt")) {
            File txt = new File(args[0]);
            ChatBot chat = new ChatBot();
            chat.book = txt;
            PrintWriter output = new PrintWriter(System.out, true);
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            chat.botLife(input, output);
        } else {
            System.out.println("Usage is: char.jar textFile.txt");
        }
    }
}
