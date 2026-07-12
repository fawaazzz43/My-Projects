package pkg2;

import java.util.Arrays;

public class CustomSet
{
    int size ;
    int[] data ;

    public CustomSet ( int size , int[] data )
    {
        this.size = size ;
        this.data = new int[size] ;
        System.arraycopy ( data , 0 , this.data , 0 , size ) ;
    }

    public int contains ( int n )
    {
        for ( int i = 0 ; i < size ; i ++ )
        {
            if ( n == data[i] )
            {
                return 1 ;
            }
        }
        return 0 ;
    }

    public CustomSet union(CustomSet otherSet)
    {
        CustomSet temp = new CustomSet(this.size + otherSet.size, new int[this.size + otherSet.size]);
        int index  = 0 ;

        for ( int i = 0 ; i < otherSet.size ; i ++ )
        {
            temp.data[index++] = otherSet.data[i] ;
        }
        for ( int j = 0 ; j < this.size ; j ++ )
        {
            temp.data[index++] = this.data[j] ;
        }
        return temp ;
    }

    public CustomSet remove_dublicate ( )
    {
        CustomSet temp = new CustomSet ( this.size , new int [this.size] ) ;
        temp.data[0] =  this.data[0] ;
        int index  = 1 ;

        for ( int i = 1 ; i < size ; i ++ )
        {
            if ( temp.contains(data[i]) == 0 )
            {
                temp.data[index++] = data[i] ;
            }
        }

        CustomSet result = new CustomSet ( index, new int[index] ) ;
        System.arraycopy( temp.data , 0 ,result.data , 0 , index ) ;
        return result ;
    }

    public void sort ( )
    {
        Arrays.sort ( this.data , 0 , this.size ) ;
    }

    private static boolean isPrime(int n)
    {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(n); i += 2)
        {
            if (n % i == 0) return false;
        }
        return true;
    }

    public CustomSet getPrimes()
    {
        int primeCount = 0;
        for (int i = 0; i < this.size; i++)
        {
            if (isPrime(this.data[i]))
            {
                primeCount++;
            }
        }

        int[] primes = new int[primeCount];
        int index = 0;
        for (int i = 0; i < this.size; i++)
        {
            if (isPrime(this.data[i]))
            {
                primes[index++] = this.data[i];
            }
        }
        return new CustomSet (primes.length, primes);
    }


}

