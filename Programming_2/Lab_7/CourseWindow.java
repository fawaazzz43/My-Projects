import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CourseWindow extends JFrame {

    // Panel for dynamic lesson checkboxes
    private JPanel lessonsPanel;

    // Text areas for info
    private JTextArea jTextAreaTitle;
    private JTextArea jTextAreaCourseId;
    private JTextArea jTextAreaInstructorId;
    private JTextArea jTextAreaDescription;

    private JButton enrollButton; // Button to enroll

    ArrayList<Lesson> lessons;
    static Course course;
    static Student student;

    // Constructor
    public CourseWindow(Course course, Student student) {
        this.course = course;
        this.student = student;
        this.lessons = course.getLessons();
        initComponents();
        addLessonCheckboxes(lessons);
        updateEnrollButton();
    }

    // Default constructor
    public CourseWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel6 = new JLabel();
        jLabel7 = new JLabel();

        lessonsPanel = new JPanel();
        lessonsPanel.setLayout(new BoxLayout(lessonsPanel, BoxLayout.Y_AXIS));

        // Title TextArea
        jTextAreaTitle = createTextArea(course != null ? course.getTitle() : "", 20);

        // Course ID TextArea
        jTextAreaCourseId = createTextArea(course != null ? String.valueOf(course.getCourseId()) : "", 16);

        // Instructor ID TextArea
        jTextAreaInstructorId = createTextArea(course != null ? String.valueOf(course.getInstructorId()) : "", 16);

        // Description TextArea
        jTextAreaDescription = createTextArea(course != null ? course.getDescription() : "", 16);
        JScrollPane scrollPaneDescription = new JScrollPane(jTextAreaDescription);
        scrollPaneDescription.setPreferredSize(new Dimension(300, 100));

        // Enroll Button
        enrollButton = new JButton();
        enrollButton.setFont(new Font("Cambria", Font.BOLD, 20));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setText("Lessons Of Course");
        //jLabel7.setText("Info");

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextAreaTitle)
                                        .addComponent(jTextAreaCourseId)
                                        .addComponent(scrollPaneDescription)
                                        .addComponent(jTextAreaInstructorId)
                                        .addComponent(enrollButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(50)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(lessonsPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(30, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(jLabel7)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(jTextAreaTitle, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(jTextAreaCourseId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(scrollPaneDescription, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(jTextAreaInstructorId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(10)
                                .addComponent(enrollButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50)
                                .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(lessonsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(180)
                                .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }

    // Helper to create non-editable JTextAreas for info
    private JTextArea createTextArea(String text, int fontSize) {
        JTextArea area = new JTextArea(text);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setFont(new Font("Cambria", Font.BOLD, fontSize));
        area.setOpaque(false); // Looks like JLabel
        area.setBorder(null);
        return area;
    }

    // Method to add dynamic checkboxes to lessonsPanel
    private void addLessonCheckboxes(ArrayList<Lesson> lessons) {
        lessonsPanel.removeAll(); // Clear old checkboxes
        for (Lesson lesson : lessons) {
            JCheckBox cb = new JCheckBox(String.valueOf(lesson));
            lessonsPanel.add(cb);
        }
        lessonsPanel.revalidate();
        lessonsPanel.repaint();
        lessonsPanel.setVisible(true);
    }

    // Update enroll button text and behavior
    private void updateEnrollButton() {
        if (student != null && student.enrolledCourses.contains(course)) {
            enrollButton.setText("ENROLLED");
            enrollButton.setEnabled(false);
        } else {
            enrollButton.setText("ENROLL");
            enrollButton.setEnabled(true);
            enrollButton.addActionListener(e -> {
                try {
                    student.enroll(course);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                enrollButton.setText("ENROLLED");
                enrollButton.setEnabled(false);
            });
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new CourseWindow(course, student).setVisible(true));
    }

    // Variables
    private JLabel jLabel6;
    private JLabel jLabel7;
}
