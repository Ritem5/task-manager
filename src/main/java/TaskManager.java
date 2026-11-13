package main.java;

import java.io.IOException;
import java.util.*;
import java.nio.file.*;

public class TaskManager {
    private static final Path FILE_PATH = Paths.get("tasks.txt");

    public static List<Task> loadTasks(){
        List<Task> tasks = new ArrayList<>();

        if(!Files.exists(FILE_PATH)){
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);
            for (String line : lines) {
                String[] parts = line.split(";", 2);
                if (parts.length == 2) {
                    boolean isDone = Boolean.parseBoolean(parts[0]);
                    String text = parts[1];
                    tasks.add(new Task(text, isDone));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return tasks;
    }

    public static void saveTasks(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.isDone() + ";" + task.getText());
        }

        try {
            Files.write(FILE_PATH, lines);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = TaskManager.loadTasks();


        while (true) {
        System.out.print("> ");
        String cmd = scanner.nextLine();
        String[] parts = cmd.split(" ",2); // [0] - команда, [1] - номер (если есть)

        if (cmd.equals("exit")){
            TaskManager.saveTasks(tasks);
            break;
        }else if(cmd.equals("list")) {
            for(int i = 0; i < tasks.size() ; i++){
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }else if(cmd.startsWith("add")){
              tasks.add(new Task(cmd.substring(4), false));
        } else if (parts[0].equals("done") || parts[0].equals("delete")){
            if(parts.length < 2) {                                          // ввели просто "done"
                System.out.println("Укажи номер, например: done 2");
                continue;                                                   // сразу к следующей команде
            }
            try {
                int index = Integer.parseInt(parts[1].trim()) - 1;          // номер с 1 -> индексом с 0
                if (index < 0 || index >= tasks.size()) {
                    System.out.println("Задачи с таким номером нет");
                }else if (parts[0].equals("done")) {
                    tasks.get(index).setDone(true);
                    System.out.println("Готово");
                }else {
                    tasks.remove(index);
                    System.out.println("Удалено");
                }
            } catch (NumberFormatException e) {             // ввели "done abc"
                System.out.println("Номер должен быть числом");
            }
        }else {
            System.out.println("Неизвестная команда");
        }
        }

    }
}
