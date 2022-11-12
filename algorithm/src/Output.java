import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
/**
 * This class contains one methods to write a result in txt file
 *
 * @author SaharEbrahimi
 * @version 1.0.0
 * @since 27.6.2018
 */
class Output
{

    void write(int max, int[] color) {
        try {
            File file = new File("out.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Vertices"+"  "+"Color"+"\n");
            for(int u=0 ; u<max ; u++){
                color[u]=color[u]+1;
                fileWriter.write(u+1+"           "+color[u]+"\n");}
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
