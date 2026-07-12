package pkg2;

import java.util.Arrays;

public class Invoice
{
    int invoice_number ;
    String customer_name ;
    double[] prices ;

    public Invoice( int invoice_number , String customer_name , double[] prices )
    {
        this.invoice_number = invoice_number ;
        this.customer_name = customer_name ;
        this.prices = prices ;
    }

    public void report()
    {
        System.out.println("account Number : " + invoice_number );
        System.out.println("Name : " + customer_name );
        System.out.println("cost : " + calculate_invoice() );
        System.out.println("total cost after discount : " + cost_after_discount() );
        System.out.println("###########");
    }

    public double calculate_invoice ()
    {
        double sum = 0 ;
        for ( int i = 0; i < prices.length; i++ )
        {
            sum += prices[i] ;
        }
        return sum ;
    }

    public double cost_after_discount ()
    {
        double n = calculate_invoice() ;
        if ( n > 10000 )
        {
            return n-n*0.08 ;
        }
        else if ( n > 8000 )
        {
            return n-n*0.06 ;
        }
        else if ( n > 5000 )
        {
            return n-n*0.04 ;
        }
        else if ( n > 1000 )
        {
            return n-n*0.01 ;
        }
        else
        {
            return n ;
        }
    }


}

