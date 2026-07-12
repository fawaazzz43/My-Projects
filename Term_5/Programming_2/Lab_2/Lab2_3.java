package pkg2;
import java.util.* ;

public class Lab2_3 {
    public static void main() {
        Scanner scan = new Scanner(System.in);

        int account_num = 2;
        Invoice[] accounts = new Invoice[account_num];

        for (int i = 0; i < account_num; i++)
        {
            int num;
            String name;
            int num_of_elements;

            System.out.print("Enter Invoice number : ");
            num = scan.nextInt();
            scan.nextLine();

            System.out.print("Enter customer name : ");
            name = scan.nextLine();

            System.out.print("Enter number of elements : ");
            num_of_elements = scan.nextInt();
            scan.nextLine();

            double[] arr = new double[num_of_elements];
            String s;

            System.out.print("Enter the prices : ");
            s = scan.nextLine();

            String[] arr_str = s.trim().split(",");

            for (int j = 0; j < arr_str.length; j++) {
                arr[j] = Double.parseDouble(arr_str[j]);
            }

            Invoice v = new Invoice(num, name, arr);
            accounts[i] = v;

            System.out.println("###########");
        }

        for (int k = 0; k < account_num; k++) {
            accounts[k].report();
        }
    }
}
