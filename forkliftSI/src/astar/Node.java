package astar;

/**
 * Created by Tomasz on 2015-06-05.
 */
public class Node implements Comparable<Node> {

    private int posX;
    private int posY;
    private int cost;
    private int G;
    private int H;

    private boolean isPassable;
    private Node parent;

    public Node(int posX, int posY, boolean isPassable) {
        this.posX = posX;
        this.posY = posY;
        this.isPassable = isPassable;
        this.H = 0;
        this.G = 0;
        this.cost = 1;
    }

    public int getPosX() {
        return posX;
    }

    public Node setPosX(int posX) {
        this.posX = posX;
        return this;
    }

    public int getPosY() {
        return posY;
    }

    public Node setPosY(int posY) {
        this.posY = posY;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public Node setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public Node setIsPassable(boolean isPassable) {
        this.isPassable = isPassable;
        return this;
    }

    public Node getParent() {
        return parent;
    }

    public Node setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    public String toString() {
        return "[Node x:" + getPosX() + " y:" + getPosY() + " G:"+G+" H:"+H+"]";
    }

    public int getG() {
        return G;
    }

    public Node setG(int g) {
        G = g;
        return this;
    }

    public int getF() {
        return G + H;
    }

    public int getH() {
        return H;
    }

    public Node setH(int h) {
        H = h;
        return this;
    }

    @Override
    public int compareTo(Node o) {
        return this.getF() - o.getF();
    }
}
