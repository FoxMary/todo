import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    public void commands() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while (true) {
            String msg = reader.readLine();
            String[] tokens = msg.split("\\s", 2);
            if (msg.startsWith("add")) {
                if (tokens.length == 1) out.println("Неверное описание задачи");
                else add(tokens[1]);
            }else if (msg.startsWith("edit")) {
                if (tokens.length == 1) out.println("Неверные номер и описание задачи");
                else edit(tokens[1]);
            }else if (msg.startsWith("delete")) {
                if (tokens.length == 1) out.println("Неверный номер задачи");
                else delete(tokens[1]);
            } else if (msg.startsWith("print")) {
                if (tokens.length == 1) print(false);
                else if (tokens[1].equals("all")) print(true);
                else out.println("Неверный аргумент");
            } else if (msg.startsWith("toggle")) {
                if (tokens.length == 1) out.println("Неверный номер задачи");
                else toggle(tokens[1]);
            } else if (msg.startsWith("search")) {
                if (tokens.length == 1) out.println("Неверный критерий поиска");
                else search(tokens[1]);
            }
            else if (msg.startsWith("quit")) {
                reader.close();
                break;
            } else out.println("Неверная команда");
        }
    }

    private void add(String description) {
        taskList.add(new Task(taskList.size() + 1, description));
    }

    private void edit(String msg) {
        String[] tokens = msg.split("\\s", 2);
        if (tokens.length == 1) out.println("Неверное описание задачи");
        else {
            try {
                int i = Integer.parseInt(tokens[0]);
                if (i <= 0 || i-1 >= taskList.size()) out.println("Задача с таким номером не найдена");
                else taskList.set(i-1, new Task(i, tokens[1]));
            } catch (NumberFormatException e) {
                out.println("Неверный номер задачи");
            }
        }
    }

    private void delete(String id) {
        try {
            int i = Integer.parseInt(id);
            if (i <= 0 || i-1 >= taskList.size()) out.println("Задача с таким номером не найдена");
            else taskList.remove(i-1);

            for (Task t : taskList) {
                if (t.getId() == i + 1) {
                    t.setId(i);
                    i++;
                }
            }
        } catch (NumberFormatException e) {
            out.println("Неверный номер задачи");
        }
    }

    private void search(String msg) {
        boolean coincidence = false;
        for (Task t : taskList) {
            if (t.getDescription().contains(msg)) {
                coincidence = true;
           out.println(t.getId() + ". " + t.getStatusIcon() + " " + t.getDescription());
            }
        }
        if (!coincidence) out.println("Задач не найдено");
    }

    private void print(boolean argument) {
        for (Task t : taskList) {
            if (argument || !t.isStatus()) out.println(t.getId() + ". " + t.getStatusIcon() + " " + t.getDescription());
        }
    }

    private void toggle(String id) {
        try {
            int i = Integer.parseInt(id);
            if (i > 0 & i < taskList.size()) {
                for (Task t : taskList) {
                    if (t.getId() == i) t.setStatus(!t.isStatus());
                }
            } else out.println("Задача с таким номером не найдена");
        } catch (NumberFormatException e) {
            out.println("Неверный номер задачи");
        }
    }
}
