import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Type;
public class User {
    
    private static ArrayList<Student> students;
    private static ArrayList<Instructor> instructors;
    private String filename;
    
    public User(String filename) {
        this.filename=filename;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }
    
    
    public void load() {
    try (FileReader reader = new FileReader(filename)) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, List<Object>>>() {}.getType();
        Map<String, Object> data = gson.fromJson(reader, type);


        students = gson.fromJson(gson.toJson(data.get("students")), new TypeToken<ArrayList<Student>>(){}.getType());
        instructors = gson.fromJson(gson.toJson(data.get("instructors")), new TypeToken<ArrayList<Instructor>>(){}.getType());

    } catch (Exception e) {
        System.out.println("Error Loading File OR file doesn't exist: " + e.getMessage());
        students = new ArrayList<>();
        instructors = new ArrayList<>();
    }
}


   public void save() {
    try (FileWriter writer = new FileWriter(filename)) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Map<String, Object> data = new HashMap<>();
        data.put("students", students);
        data.put("instructors", instructors);
        gson.toJson(data, writer);
    } catch (Exception e) {
        System.out.println("Error Saving File: " + e.getMessage());
    }
}


}