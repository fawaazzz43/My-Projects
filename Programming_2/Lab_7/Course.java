
import com.google.gson.*;

import java.util.ArrayList;

import java.io.*;

public class Course {
    //private static final String fileName = "D:\\programming\\java\\lab7\\lab7_IJ\\courses.json";
    private String courseId;
    private String title;
    private String description;
    private int instructorId;
    private ArrayList<Lesson> lessons;
    private ArrayList<Student> studentsIncourse;
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    // private ArrayList<Lesson> lessons;

    public Course(String courseId, String title, String description, int instructorId) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        // this.lessons = new ArrayList<>();
        this.studentsIncourse = new ArrayList<>();
        this.lessons=new ArrayList<>();
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public ArrayList<Student> getStudentsIncourse() {
        return studentsIncourse;
    }



    public void AddStudent(Student student)
    {
        studentsIncourse.add(student);
    }
    public String getCourseId() {
        return courseId;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public int getInstructorId() {
        return instructorId;
    }
    // public ArrayList<Lesson> getLessons() {
    //     return lessons;
    // }

    /*public void saveToJSON() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            FileReader reader = new FileReader(fileName);
            JsonArray courses = JsonParser.parseReader(reader).getAsJsonArray();

            reader.close();



            JsonObject newCourse = new JsonObject();
            newCourse.addProperty("courseId", this.courseId);
            newCourse.addProperty("title", this.title);
            newCourse.addProperty("description", this.description);
            newCourse.addProperty("instructorId", this.instructorId);


            courses.add(newCourse);

            FileWriter writer = new FileWriter(fileName);
            gson.toJson(courses, writer);
            writer.flush();
            writer.close();

            System.out.println("Course saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    // public void displayAllCourses() {
    //     try {      
    //         FileReader reader = new FileReader(fileName);
    //         JsonArray courses = JsonParser.parseReader(reader).getAsJsonArray();
    //         for(JsonElement course : courses) {
    //             JsonObject courseObj = course.getAsJsonObject();
    //             System.out.println("Existing Course ID: " + courseObj.get("courseId")); 
    //             System.out.println("Existing Course Title: " + courseObj.get("title"));
    //             System.out.println("Instructor ID: " + courseObj.get("instructorId"));
    //             for(JsonElement student : courseObj.get("students").getAsJsonArray()) {
    //                 System.out.println(" - Student ID: " + student.getAsJsonObject().get("userId"));
    //                 System.out.println(" - Progress: " + student.getAsJsonObject().get("progress"));
    //             }
    //         } 
    //         reader.close();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    /*public ArrayList<Student> UniqeCourseStudents(int courseId) {

        try {

            FileReader reader = new FileReader(fileName);
            JsonArray courses = JsonParser.parseReader(reader).getAsJsonArray();
            for(JsonElement course : courses) {
                JsonObject courseObj = course.getAsJsonObject();
                if(courseObj.get("courseId").getAsInt() == courseId) {
                    for(JsonElement student : courseObj.get("students").getAsJsonArray()) {
                        String userId = student.getAsJsonObject().get("userId").getAsString();
                        int progress = student.getAsJsonObject().get("progress").getAsInt();
                        String username = student.getAsJsonObject().has("username") ? student.getAsJsonObject().get("username").getAsString() : "";
                        String email = student.getAsJsonObject().has("email") ? student.getAsJsonObject().get("email").getAsString() : "";
                        if(!studentsIncourse.contains(userId)) {
                            studentsIncourse.add(new Student(userId, "", username, email, "",progress));
                        }
                    }
                }
            }
            reader.close();
            return studentsIncourse;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/



}