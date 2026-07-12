import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public  static void main(String[] args) throws IOException
    {

        Scanner scan = new Scanner(System.in);
        String mode = scan.next();
        String filename = "test2_invalid.csv";
        Modes checker =  Factory.getmode(mode,filename);
           checker.check() ;
           if(unitCheck.isCheckValidity()==true)
               System.out.println("Valid");
    }
}