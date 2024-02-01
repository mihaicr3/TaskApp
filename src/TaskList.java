import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {

    public List<Task> listOfTasks=new ArrayList<>();

    public void addTask(Task task)
    {
        listOfTasks.add(task);
    }
}
