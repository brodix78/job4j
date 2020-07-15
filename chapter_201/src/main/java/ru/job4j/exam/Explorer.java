package ru.job4j.exam;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class Explorer implements Callable<List<Map<String, String>>> {
    abstract public List<Map<String, String>> call();
    abstract public Explorer getInstance(String source);
}
