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

        List<String> objetos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("task.json"))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            String jsonString = json.toString().trim();

            if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
                String[] obj = jsonString.substring(1, json.length() - 1).split("\\},\\{");

                for (String object : obj) {
                    objetos.add(object.startsWith("{") ? object : ""+ object);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String newTask = "{ \n" +
                "\"id\": " + task.getId() + ",\n" +
                "\"description\": \"" + task.getDescription() + "\",\n" +
                "\"status\": \"" + task.getStatus() + "\",\n" +
                "\"createdAt\": \"" + task.getCreatedAt() + "\",\n" +
                "\"updateAt\": \"" + task.getUpdatedAt() + "\"\n" +
                "}";

        objetos.add(newTask);

        StringBuilder nuevoJson = new StringBuilder("[");
        for (int i = 0; i < objetos.size(); i++) {
            nuevoJson.append(objetos.get(i));
            if (i < objetos.size() - 1) {
                nuevoJson.append(", ");
            }
        }
        nuevoJson.append("]");



        try (FileWriter file = new FileWriter("task.json")) {
            file.write(nuevoJson.toString());
            System.out.println("Json Created Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
