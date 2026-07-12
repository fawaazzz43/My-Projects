package pkg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Lab2_4
{
    public static Invoice[] readInvoiceDetailsFromFile(String file_name) {
        try {
            File f = new File(file_name);
            Scanner scan = new Scanner(f);

            Invoice[] arr = new Invoice[Integer.parseInt(scan.nextLine())];
            int i = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.trim().split(",");

                int num = Integer.parseInt(parts[0]);
                String name = parts[1];
                double[] p = new double[Integer.parseInt(parts[2])];

                for (int j = 0; j < p.length; j++) {
                    p[j] = Double.parseDouble(parts[3 + j]);
                }

                arr[i++] = new Invoice(num, name, p);
            }
            return arr;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file_name);
            return new Invoice[0];
        }
    }

    public static void main(String[] args)
    {
        Invoice[] arr_file = readInvoiceDetailsFromFile("D:\\programming\\java\\lab2\\invoice");

        Arrays.sort(arr_file, Comparator.comparingDouble(Invoice::cost_after_discount).reversed());

        for ( int k = 0 ; k < arr_file.length ; k++ )
        {
            arr_file[k].report();
        }

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

            try
            {
                FileWriter fw = new FileWriter("D:\\programming\\java\\lab2\\invoice" );

                for ( int l = 0 ; l < accounts.length ; l++ )
                {
                    fw.write(String.valueOf(accounts[l].invoice_number) + "," + accounts[l].customer_name );
                    for ( int p = 0 ; p < accounts[l].prices.length ; p++ )
                    {
                        fw.write("," + accounts[l].prices[p]);
                    }
                }
                fw.write("\n");
            }
            catch (IOException e)
            {
                System.out.println("File not found: ");
            }

            System.out.println("###########");
        }



    }
}
