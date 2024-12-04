import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonConstructor {

    public void createJson(Task task) {
        String json = "[]";

        try (FileWriter file = new FileWriter("task.json")) {
            file.write(json);
            System.out.println("Json Created Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Task task) {
        StringBuilder json = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("task.json"))) {

            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

        } catch (FileNotFoundException f){
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = json.toString();
        String[] obj = new String[]{jsonString.replace("[", "").replace("]", "")};

        List<String> objetos = new ArrayList<>();

        String newTask = "{\n" +
                "\"id\": " + task.getId() + ",\n" +
                "\"description\": \"" + task.getDescription() + "\",\n" +
                "\"status\": \"" + task.getStatus() + "\",\n" +
                "\"createdAt\": \"" + task.getCreatedAt() + "\",\n" +
                "\"updateAt\": \"" + task.getUpdatedAt() + "\"\n" +
                "}";

        objetos.add(newTask);
        for (String tasks : obj) {
            System.out.println(tasks);
            objetos.add(tasks);
        }

        String newJson = "[" + String.join(",", objetos) + "]";
        try (FileWriter file = new FileWriter("task.json")) {
            file.write(newJson);
            System.out.println("Json Created Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
