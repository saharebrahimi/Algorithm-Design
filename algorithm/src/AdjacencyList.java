import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains some methods for adding vertices to linked list
 * printing  vertices and it adjacency and coloring vertices
 *
 *
 * @author SaharEbrahimi
 * @version 1.0.0
 * @since 27.6.2018
 */
class AdjacencyList {
    private static Node adjLists[];
    private static final int MAX_RANGE = 100000000;
    private static ArrayList<information> numberOfAdj;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static ArrayList<Integer> temp;
    private Output output=new Output();

    AdjacencyList(int vertices) throws IOException {
        adjLists = new Node[vertices + 1];
        numberOfAdj = new ArrayList<>();
        temp = new ArrayList<>();
        for (int i = 1; i <= vertices; i++) {
            adjLists[i] = new Node();
            adjLists[i].setNum(i);

        }

    }

    /**
     * This method takes two number as a number of destination and source of the vertices
     * and add it to array of linked list
     *
     * @param src,dest: the given vertices number
     */
    private void add(int src, int dest) {
        Node data = new Node();
        data.setNum(dest);
        Node ptr;
        if (adjLists[src].getLink() == null) {
            adjLists[src].setLink(data);
        } else {
            ptr = adjLists[src].getLink();
            while (ptr.getLink() != null) {
                ptr = ptr.getLink();
            }
            ptr.setLink(data);
        }

    }

    void addEdge(int src, int dest) {
        add(src, dest);

    }

     void numberOfAdjacency() {

         information in =new information();
         in.setIndex(-1);
         in.setNum(-1);
         temp.add(-1);
         numberOfAdj.add(in);

        for (int i = 1; i < adjLists.length; i++) {
            int num=0;
            Node p = adjLists[i];
            ArrayList<Integer> t=new ArrayList<>();
            while (p.getLink() != null) {
                num++;

                p = p.getLink();
                t.add(p.getNum());


            }
            graph.add(t);

            information in2 =new information();
            in2.setIndex(i);
            in2.setNum(num);
            temp.add(num);
            numberOfAdj.add(in2);

        }

    }



         /*
          * This method do count sort
          *
          */
        private static void sort()
        {
            int N = numberOfAdj.size();
            if (N == 0)
                return;
            int max = numberOfAdj.get(0).getNum(), min = numberOfAdj.get(0).getNum();

            for (int i = 1; i < N; i++)

            {

                if (numberOfAdj.get(i).getNum() > max)
                    max = numberOfAdj.get(i).getNum();

                if (numberOfAdj.get(i).getNum()< min)
                    min = numberOfAdj.get(i).getNum();

            }

            int range = max - min + 1;
            if (range > MAX_RANGE)

            {
                System.out.println("\nError:range is too large for sort");
                return;

            }

            int[] count = new int[range];

            for (information aNumberOfAdj : numberOfAdj) count[aNumberOfAdj.getNum() - min]++;

            for (int i = 1; i < range; i++)

                count[i] += count[i - 1];



            int j = 0;
            int cnt;

            for (int i = 0; i < range; i++) {

                while (j < count[i]) {
                    cnt=j++;
                    numberOfAdj.get(cnt).setNum(i + min);
                    numberOfAdj.get(cnt).setIndex(temp.indexOf(i + min));
                    temp.set(temp.indexOf(i + min),-2);


                }
            }

        }

    /*
     * This method print vertices and its adjacency
     *(just for checking)
     *
     */
    void print() {
        for (int i = 1; i < adjLists.length; i++) {
        Node p = adjLists[i];
            System.out.print(i+"-->");
        if (p == null)
            System.out.print("there is no adj ");
            if (p != null) {
                while (p.getLink() != null) {
                   System.out.print(p.getLink().getNum()+" ");
                   p = p.getLink();

               }
            }
            System.out.println();

    }

    }


    /**
     * This method used the sort list of adjacency
     * This method has array which shows vertices with -1 value at first
     * after coloring first vertices all of the other vertices which are not adjacency of the vertices which are colored can
     * have the same color
     * this process continue till all the vertices  have been colored
     *
     */
    void greedyColoringWelshPowell(int max) {
        sort();
        ArrayList<Integer> num=new ArrayList<>();
        ArrayList<Integer> check=new ArrayList<>();
        int vertices[] = new int[max];
        Arrays.fill(vertices, -1);
        int color=0;

        for (int u = 1; u < max+1; u++)
        {
            if(vertices[numberOfAdj.get(max+1-u).getIndex()-1]==-1){

            vertices[(numberOfAdj.get(max).getIndex())-1]  = 0;
            Node p = adjLists[numberOfAdj.get(max+1-u).getIndex()];
                for(int w=0 ; w<graph.get(numberOfAdj.get(max+1-u).getIndex()-1).size();w++)
                    check.add(graph.get(numberOfAdj.get(max+1-u).getIndex()-1).get(w));

            while (p.getLink()!=null)
            {
                int i = p.getLink().getNum();
                num.add(i);
                p=p.getLink();

            }

            if(vertices[numberOfAdj.get(max+1-u).getIndex()-1]==-1 ){
                vertices[numberOfAdj.get(max+1-u).getIndex()-1] = color;
                for(int r=1;r<max+1 ; r++)
                    if(!num.contains(r)&& r!=numberOfAdj.get(max+1-u).getIndex() && vertices[r-1]==-1 &&  !check.contains(r)){
                        vertices[r-1]=color;
                        for(int w=0 ; w<graph.get(r-1).size();w++)
                        check.add(graph.get(r-1).get(w));
                }

            }
            else
                for(int r=1;r<max+1 ; r++)
                    if(!num.contains(r) && r!=numberOfAdj.get(max+1-u).getIndex() && vertices[r-1]==-1 && !check.contains(r)) {
                        vertices[r - 1] = color;
                        for(int w=0 ; w<graph.get(r-1).size();w++)
                            check.add(graph.get(r-1).get(w));
                    }

            color++;
            num.clear();
            check.clear();


        }}

         output.write(max,vertices);

    }

    /**
     * This method  has array which shows vertices with -1 value at first
     * it also have available array to store the available colors
     * all colors are available at first
     * then we flag adjacent vertices colors as unavailable
     * and then we find the color with lowest number and color specific vertices
     *
     *
     */
    void greedyColoring(int max) {
        int vertices[] = new int[max];
        Arrays.fill(vertices, -1);
        boolean available[] = new boolean[max];
        vertices[0]  = 1;
        Arrays.fill(available, true);
        for (int u = 1; u < max+1; u++)
        {
            int color;
            Node p = adjLists[u];
            while (p.getLink()!=null)
            {
                int i = p.getLink().getNum();
                if (vertices[i-1] != -1)
                    available[vertices[i-1]] = false;
                p=p.getLink();

            }
            for (color = 0; color < max; color++){
                if (available[color])
                    break;
            }


            vertices[u-1] = color;
            Arrays.fill(available, true);
        }

        output.write(max,vertices);

    }

}


