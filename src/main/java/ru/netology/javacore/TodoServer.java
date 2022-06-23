package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    public void start() {
        System.out.println("Starting server at " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    String line = in.readLine();
                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    ServerRequest req = gson.fromJson(line, ServerRequest.class);
                    String typeOperation =  req.getType();
                    String task = req.getTask();

                    if (typeOperation.equals("ADD")) {
                        todos.addTask(task);
                        out.println("Задача: " + task + ", внесена в список задач. Актуальные задачи: " + todos.getAllTasks() + "\n");
                    }

                    if (typeOperation.equals("REMOVE")) {
                        todos.removeTask(task);
                        out.println("Задача: " + task + ", удалена из списка задач. Актуальные задачи: " + todos.getAllTasks() + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
