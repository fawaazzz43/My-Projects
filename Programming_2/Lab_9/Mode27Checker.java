import java.io.IOException;
import java.util.ArrayList;

public class Mode27Checker implements Modes{
    private loadFromFile loader ;
    public static  int i =0;

    public Mode27Checker(String filename) {
        this.loader = new loadFromFile();

        try {
            this.loader.getContent(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void check() {
        ArrayList<Thread> threads = new ArrayList<>();
        boolean flag = true ;

        unitCheck check = new unitCheck();

        for ( i = 0 ; i< 9 ; i ++ )
        {
            final  int index = i;
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    if(!check.isValid(loader.getRow(index)) )
                    {
                        check .printAllsudokuDublicate(0,index) ;
                    }
                }
            }));
        }

        for ( i = 0 ; i< 9 ; i ++ )
        {
            final  int index = i;
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    if(!check.isValid(loader.getColumn(index)) )
                    {
                        check .printAllsudokuDublicate(1,index) ;
                    }
                }
            }));
        }

        for ( i = 0 ; i< 9 ; i ++ )
        {
            final  int index = i;
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    if(!check.isValid(loader.getBox(index)) )
                    {
                        check .printAllsudokuDublicate(2,index) ;
                    }
                }
            }));
        }

        for(Thread thread : threads)
        { thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }
}