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

    public void updateStatus(int id, String status) {
        jc.updateStatus(id, status);
    }

    public void deleteTask(Integer id) {
        jc.delete(id);
    }

    public void updateDescription(int id, String description) {
        jc.updateDescription(id, description);

    }

    public void getAllTasks() {
        List<Task> tasksList = new ArrayList<>();
        for (Task task : jc.listTasks()) {
            System.out.println("id: " + task.getId());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Status: " + task.getStatus());
            System.out.println("<------------------------------------------->");
        }
    }

    public void getTasksByStatus(String status) {
        List<Task> tasksList = new ArrayList<>();
        for (Task task : jc.listTasks()) {
            if (task.getStatus().equals(status)) {
                System.out.println("id: " + task.getId());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Status: " + task.getStatus());
                System.out.println("<------------------------------------------->");
            }
        }
    }
}

