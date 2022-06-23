package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {

    private final List<String> todos = new ArrayList<>();

    public List<String> getTasksList() {
        return todos;
    }

    public void addTask(String task) {
        todos.add(task);
    }

    public void removeTask(String task) {
        todos.remove(task);
    }

    public String getAllTasks() {
        return todos.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(", "));
    }
}