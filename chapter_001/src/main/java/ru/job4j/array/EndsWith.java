package ru.job4j.array;

public class EndsWith {
    public static boolean endsWith(char[] word, char[] post) {
        for (int i = 1; i <= post.length; i++) {
            if (word[word.length - i] != post[post.length - i]) {
                return false;
            }
        }
        return true;
    }
}
