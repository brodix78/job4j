package ru.job4j.array;

public class EndsWith {
    public static boolean endsWith(char[] word, char[] post) {
        boolean rsl = true;
        for (int i = 1; i <= post.length; i++) {
            if (word[word.length - i] != post[post.length - i]) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }
}
