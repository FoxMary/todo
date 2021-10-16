import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TaskList list = new TaskList();
        try {
            list.commands();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
