package pkg2;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String str = input.nextLine();
        int l1=str.length();

        int[] numbers = new int[l1-l1/2];
        int j=0,m=0;

        for(int i = 0 ; i < l1 ; i++)
        {
            if(((i+1)<str.length()-1 && str.charAt(i+1)=='-'  ) || ( (i-1)>0 && str.charAt(i-1)=='-' ))
            {

                numbers[j]=Character.getNumericValue(str.charAt(i));
                j++;
            }

        }
        int l2=numbers.length;

        for(int k=0;k<l2;k++)
        {
            for(int c=0;c<l2;c++)
            {
                if(c==k)
                {
                    continue;
                }
                else if(numbers[k]==numbers[c])
                {
                    m++;
                }
                else
                {
                    m--;
                }

            }

        }


        if(m==(l2-1)*l2)
        {
            System.out.println("All Equal");
        }
        else if(m==(l2-1)*l2*-1)
        {
            System.out.println("All Different");

        }
        else
        {
            System.out.println(" Neither");
        }





    }
}

