import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileWriter;


public class SignUp {

    private ArrayList<Student> allStudents;
    private ArrayList<Instructor> allInstructors;
    private User user;
    
    


    public SignUp() {
        this.user = new User("D:\\programming\\java\\lab7\\lab7_IJ\\users.json");
        user.load();
        this.allStudents = user.getStudents() != null ? user.getStudents() : new ArrayList<>();
        this.allInstructors = user.getInstructors() != null ? user.getInstructors() : new ArrayList<>();
       
    }

    

    

    public void AddStudent(Student student) {
        
        allStudents.add(student);
       
    }
    public void AddInstructor(Instructor instructor) {
        
        allInstructors.add(instructor);
        
    }

    

    public boolean validateuserId(String userId) {
        for ( Student student : allStudents) {
            if (student.getUserId().equals(userId)) {
                return true;
            }
        }
       
        for ( Instructor instructor : allInstructors) {
            if (instructor.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }
    public int validateuserEmail(String email) {

        if (!email.contains("@") || !email.contains(".")) {
            return 1;
        }
        for (Student student : allStudents) {
            if (student.getEmail().equals(email)) {
                return 2;
            }
        }
        for (Instructor instructor : allInstructors) {
            if (instructor.getEmail().equals(email)) {
                return 2;
            }
        }
        return 0;
    }

     public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            
        
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
