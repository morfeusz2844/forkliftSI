package astar;

import model.Forklift;
import model.Warehouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Astar {

    private Warehouse warehouse;
    private Forklift forklift;
    private Node[][] passableMap;


    public Astar(Warehouse warehouse, Forklift forklift) {
        this.warehouse = warehouse;
        this.forklift = forklift;
        this.passableMap = preparePassableMap(this.warehouse);
    }

    public void findPath(int startX, int startY, int destinationX, int destinationY) {

       Comparator<Node> nodeComparator = (o1, o2) -> o1.getF() - o2.getF();

        ArrayList<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        Node destinationNode = passableMap[destinationX][destinationY];
        Node currentNode = passableMap[startX][startY];
        currentNode.setH(Manhattan(currentNode, destinationNode));
        // dodajemy punkt pocz¹tkowy do listy otwartych

        openList.add(currentNode);

        // dodajemy punkty s¹siaduj¹ce, przez które mo¿na przejœæ, do listy otwartych

        Node tempNode;

        while(!openList.isEmpty() && !closedList.contains(destinationNode)) {

            tempNode = passableMap[currentNode.getPosX() - 1][currentNode.getPosY()];
            if (tempNode.isPassable() && !closedList.contains(tempNode))
                if (openList.contains(tempNode)) {

                    if (tempNode.getG() > currentNode.getG()+tempNode.getCost()){
                        tempNode.setG(currentNode.getG()+tempNode.getCost()).setParent(currentNode);
                    }

                } else {
                    openList.add(tempNode.setParent(currentNode).setG(currentNode.getG() + tempNode.getCost()).setH(Manhattan(tempNode, destinationNode)));
                }
            tempNode = passableMap[currentNode.getPosX() + 1][currentNode.getPosY()];
            if (tempNode.isPassable() && !closedList.contains(tempNode))
                if (openList.contains(tempNode)) {
                    if (tempNode.getG() > currentNode.getG()+tempNode.getCost()){
                        tempNode.setG(currentNode.getG()+tempNode.getCost()).setParent(currentNode);
                    }
                } else {
                    openList.add(tempNode.setParent(currentNode).setG(currentNode.getG() + tempNode.getCost()).setH(Manhattan(tempNode, destinationNode)));
                }
            tempNode = passableMap[currentNode.getPosX()][currentNode.getPosY() - 1];
            if (tempNode.isPassable() && !closedList.contains(tempNode))
                if (openList.contains(tempNode)) {
                    if (tempNode.getG() > currentNode.getG()+tempNode.getCost()){
                        tempNode.setG(currentNode.getG()+tempNode.getCost()).setParent(currentNode);
                    }
                } else {
                    openList.add(tempNode.setParent(currentNode).setG(currentNode.getG() + tempNode.getCost()).setH(Manhattan(tempNode, destinationNode)));
                }
            tempNode = passableMap[currentNode.getPosX()][currentNode.getPosY() + 1];
            if (tempNode.isPassable() && !closedList.contains(tempNode))
                if (openList.contains(tempNode)) {
                    if (tempNode.getG() > currentNode.getG()+tempNode.getCost()){
                        tempNode.setG(currentNode.getG()+tempNode.getCost()).setParent(currentNode);
                    }
                } else {
                    openList.add(tempNode.setParent(currentNode).setG(currentNode.getG() + tempNode.getCost()).setH(Manhattan(tempNode, destinationNode)));
                }
            openList.remove(currentNode);
            closedList.add(currentNode);

            openList.sort(nodeComparator);

            currentNode = openList.get(0);

            System.out.println(closedList);
        }

        System.out.println(openList);
        System.out.println(closedList);

        Node finish = closedList.get(closedList.size()-1);
        while(finish.getParent()!=null){
            System.out.println("Path should look like this:");
            System.out.println(finish);
            finish = finish.getParent();
        }
    }

    private static int Manhattan(Node begin, Node end) {
        int distX = Math.abs(end.getPosX() - begin.getPosX());
        int distY = Math.abs(end.getPosY() - begin.getPosY());

        return distX + distY;
    }


    private static Node[][] preparePassableMap(Warehouse warehouse) {

        int sizeX = warehouse.getSizeX();
        int sizeY = warehouse.getSizeY();

        Node[][] map = new Node[sizeX][sizeY];

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {

                map[j][i] = new Node(j, i, warehouse.isPassable(i, j));
            }
        }

        return map;
    }
}




