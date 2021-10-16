import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class TaskList {
    private final List<Task> taskList = new ArrayList<>();

    public void commands() {
        Scanner scanner = new Scanner(in);

        while (true) {
            String msg = scanner.nextLine();
            String[] tokens = msg.split("\\s", 2);
            if (msg.startsWith("add")) {
                if (tokens.length == 1) out.println("Неверное описание задачи");
                else add(tokens[1]);
            } else if (msg.startsWith("print")) {
                if (tokens.length == 1) printNotDone();
                else if (tokens[1].equals("all")) printAll();
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
                break;
            } else {
                out.println("Неверная команда");
            }
        }
    }

    private void add(String description) {
        taskList.clear();
        taskList.add(new Task(1, description));
    }

    private void printNotDone() {
        for (Task t : taskList) {
            if (!t.isStatus()) out.println(t.getId() + ". [ ] " + t.getDescription());
        }
    }

    private void printAll() {
        for (Task t : taskList) {
            String st = "[ ]";
            if(t.isStatus()) st = "[x]";
            out.println(t.getId() + ". " + st + " " + t.getDescription());
        }
    }

    private void toggle(int id) {
        for (Task t : taskList) {
            if (t.getId() == id) t.setStatus(!t.isStatus());
        }
    }
}
