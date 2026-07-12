package lab5;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;



public class StudentDatabase {
    private ArrayList<Student> records;
    private String filename;

    public StudentDatabase(String filename)
    {
        this.filename=filename;
        this.records=new ArrayList<Student>();
    }


    public void readFromFile() throws Exception
    {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found!");
            return;
        }
        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            records.clear();
            while (line != null) {
                
                records.add(createRecordFrom(line));
                
                line = br.readLine();
            }
            br.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }

    }


    public Student SearchStudent(int ID)
    {
        
        for(int i=0;i<records.size();i++)
        {
            if((records.get(i)).getStudentID()==ID)
            {
                return records.get(i);
            }
        }
        return null;
    }

    
     public Student createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length == 6) {

            Student student = new Student(Integer.parseInt(parts[0]),parts[1],Integer.parseInt(parts[2]),parts[3],parts[4],Float.parseFloat(parts[5]));
            return student;
        }
        return null;
    }

    public void  ViewStudents()
    {
         for(int i=0;i<records.size();i++)
        {
            System.out.println(records.get(i).lineRepresentationOfStudent());
        }
    }


    public boolean DeleteStudent(int ID)
    {
        
        for(int i=0;i<records.size();i++)
        {
            if((records.get(i)).getStudentID()==ID)
            {
                try {
                    this.records.remove(records.get(i));
                    saveToFile();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean  AddStudent(Student student) 
    {
       if(SearchStudent(student.getStudentID())==null)
        {
            
            try {
                this.records.add(student);
                saveToFile();
            } catch (Exception e) {
                return false;
            }
            return true;
            
        }
       return false;
    }

     public void saveToFile() throws Exception
    {
        FileWriter file = new FileWriter(filename);
        for(int i=0;i<records.size();i++)
        {
            file.write(( records.get(i)).lineRepresentationOfStudent()+"\n");
        }
        file.close();
    }
    

    public void  UpdateStudent(String choise,int UpdatedValue,int ID)
    {
       
        if(choise.equalsIgnoreCase("id"))
        {
         SearchStudent(ID).setStudentID(UpdatedValue);
        }
        
        else if(choise.equalsIgnoreCase("gpa"))
        {
            SearchStudent(ID).setGPA(UpdatedValue);
        }

        else if(choise.equalsIgnoreCase("age"))
        {
            SearchStudent(ID).setStudentID(UpdatedValue);
        }
        
    }
    
    public ArrayList<Student> ReturnAllStudents() {
        return records;
    }
}

