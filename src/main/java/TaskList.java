import java.io.IOException;
import java.util.*;

import static java.lang.System.in;
import static java.lang.System.out;

public class TaskList {
    private final HashMap<Integer, Task> taskList = new HashMap<>();
    int count = 0;

    public void commands() throws IOException {
        final Scanner scanner = new Scanner(in);

        while (scanner.hasNext()) {
            String msg = scanner.next();
            if (msg.equals("add")) add(scanner);
            else if (msg.equals("edit")) edit(scanner);
            else if (msg.equals("delete")) delete(scanner);
            else if (msg.equals("print")) print(scanner);
            else if (msg.equals("toggle")) toggle(scanner);
            else if (msg.equals("search")) search(scanner);
            else if (msg.equals("quit")) return;
            else out.println("Неверная команда");
        }
    }

    private void add(Scanner scanner) {
        String msg = scanner.nextLine().trim();
        if (checkArgument(msg)) {
            count += 1;
            taskList.put(count, new Task(msg));
        }
    }

    private void edit(Scanner scanner) {
        String msg = scanner.nextLine().trim();
        if (checkArgument(msg)) {
            String[] tokens = msg.split("\\s", 2);
            int id = checkId(tokens[0]);
                if (tokens.length == 1) out.println("Неверное описание задачи");
                else if (id != -1 & taskList.containsKey(id)) taskList.get(id).setDescription(tokens[1]);
                else out.println("Задачи с таким номером не найдено");
        }
    }

    private void delete(Scanner scanner) {
        String msg = scanner.nextLine().trim();
        if (checkArgument(msg)) {
            int id = checkId(msg);
                if (id != -1) taskList.remove(id);
        }
    }

    private void print(Scanner scanner) {
        String msg = scanner.nextLine().trim();
        for (int i = 1; i <= count; i++) simplePrint(i, msg.equals("all") || !taskList.get(i).isStatus());
    }

    private void search(Scanner scanner) {
        String msg = scanner.nextLine().trim();
        if (checkArgument(msg)) {
            for (int i = 1; i <= count; i++) simplePrint(i, taskList.get(i).getDescription().contains(msg));
        }
    }

    private void simplePrint(int i, boolean b) {
        boolean flag = true;
        if (taskList.containsKey(i)) {
            if (b) {
                out.println(i + ". " + taskList.get(i).getStatusIcon() + " " + taskList.get(i).getDescription());
                flag = false;
            }
        }
        if (flag) out.println("Задач не найдено");
    }

    private void toggle(Scanner scanner) {
        String msg = scanner.nextLine().trim();
        if (checkArgument(msg)) {
            int id = checkId(msg);
            if (taskList.containsKey(id)) taskList.get(id).setStatus(!taskList.get(id).isStatus());
            else if (id != -1) out.println("Задачи с таким номером не найдено");
        }
    }

    private boolean checkArgument(String msg) {
        if (msg.length() == 0) {
            out.println("Нехватает аргумента");
            return false;
        } else return true;
    }

    private static int checkId(String stringId) {
        int id = -1;
        try {
            id = Integer.parseInt(stringId);
            return id;
        } catch (NumberFormatException e) {
            out.println("Неверный номер задачи");
        }
        return id;
    }
}
