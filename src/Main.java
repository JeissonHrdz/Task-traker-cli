import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        TaskDao taskDao = new TaskDao();
        String command = "";
        JsonConstructor jsonConstructor = new JsonConstructor();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Task-cli ");
            command = scanner.nextLine();
            if (command.startsWith("add")) {
                command = command.replace("add ", "");
                System.out.println(command);
                if (command.startsWith("\"") && command.endsWith("\"")) {
                    String taskDescription = command.replace("\"", "");
                    Task task = new Task(0, taskDescription, "todo", new Date(), new Date());
                    taskDao.newTask(task);
                } else {
                    System.out.println("Invalid task description");
                }
            }
        } while (!command.equals("exit"));


        //   Task task = new Task(4, "Create Front", "Done", new Date(), new Date());

        //  taskDao.updateStatus(4, "In Progress");
        //taskDao.updateDescription(4,"SQL DB");
        // taskDao.deleteTask(4);
        //taskDao.getTasksByStatus("Done");
    }
}