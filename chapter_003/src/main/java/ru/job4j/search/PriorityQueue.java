package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<Task>();

    public void putTask(Task task) {
        var index = 0;
        for (Task curTask : this.tasks) {
            if (curTask.getPriority() >= task.getPriority()) {
                break;
            }
            index++;
        }
        this.tasks.add(index, task);
    }

    public Task takeTask() {
        return this.tasks.remove(0);
    }
}