import java.io.IOException;

public class Mode3Checker implements Modes {
    
    private loadFromFile loader = new loadFromFile();

    private final String filename ;
   
    public Mode3Checker(String filename) throws IOException {
         this.filename = filename;
         loader.getContent(filename) ;
        
    }
    
    @Override
    public void check()  {


        // Create three threads for rows, columns, and boxes
        Thread rowThread = new Thread(new RowChecker());
        Thread columnThread = new Thread(new ColumnChecker());
        Thread boxThread = new Thread(new BoxChecker());

        // Main thread waits for all threads to complete
        rowThread.start();
        try {
            rowThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        columnThread.start();
        try {
            columnThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boxThread.start();
        try {
            boxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    // Thread for checking all rows
    private class RowChecker implements Runnable {
        @Override
        public void run() {
            unitCheck check = new unitCheck();
            for (int i = 0; i < 9; i++) {
                if (!check.isValid(loader.getRow(i))) {
                    check.printAllsudokuDublicate(0, i);

                }
            }
        }
    }
    
    // Thread for checking all columns
    private class ColumnChecker implements Runnable {
        @Override
        public void run() {
            unitCheck check = new unitCheck();
            for (int i = 0; i < 9; i++) {
                if (!check.isValid(loader.getColumn(i))) {
                    check.printAllsudokuDublicate(1, i);

                }
            }
        }
    }
    
    // Thread for checking all boxes
    private class BoxChecker implements Runnable {
        @Override
        public void run() {
            unitCheck check = new unitCheck();
            for (int i = 0; i < 9; i++) {
                if (!check.isValid(loader.getBox(i))) {
                    check.printAllsudokuDublicate(2, i);

                }
            }
        }
    }
}
