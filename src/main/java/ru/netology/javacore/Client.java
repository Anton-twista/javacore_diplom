package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[]{"Работать", "Дела по дому",
                "Сходить в магазин", "Пойти гулять",
                "Учить уроки", "Отдыхать",
                "Сходить в кино", "Приготовить еды"};
        int action;
        int taskNumber;

        while (true) {
            try (
                    Socket socket = new Socket("localhost", 8989);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            ) {
                System.out.println("Выберите действие:\n"
                        + "1 Добавить задачу\n"
                        + "2 Удалить задачу\n"
                        + "0 Выход\n");
                action = scanner.nextInt();

                if (action == 0) {
                    System.out.println("Вы вышли");
                    break;
                }

                switch (action) {
                    case 1:
                        System.out.println("Введите номер задачи чтобы добавить. 0 - Выход");
                        allTasks(tasks);
                        taskNumber = scanner.nextInt();
                        if (taskNumber == 0) {
                            System.out.println("Вы вышли");
                            break;
                        }
                        out.println("{ \"type\": \"ADD\", \"task\": \"" + tasks[taskNumber - 1] + "\" }");
                        System.out.println(in.readLine());
                        break;

                    case 2:
                        System.out.println("Введите номер задачи чтобы удалить. 0 - Выход");
                        allTasks(tasks);
                        taskNumber = scanner.nextInt();
                        if (taskNumber == 0) {
                            System.out.println("Вы вышли");
                            break;
                        }
                        out.println("{ \"type\": \"REMOVE\", \"task\": \"" + tasks[taskNumber - 1] + "\" }");
                        System.out.println(in.readLine());
                        break;
                }
            }
        }
    }

    public static void allTasks(String[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
    }
}

