import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
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
        int id = 0;
        List<String> objetos = new ArrayList<>();
        objetos = normalizeJson();
        List<Task> list = listTasks();
        if (list.size() > 1) {
            for (Task t : list) {
                id = t.getId();
            }
        }

        String newTask = "{ \n" +
                "\"id\": " + (id + 1) + ",\n" +
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(int id, String newStatus, String dateUpdated) {
        List<String> objetos = new ArrayList<>();
        List<String> tasksUpadted = new ArrayList<>();
        objetos = normalizeJson();

        for (String tasks : objetos) {
            String objetoNormalizado = tasks;
            if (objetoNormalizado.contains("\"id\": " + id)) {
                objetoNormalizado = objetoNormalizado.replaceFirst("\"status\": \"[^\"]+\"", "\"status\": \"" + newStatus + "\"");
                objetoNormalizado = objetoNormalizado.replaceFirst("\"updateAt\": \"[^\"]+\"", "\"updateAt\": \"" + dateUpdated + "\"");
            }
            tasksUpadted.add(objetoNormalizado);
        }

        String nuevoJson = "[" + String.join(", ", tasksUpadted) + "]";
        try (FileWriter file = new FileWriter("task.json")) {
            file.write(nuevoJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateDescription(int id, String newDescription, String dateUpdated) {
        List<String> objetos = new ArrayList<>();
        List<String> tasksUpadted = new ArrayList<>();
        objetos = normalizeJson();

        for (String tasks : objetos) {
            String objetoNormalizado = tasks;
            if (objetoNormalizado.contains("\"id\": " + id)) {
                objetoNormalizado = objetoNormalizado.replaceFirst("\"description\": \"[^\"]+\"", "\"description\": \"" + newDescription + "\"");
                objetoNormalizado = objetoNormalizado.replaceFirst("\"updateAt\": \"[^\"]+\"", "\"updateAt\": \"" + dateUpdated + "\"");
            }
            tasksUpadted.add(objetoNormalizado);
        }

        String nuevoJson = "[" + String.join(", ", tasksUpadted) + "]";
        try (FileWriter file = new FileWriter("task.json")) {
            file.write(nuevoJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        List<String> objetos = new ArrayList<>();
        List<String> tasksUpdates = new ArrayList<>();
        objetos = normalizeJson();
        String deletedTask = "";

        for (String tasks : objetos) {
            String objetoNormalizado = tasks;
            if (!objetoNormalizado.contains("\"id\": " + id)) {
                tasksUpdates.add(objetoNormalizado);
            }

        }

        String nuevoJson = "[" + String.join(", ", tasksUpdates) + "]";
        try (FileWriter file = new FileWriter("task.json")) {
            file.write(nuevoJson.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> listTasks() {
        List<String> tasks = new ArrayList<>();
        List<Task> listTasks = new ArrayList<>();

        tasks = normalizeJson();

            for (String task : tasks) {
               // System.out.println(task);
                String atrr = task.trim().substring(1, task.length() - 1);
                System.out.println(atrr);
                String[] attrSeparatted = atrr.split(",\\s");
                Task task1 = new Task();
                for (String attr : attrSeparatted) {
                    String[] keyValue = attr.split(":",2);
                    String clave = keyValue[0].trim().replace("\"", "");
                    String valor = keyValue[1].trim();
                    System.out.println(valor);
                    switch (clave) {
                        case "id":
                            task1.setId(Integer.parseInt(valor));
                            break;
                        case "description":
                            task1.setDescription(valor.replace("\"", ""));
                            break;
                        case "status":
                            task1.setStatus(valor.replace("\"", ""));
                            break;
                        case "createdAt":
                            task1.setCreatedAt("");
                            break;
                        case "updateAt":
                            task1.setUpdatedAt("");
                            break;
                    }
                }
                listTasks.add(task1);
            }



        return listTasks;
    }
}
