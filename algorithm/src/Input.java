import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains 2 methods
 * one of them read fil from specific address
 * and the other doing doing some action on input file to pass it to other class
 *
 * @author SaharEbrahimi
 * @version 1.0.0
 * @since 27.6.2018
 */
class Input {
    private BufferedReader br = null;
    private ArrayList<String> readFromFile = new ArrayList<>();
    private int max;

    /**
     * This method takes a string address as a file address
     * and read file contents
     *
     * @param address: the given address file
     */
    void csvReader(String address) {

        try {
            br = new BufferedReader(new FileReader(address));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String line;
            while ((line = br.readLine()) != null) {
                String[] graph = line.split(",");
                Collections.addAll(readFromFile, graph);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method find number of vertices and
     * add the vertices to adjacency class
     *
     */

    void toAdjacencyList(int algorithm) throws IOException {
        for (int i = 0; i < readFromFile.size() - 1; i = i + 2) {
            if (max < Integer.parseInt(readFromFile.get(i)))
                max = Integer.parseInt(readFromFile.get(i));
        }

        AdjacencyList adjacencyList = new AdjacencyList(max);
        for (int i = 0; i < readFromFile.size() - 1; i = i + 2) {
            adjacencyList.addEdge(Integer.parseInt(readFromFile.get(i)), Integer.parseInt((readFromFile.get(i + 1))));
        }
       // adjacencyList.print();
        adjacencyList.numberOfAdjacency();
        if(algorithm==1)
        adjacencyList.greedyColoring(max);
        else
            adjacencyList.greedyColoringWelshPowell(max);

    }


}
