import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
            } else if (command.startsWith("update")) {
                int idTask = 0;

                Pattern pattern = Pattern.compile("^update\\s+(\\d+)\\s+.*");
                Matcher matcher = pattern.matcher(command);

                if (matcher.matches()) {
                    idTask = Integer.parseInt(matcher.group(1)); // Obtener el ID del grupo 1
                    command = command.replace("update " + idTask + " ", "");
                    if (command.startsWith("\"") && command.endsWith("\"")) {
                        String taskDescription = command.replace("\"", "");
                        Task task = new Task(0, taskDescription, "todo", new Date(), new Date());
                        taskDao.updateDescription(idTask, taskDescription);
                    } else {
                        System.out.println("Invalid task description");
                    }
                } else {
                    System.out.println("id invalido");
                }


            } else if (command.startsWith("delete")) {

                int idTask = 0;

                Pattern pattern = Pattern.compile("^delete\\s+(\\d+)$");
                Matcher matcher = pattern.matcher(command);

                if (matcher.matches()) {
                    idTask = Integer.parseInt(matcher.group(1)); // Obtener el ID del grupo 1
                    taskDao.deleteTask(idTask);
                    System.out.println("task deleted successfully");
                } else {
                    System.out.println("id invalido");
                }

            } else if (command.startsWith("mark-")) {

                int idTask = 0;
                Pattern pattern = Pattern.compile("^(\\w+)\\s+(\\d+)(\\s+.*)?$");
                Matcher matcher = pattern.matcher(command);

                if (matcher.matches()) {
                    idTask = Integer.parseInt(matcher.group(2));
                    String getCommand = matcher.group(1);
                    System.out.println(getCommand);

                    if (command.equals("mark-in-progress")) {
                        taskDao.updateStatus(idTask, "in-progress");
                    } else if (command.equals("mark-done")) {
                        taskDao.updateStatus(idTask, "done");

                    }
                } else {
                    System.out.println("id invalido");
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