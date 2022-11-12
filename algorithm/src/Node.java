/**
 * This class is essential for implementing  adjacency list
 *
 * @author SaharEbrahimi
 * @version 1.0.0
 * @since 27.6.2018
 */
class Node {
    private int num;
    private Node link;


    Node() {
        link = null;
        num = 0;

    }


    void setNum(int num) {
        this.num = num;
    }

    int getNum() {
        return num;
    }

    void setLink(Node n) {
        link = n;
    }

    Node getLink() {
        return link;
    }




}
