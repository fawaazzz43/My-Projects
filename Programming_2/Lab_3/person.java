package pkg3;

public class person
{
    Double default_salary = 1000.0 ;

    String name ;
    Double salary ;
    Boolean work ;
    Double expenses ;

    public  person ( String name, Double salary, Boolean work, Double expenses )
    {
        if (work == true)
        {
            if ( salary >= 0 )
            {
                if ( expenses >= 0 )
                {
                    this.name = name;
                    this.salary = salary ;
                    this.work = work ;
                    this.expenses = expenses ;
                }
                else
                {
                    this.name = name;
                    this.salary = salary ;
                    this.work = work ;
                    this.expenses = expenses ;
                }
            }
            else
            {
                this.name = name;
                this.salary = 0.0 ;
                this.work = work ;
                this.expenses = expenses ;
            }

        }
        else if ( work == false)
        {
            this.name = name ;
            this.salary = 0.0 ;
            this.expenses = 0.0 ;
        }
    }

    public double net_income ()
    {
        if ( this.work == true && this.expenses >= 0 )
        {
            Double net_income = salary - expenses ;
            if ( net_income > 0 )
            {
                return net_income ;
            }
            else
            {
                return default_salary ;
            }
        }
        return 0.0 ;
    }
}


