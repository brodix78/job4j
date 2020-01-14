package ru.job4j.collection;

import java.util.HashSet;
import java.util.SplittableRandom;

public class UniqueText {
    public static boolean isEquals(String originText, String testText) {
        HashSet<String> originWords = new HashSet<>();
        boolean equal = true;
        String[] original = originText.split(" ");
        for (String word : original) {
            originWords.add(word);
        }
        for (String origWord : originWords) {
            equal = testText.contains(origWord);
            if (!equal) {
                break;
            }
        }
        return equal;
    }
}