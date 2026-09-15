package pkg3;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Bank Account Test Scenario ===\n");

        System.out.println("1. Account Creation:");
        bank_account account = bank_account.create_new_bank_account("123456", "John Doe");
        account.show_info();


        System.out.println("2. After Deposit:");
        double depositAmount = 1000.0;
        System.out.println("Deposit Amount: " + depositAmount + " EGP");
        account.deposit(depositAmount);
        account.show_info();

  
        System.out.println("3. After Withdrawal:");
        double withdrawAmount1 = 500.0;
        System.out.println("Withdrawal Amount: " + withdrawAmount1 + " EGP");
        account.withdraw(withdrawAmount1);
        account.show_info();

        System.out.println("5. Attempt to Withdraw More Than Balance:");
        double withdrawAmount2 = 600.0;
        System.out.println("Withdrawal Attempt: " + withdrawAmount2 + " EGP");
        account.withdraw(withdrawAmount2); 
        System.out.println();

       
        System.out.println("6. Final Balance Check:");
        account.show_info();
        
        
        System.out.println("=== Testing Edge Cases ===");
        System.out.println("Attempting negative deposit (-100.0):");
        account.deposit(-100.0);
    }
}