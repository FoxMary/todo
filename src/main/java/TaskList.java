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
            } else if (msg.startsWith("print")) {
                if (tokens.length == 1) print(false);
                else if (tokens[1].equals("all")) print(true);
                else out.println("Неверный аргумент");
            } else if (msg.startsWith("toggle")) {
                if (tokens.length == 1) out.println("Неверный номер задачи");
                else {
                    try {
                        int i = Integer.parseInt(tokens[1]);
                        toggle(i);
                    } catch (NumberFormatException e) {
                        out.println("Неверный номер задачи");
                    }
                }
            } else if (msg.startsWith("quit")) {
                reader.close();
                break;
            } else out.println("Неверная команда");
        }
    }

    private void add(String description) {
        taskList.clear();
        taskList.add(new Task(1, description));
    }

    private void print(boolean argument) {
            for (Task t : taskList) {
                String st = "[ ]";
                if (t.isStatus()) st = "[x]";
                if (argument) out.println(t.getId() + ". " + st + " " + t.getDescription());
                if (!argument & !t.isStatus()) out.println(t.getId() + ". " + st + " " + t.getDescription());
            }
    }

    private void toggle(int id) {
        for (Task t : taskList) {
            if (t.getId() == id) t.setStatus(!t.isStatus());
        }
    }
}
