import java.util.Date;


public class Main {
    public static void main(String[] args) {
        TaskDao taskDao = new TaskDao();
        Task task = new Task(3, "Create Front", "Done", new Date(), new Date());
        // taskDao.newTask(task);
        //taskDao.updateTask(task);
        taskDao.deleteTask(2);
    }
}