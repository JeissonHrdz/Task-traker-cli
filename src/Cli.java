import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cli {

    TaskDao taskDao = new TaskDao();

    public void add(String command) throws ParseException {

        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        command = command.replace("add ", "");
        command = command.trim();
        if (command.startsWith("\"") && command.endsWith("\"")) {
            String taskDescription = command.replace("\"", "");
            Task task = new Task(0, taskDescription, "todo", (format1.format(date)),(format1.format(date)) );
            taskDao.newTask(task);
        } else {
            System.out.println("Invalid task description");
        }
    }

    public void list() {
       taskDao.getAllTasks();
    }

    public void listTaskForStatus(String command) {

        Pattern pattern = Pattern.compile("^list\\s+(.+)$");
        Matcher matcher = pattern.matcher(command);
        System.out.println("Entro");

        if (matcher.matches()) {
            String status = matcher.group(1);
            taskDao.getTasksByStatus(status);

        } else {
            System.out.println("Invalid command");
        }

    }

    public void updateDescription(String command) throws ParseException {
        int idTask = 0;

        Pattern pattern = Pattern.compile("^update\\s+(\\d+)\\s+.*");
        Matcher matcher = pattern.matcher(command);
        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");


        if (matcher.matches()) {
            idTask = Integer.parseInt(matcher.group(1)); // Obtener el ID del grupo 1
            command = command.replace("update " + idTask + " ", "");
            if (command.startsWith("\"") && command.endsWith("\"")) {
                String taskDescription = command.replace("\"", "");
                Task task = new Task(0, taskDescription, "todo", "", "");
                taskDao.updateDescription(idTask, taskDescription, format1.format(date));
            } else {
                System.out.println("Invalid task description");
            }
        } else {
            System.out.println("id invalid");
        }
    }

    public void delete(String command) {

        int idTask = 0;
        Pattern pattern = Pattern.compile("^delete\\s+(\\d+)$");
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            idTask = Integer.parseInt(matcher.group(1));
            taskDao.deleteTask(idTask);
            System.out.println("task deleted successfully");
        } else {
            System.out.println("id invalido");
        }
    }

    public void updateStatus(String command) {

        Pattern pattern = Pattern.compile("^(mark-(in-progress|done))\\s+(\\d+)$");
        Matcher matcher = pattern.matcher(command);
        Date date = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

        if (matcher.matches()) {
            String commandSplit = matcher.group(1);
            int id = Integer.parseInt(matcher.group(3));

            System.out.println("Comando: " + commandSplit);
            System.out.println("ID: " + id);

            if (commandSplit.equals("mark-in-progress")) {
                taskDao.updateStatus(id, "in progress", format1.format(date));
            } else if (commandSplit.equals("mark-done")) {
                taskDao.updateStatus(id, "done", format1.format(date));
            }
        } else {
            System.out.println("Invalid command");
        }

    }

}
