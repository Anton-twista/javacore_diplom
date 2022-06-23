package ru.netology.javacore;

public class ServerRequest {
    private final String type;
    private final String task;

    public ServerRequest(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return "ServerRequest{" +
                "type='" + type + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
