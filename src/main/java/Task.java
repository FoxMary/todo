public class Task {
    private int id;
    private boolean status;
    private String description;

    public int getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public Task(int id, String description) {
        this.id = id;
        this.status = false;
        this.description = description;
    }
}
