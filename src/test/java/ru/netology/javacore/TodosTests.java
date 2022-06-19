package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodosTests {

    Todos todo = new Todos();

    @BeforeEach
    public void initData() {
        todo.addTask("Сон");
        todo.addTask("Уборка");
        todo.addTask("Работа");
        todo.addTask("Тренировка");
    }


    @Test
    void addTaskTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("Сон", "Уборка", "Работа", "Тренировка", "Сон"));
        todo.addTask("Сон");
        Assertions.assertEquals(expected, todo.getTasksList());
    }

    @Test
    void remoteTaskTest() {
        List<String> expected = new ArrayList<>(Arrays.asList("Сон", "Уборка", "Работа", "Тренировка"));
        todo.removeTask("Ремонт");
        Assertions.assertEquals(expected, todo.getTasksList());
    }

    @Test
    void getAllTasksTest() {
        String expected = "Работа, Сон, Тренировка, Уборка";
        String result = todo.getAllTasks();
        Assertions.assertEquals(expected, result);
    }
}
