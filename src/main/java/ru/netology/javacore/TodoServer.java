package ru.netology.javacore;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {

    private final int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        JSONParser parser = new JSONParser();
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    Object obj = parser.parse(in.readLine());
                    JSONObject jsonObject = (JSONObject) obj;
                    String typeOperation = (String) jsonObject.get("type");
                    String task = (String) jsonObject.get("task");

                    if (typeOperation.equals("ADD")) {
                        todos.addTask(task);
                        out.println("Задача: " + task + ", внесена в список задач. Актуальные задачи: " + todos.getAllTasks() + "\n");
                    }

                    if (typeOperation.equals("REMOVE")) {
                        todos.removeTask(task);
                        out.println("Задача: " + task + ", удалена из списка задач. Актуальные задачи: " + todos.getAllTasks() + "\n");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
