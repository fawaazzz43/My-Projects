
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Courses
{
    private static ArrayList<Course> courses;
    private String FileName;

    public Courses(String FileName)
    {
        this.FileName = FileName;
    }

    public ArrayList<Course> getCourses()
    {
        return courses;
    }

    public void load() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(FileName)));

        // Load into the courses list
        courses = gson.fromJson(json, new TypeToken<List<Course>>() {}.getType());

        // Initialize if null
        if (courses == null) {
            courses = new ArrayList<>();
        }
    }

    public void addCourse(Course course)
    {
        courses.add(course);
    }

    public void deleteCourse(Course course)
    {
        courses.remove(course);
    }


    public void SaveToJsonCourses() throws IOException
    {
        try (FileWriter writer = new FileWriter(FileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer); // ← PROBLEM: Saving 'this' (Courses object)
        } catch (Exception e) {
            System.out.println("Error Saving File: " + e.getMessage());
        }
    }
}
    /*public void SaveToJsonCourses()
    {
        JSONArray arr = new JSONArray();

        for (Course c : courses)
        {
            arr.put(new JSONObject(c));
        }
        try (FileWriter file = new FileWriter(FileName))
        {
            file.write(arr.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }*/