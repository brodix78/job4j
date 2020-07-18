package ru.job4j.exam;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class Explorer implements Callable<List<Map<String, String>>> {

    /**
     *
     * @return required data as a List of Maps(String, String)
     * @throws Exception like IO exception
     */
    abstract public List<Map<String, String>> call() throws Exception;
    abstract public Explorer getInstance(String source);
}
