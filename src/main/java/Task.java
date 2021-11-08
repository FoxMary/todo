import lombok.Data;

@Data
public class Task {
    private String description;
    private boolean status;
    private String statusIcon;

    public Task(String description) {
        this.description = description;
        status = false;
        statusIcon = "[ ]";
    }

    public void setStatus(boolean status) {
        this.status = status;
        if (status) statusIcon = "[x]";
        else statusIcon = "[ ]";
    }
}
