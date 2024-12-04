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

    }

    public void updateStatus(Task updateStatus) {

    }

    public List<Task> getAllTasks() {
        return null;
    }

    public List<Task> getTasksByStatus(String status) {
        return null;
    }

}
