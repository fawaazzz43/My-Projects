
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Student
{
    String userId ;
    String role ;
    String username ;
    String email ;
    String passwordHash ;
    ArrayList <Course> enrolledCourses ;
    double progress ;

    public Student(String userId, String role, String username, String email, String passwordHash , double progress)
    {
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;

        //super(userId,role,username,email,passwordHash);
        this.enrolledCourses = new ArrayList<>() ;
        this.progress = progress;
    }

    public ArrayList<Course> search (String word )
    {
        try
        {
            String content = new String(Files.readAllBytes(Paths.get("D:\\programming\\java\\lab7\\lab7_IJ\\courses.json")));
            JSONArray array = new JSONArray(content);

            ArrayList<Course> coursesOfSearch = new ArrayList<>();

            for ( int i = 0 ; i < array.length() ; i ++ )
            {
                JSONObject object = array.getJSONObject(i);
                if ( object.getString("title").toLowerCase().contains(word.toLowerCase()))
                {
                    coursesOfSearch.add(new Course(object.getString("courseId"), object.getString("title"), object.getString("description"), object.getInt("instructorId"))) ;
                }
            }
            if ( !coursesOfSearch.isEmpty() )
            {
                return coursesOfSearch ;
            }
            else
            {
                return null ;
            }
        }
        catch ( Exception e )
        {
            System.out.println( e ) ;
            return null ;
        }
    }
    /*
    public void enroll ( Course course ) throws IOException
    {
        enrolledCourses.add(course) ;
        UpdateProgressAndEnrolledCourses();

        course.AddStudent(this);
        Courses courses=new Courses("D:\\programming\\java\\lab7\\lab7_IJ\\courses.json");
        courses.load();
        courses.SaveToJsonCourses() ;
    }
    */
    /*
    public void UpdateProgressAndEnrolledCourses()
    {
        try
        {
            String content = new String(Files.readAllBytes(Paths.get("D:\\programming\\java\\lab7\\lab7_IJ\\users.json")));
            JSONArray data = new JSONArray(content);

            JSONObject jsonObj = new JSONObject(content);
            JSONArray students = jsonObj.getJSONArray("students");
            JSONArray instructors = jsonObj.getJSONArray("instructors");

            for ( int i = 0 ; i < data.length() ; i ++ )
            {
                JSONObject obj = students.getJSONObject(i);
                if (obj.getString("userId").equals(userId))
                {
                    obj.put("progress", progress ) ;
                    obj.put("enrolledCourses", enrolledCourses) ;

                    break ;
                }
            }
            Files.write(Paths.get("users.json"), data.toString().getBytes());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
     */

    public void enroll(Course course) throws IOException {
        // Initialize if null
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }

        enrolledCourses.add(course);

        // Remove this problematic call - we'll handle user updates differently
        // UpdateProgressAndEnrolledCourses();

        course.AddStudent(this);

        Courses courses = new Courses("D:\\programming\\java\\lab7\\lab7_IJ\\courses.json");
        courses.load();
        courses.SaveToJsonCourses();

        // Also update users.json
        updateUserInUsersFile();
    }

    private void updateUserInUsersFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("D:\\programming\\java\\lab7\\lab7_IJ\\users.json")));
            JSONObject jsonObj = new JSONObject(content); // This is correct - users.json is an object

            JSONArray students = jsonObj.getJSONArray("students");

            // Find and update the current student
            for (int i = 0; i < students.length(); i++) {
                JSONObject studentObj = students.getJSONObject(i);
                if (studentObj.getString("userId").equals(this.userId)) {
                    // Update progress
                    studentObj.put("progress", this.progress);

                    // Convert enrolledCourses to JSON array
                    JSONArray enrolledCoursesArray = new JSONArray();
                    for (Course course : this.enrolledCourses) {
                        JSONObject courseObj = new JSONObject();
                        courseObj.put("courseId", course.getCourseId());
                        courseObj.put("title", course.getTitle());
                        // Add other course properties as needed
                        enrolledCoursesArray.put(courseObj);
                    }
                    studentObj.put("enrolledCourses", enrolledCoursesArray);
                    break;
                }
            }

            // Write back to file
            Files.write(Paths.get("D:\\programming\\java\\lab7\\lab7_IJ\\users.json"), jsonObj.toString().getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}