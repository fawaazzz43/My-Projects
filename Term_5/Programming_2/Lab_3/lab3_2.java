package pkg3;

import java.util.Scanner;

public class lab3_2
{
    public static void main(String args[])
    {
        Scanner scan = new Scanner (System.in) ;

        System.out.println("Enter Account Number");
        int account_number = scan.nextInt() ;
        scan.nextLine();

        System.out.println("Enter Account name");
        String name = scan.nextLine() ;

        bank_account B = bank_account.create_new_bank_account ( Integer.toString(account_number), name ) ;

        while (true)
        {
            System.out.println("What you want ?\n1.deposite\n2.withdraw\n3.Show my account information\n4.Exit");
            int choice = scan.nextInt() ;
            switch(choice)
            {
                case 1:
                    System.out.println("Enter amount to deposite");
                    double amount = scan.nextDouble() ;
                    B.deposit(amount) ;
                    break;

                case 2:
                    System.out.println("Enter amount to withdraw");
                    double withdraw = scan.nextDouble() ;
                    B.withdraw(withdraw) ;
                    break;
                case 3:
                    B.show_info();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
