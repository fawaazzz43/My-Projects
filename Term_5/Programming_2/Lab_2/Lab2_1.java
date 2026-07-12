package pkg2 ;
import java.util.Scanner ;

public class Lab2_1 
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner (System.in) ;
        
        int [] nums = new int[3] ;  
        
        System.out.print("enter the numbers : ");
        String sen = scan.nextLine() ;
        
        nums[0] = Integer.parseInt(sen.trim().split("-")[0]) ;
        nums[1] = Integer.parseInt(sen.trim().split("-")[1]) ;
        nums[2] = Integer.parseInt(sen.trim().split("-")[2]) ;
        
        if ( nums[0] == nums[1] && nums[1] == nums[2] )
        {
            System.out.println("all equal") ;
        }
        else
        {
            if ( nums[0] == nums[1] || nums[0] == nums[2] || nums[1] == nums[2] )
            {
                System.out.println("neither") ;
            }
            else
            {
                System.out.println("all different") ;
            }
        }
    }
}
