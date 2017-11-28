
package qatest_1;


import java.io.FileReader;
import java.util.Arrays;
import au.com.bytecode.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QATest_1 {

    public static void main(String[] args) throws IOException {
        
        Trading process = new Trading();
        process.oneMonse();
           
    }   
}
