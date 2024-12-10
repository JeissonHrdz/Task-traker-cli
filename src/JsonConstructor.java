import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class JsonConstructor {

    public List<String> normalizeJson() {
        List<String> objetos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("task.json"))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            String jsonString = json.toString().replaceAll("\n", "");
            jsonString = jsonString.trim();

            if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
                String[] obj = jsonString.substring(1, jsonString.length() - 1).split("\\},\\s*\\{");

                for (String object : obj) {
                    String objetoNormalizado = object.startsWith("{") ? object : "{" + object;
                    objetoNormalizado = objetoNormalizado.endsWith("}") ? objetoNormalizado : objetoNormalizado + "}";
                    objetos.add(objetoNormalizado);
                    //  objetos.add(object.startsWith("{") ? object : "{" + object);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objetos;
    }

    public void save(Task task) {

        List<String> objetos = new ArrayList<>();
        objetos = normalizeJson();

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

    public void update(Task task) {
        List<String> objetos = new ArrayList<>();
        List<String> tasksUpadted = new ArrayList<>();
        objetos = normalizeJson();
        String status = "tengohambre";


        for (String tasks : objetos) {
            String objetoNormalizado = tasks;
            if (objetoNormalizado.contains("\"id\": " + 2)) {
                objetoNormalizado = objetoNormalizado.replaceFirst("\"status\": \"[^\"]+\"", "\"status\": \"" + status + "\"");
            }
            tasksUpadted.add(objetoNormalizado);
        }

        String nuevoJson = "[" + String.join(", ", tasksUpadted) + "]";
        try (FileWriter file = new FileWriter("task.json")) {
            file.write(nuevoJson.toString());
            System.out.println("Json Created Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        List<String> objetos = new ArrayList<>();
        List<String> tasksUpdates = new ArrayList<>();
        objetos = normalizeJson();
        String deletedTask ="";

        for (String tasks : objetos) {
            String objetoNormalizado = tasks;
            if (!objetoNormalizado.contains("\"id\": " + id)) {
                tasksUpdates.add(objetoNormalizado);
            }
            
        }

        String nuevoJson = "[" + String.join(", ", tasksUpdates) + "]";
        try (FileWriter file = new FileWriter("task.json")) {
            file.write(nuevoJson.toString());
            System.out.println("Json Created Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> listTasks() {
        List<String> tasks = new ArrayList<>();
        tasks = normalizeJson();
        return tasks;
    }
}
