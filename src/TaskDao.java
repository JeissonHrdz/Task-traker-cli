import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    JsonConstructor jc = new JsonConstructor();

    public void newTask(Task newTask) {
        Task task = new Task(
                newTask.getId(),
                newTask.getDescription(),
                newTask.getStatus(),
                newTask.getCreatedAt(),
                newTask.getUpdatedAt()
        );
        jc.save(task);
    }

    public void updateTask(Task updateTask) {
        jc.update(updateTask);
    }

    public void deleteTask(Integer id) {
        jc.delete(id);
    }

    public void updateStatus(Task updateStatus) {

    }

    public List<Task> getAllTasks() {
        List<Task> tasksList = new ArrayList<>();

        for (String task : jc.listTasks()) {

        }
      return tasksList;
    }

    public List<Task> getTasksByStatus(String status) {
        return null;
    }

}
