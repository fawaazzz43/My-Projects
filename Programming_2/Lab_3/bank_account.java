package pkg3;

public class bank_account
{
    static final Double initial_value = 0.0 ;

    String account_number ;
    String account_name ;
    Double account_value ;

    public  bank_account ( String account_number , String account_name ,  Double account_value )
    {
        this.account_number = account_number ;
        this.account_name = account_name ;
        this.account_value = account_value ;
    }

    public static bank_account create_new_bank_account(String num, String name)
    {
        return new bank_account( num , name , initial_value ) ;
    }

    public void deposit ( Double num )
    {
        if ( num <= 0 )
        {
            System.out.println( "Error: deposit amount must be greater than 0" );
        }
        else
        {
            account_value += num ;
        }
    }

    public void withdraw ( Double num )
    {

        if (  num <= 0 )
        {
            System.out.println( "Error: withdraw amount must be greater than 0" );
        }
        else
        {

            if ( num < account_value )
            {
                account_value -= num ;
            }
            else
            {
               System.out.println( "Insufficient funds. Withdrawal failed" );
            }

        }

    }

    public void show_info ()
    {
        System.out.println( "Account number: " + account_number );
        System.out.println( "Account name: " + account_name );
        System.out.println( "Account value: " + account_value );
        System.out.println();
    }
}

