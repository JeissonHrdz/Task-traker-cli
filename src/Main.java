
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cli cli = new Cli();
        String command = "";
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Task-cli ");
            command = scanner.nextLine();
            if (command.startsWith("add")) {
                cli.add(command);
            } else if (command.startsWith("update")) {
                cli.updateDescription(command);
            } else if (command.startsWith("delete")) {
                cli.delete(command);
            } else if (command.startsWith("mark-")) {
                cli.updateStatus(command);
            } else if (command.equals("list")) {
                cli.list();
            } else if (command.startsWith("list done") ||
                    command.startsWith("list todo") ||
                    command.startsWith("list in-progress")) {
                System.out.println("Entro");
                cli.listTaskForStatus(command);
            }
        } while (!command.equals("exit"));

    }
}