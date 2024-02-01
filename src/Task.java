import java.io.Serializable;
import java.time.*;
public class Task implements Serializable {
    String name;
    LocalDate dateAdded;
    LocalDate deadlineDate;
    boolean isDone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task(String name, LocalDate dateAdded, LocalDate deadlineDate)
    {
        this.name=name;
        this.dateAdded=dateAdded;
        this.deadlineDate=deadlineDate;
        isDone=false;
    }
}
