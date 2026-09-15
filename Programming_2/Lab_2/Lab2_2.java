package pkg2;
import java.util.* ;

public class Lab2_2 
{ 
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in) ;

        System.out.print("enter the first set : ") ;
        String set1 = scan.nextLine() ;
        
        String[] arr1_str = set1.trim().split(",") ;
    
        int[] arr1 = new int[arr1_str.length] ; 
        for(  int i = 0 ; i < arr1.length ; i ++ )
        {
            arr1[i] = Integer.parseInt(arr1_str[i].trim()) ;
        }  

        System.out.print("enter the second set : ") ;
        String set2 = scan.nextLine() ;
        
        String[] arr2_str = set2.trim().split(",") ;
    
        int[] arr2 = new int[arr2_str.length] ; 
        for(  int i = 0 ; i < arr2.length ; i ++ )
        {
            arr2[i] = Integer.parseInt(arr2_str[i].trim()) ;
        } 
        
        CustomSet s1 = new CustomSet ( arr1.length , arr1 ) ;
        CustomSet s2 = new CustomSet ( arr2.length , arr2 ) ;
        
        CustomSet s3 = s1.union(s2) ;
        CustomSet s3_new = s3.remove_dublicate() ;
        s3_new.sort() ;

        System.out.println( Arrays.toString(s3_new.data) );
        System.out.println( Arrays.toString(s3_new.getPrimes().data) );
    }  
}
