/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mahmoud-elzankalony
 */
public class Instructor{
    private String userId ;
    private String role ;
    private String username ;
    private String email ;
    private String passwordHash ;

    ArrayList<Course> createdCourses = new ArrayList<>();
    Courses courses= new Courses("D:\\programming\\java\\lab7\\lab7_IJ\\courses.json");

    public Instructor(String userId, String role, String username, String email, String passwordHash) throws IOException
    {
        this.userId = userId;
        this.role = role;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        courses.load();
    }

    public void Createcourse(String courseId, String title, String description, int instructorId) throws IOException
    {
        Course course = new Course(courseId,title,description,instructorId);
        courses.addCourse(course);
        courses.SaveToJsonCourses();
        courses.load();
    }

    public void Editcourse(Course course,int courseId, String title, String description, int instructorId) throws IOException
    {
        if(courseId != 0)
            course.setCourseId(String.valueOf(courseId));
        if(title != null)
            course.setTitle(title);
        if(description != null)
            course.setDescription(description);
        if(instructorId != 0)
            course.setInstructorId(instructorId);
        courses.SaveToJsonCourses();
        courses.load();
    }

    public void deleteCourse(int courseId) throws IOException
    {
        for(Course course : courses.getCourses()){
            if(course.getCourseId().equals(courseId))
                courses.deleteCourse(course);
            break;
        }
        courses.SaveToJsonCourses();
        courses.load();
    }

    public void Createlesson(Course course,int lessonId, String title, String content) throws IOException
    {
        Lesson lesson = new Lesson(lessonId,title,content);
        course.getLessons().add(lesson);
        courses.SaveToJsonCourses();
        courses.load();
    }

    public void Editlesson(Course course,Lesson lesson,int lessonId, String title, String content) throws IOException
    {
        if(lessonId != 0)
            lesson.setLessonId(lessonId);
        if(title != null)
            lesson.setTitle(title);
        if(content != null)
            lesson.setContent(content);
        courses.SaveToJsonCourses();
        courses.load();
    }

    public void Deletelesson(Course course,int lessonId) throws IOException
    {

        for(Lesson lesson : course.getLessons()){
            if(lesson.getLessonId()==lessonId)
                course.getLessons().remove(lesson);
            break;
        }
        courses.SaveToJsonCourses();
        courses.load();
    }

    public ArrayList<Student> Viewenrolledstudents(Course course)
    {
        return course.getStudentsIncourse();
    }

    public Object getUserId()
    {
        return userId;
    }

    public Object getEmail()
    {
        return email;
    }
}
