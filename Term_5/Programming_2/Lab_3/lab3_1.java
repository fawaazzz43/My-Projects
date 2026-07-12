package pkg3;

import java.util.Scanner;

public class lab3_1
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner (System.in) ;

        System.out.println( "enter the state\n0 if you don't work\n1 if you work" );
        Integer state = scan.nextInt() ;
        scan.nextLine();

        if ( state == 0 )
        {
            System.out.println("enter the name of the account");
            String name = scan.nextLine() ;
            person p = new person( name , null , false ,null ) ;
            System.out.println( "your account is created successfully");
        }
        else if ( state == 1 )
        {
            System.out.println("enter the name of the account");
            String name = scan.nextLine() ;
            System.out.println("enter the salary") ;
            Double salary = scan.nextDouble() ;

            System.out.println( "enter the expenses") ;
            Double expenses = scan.nextDouble() ;

            person p = new person( name , salary , true , expenses ) ;
            Double s = p.net_income() ;
            if ( s == 0 )
            {
                System.out.println("invalid input");
            }
            else if ( s == 1000.0 )
            {
                System.out.println("your account is created successfully\nbut maybe there is an error\nthe salary is (a default one) " + s );
            }
            else
            {
                System.out.println("your account is created successfully\nyour salary after expenses is " + s );
            }
        }
        else
        {
            System.out.println("invalid input") ;
        }
    }
}
