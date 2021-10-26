public class Task {
    private int id;
    private boolean status;
    private final String description;
    private String statusIcon;

    public String getStatusIcon() {
        return statusIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
        if (status) statusIcon = "[x]";
        else statusIcon = "[ ]";
    }

    public String getDescription() {
        return description;
    }

    public Task(int id, String description) {
        this.id = id;
        this.status = false;
        this.description = description;
        statusIcon = "[ ]";
    }
}
