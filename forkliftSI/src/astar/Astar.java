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

    public List<Node> findPath(int startX, int startY, int destinationX, int destinationY) {

        List<Node> finalResult = new ArrayList<>();

        Comparator<Node> nodeComparator = (o1, o2) -> o1.getF() - o2.getF();

        ArrayList<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        Node destinationNode = passableMap[destinationX][destinationY];

        Node currentNode = passableMap[startX][startY];

        if (!destinationNode.isPassable()) {

            List<Node> adjacencies = new ArrayList<>();
            Node justAnotherTempNode;
            if (destinationNode.getPosX() - 1 >= 0) {
                justAnotherTempNode = passableMap[destinationNode.getPosX() - 1][destinationNode.getPosY()];
                if (justAnotherTempNode.isPassable())
                    adjacencies.add(justAnotherTempNode);
            }
            if (destinationNode.getPosX() + 1 <= warehouse.getSizeX() - 1) {
                justAnotherTempNode = passableMap[destinationNode.getPosX() + 1][destinationNode.getPosY()];
                if (justAnotherTempNode.isPassable())
                    adjacencies.add(justAnotherTempNode);
            }
            if (destinationNode.getPosY() - 1 >= 0) {
                justAnotherTempNode = passableMap[destinationNode.getPosX()][destinationNode.getPosY() - 1];
                if (justAnotherTempNode.isPassable())
                    adjacencies.add(justAnotherTempNode);
            }
            if (destinationNode.getPosY() + 1 <= warehouse.getSizeY() + 1) {
                justAnotherTempNode = passableMap[destinationNode.getPosX()][destinationNode.getPosY() + 1];
                if (justAnotherTempNode.isPassable())
                    adjacencies.add(justAnotherTempNode);
            }
            if (adjacencies.isEmpty()) {
                System.out.println("A* = Punkt docelowy nieosiągalny");
                return null;
            }
            for (Node n : adjacencies) {
                n.setH(Manhattan(currentNode, n));
            }
            destinationNode = adjacencies.get(0);
            for (Node n : adjacencies) {
                if (n.getH() < currentNode.getH()) {
                    destinationNode = n;
                }
            }
        }

        currentNode.setH(Manhattan(currentNode, destinationNode));

        openList.add(currentNode);

        Node tempNode;

        while (!openList.isEmpty() && !closedList.contains(destinationNode)) {

            if (currentNode.getPosX() - 1 >= 0) {
                tempNode = passableMap[currentNode.getPosX() - 1][currentNode.getPosY()];
                if (tempNode.isPassable() && !closedList.contains(tempNode))
                    if (openList.contains(tempNode)) {

                        if (tempNode.getG() > currentNode.getG() + tempNode.getCost()) {
                            tempNode.setG(currentNode.getG() + tempNode.getCost()).setParent(currentNode);
                        }

                    } else {
                        openList.add(tempNode.setParent(currentNode).setG(currentNode.getG() + tempNode.getCost()).setH(Manhattan(tempNode, destinationNode)));
                    }
            }

            if (currentNode.getPosX() + 1 <= warehouse.getSizeX() - 1) {
                tempNode = passableMap[currentNode.getPosX() + 1][currentNode.getPosY()];
                if (tempNode.isPassable() && !closedList.contains(tempNode))
                    if (openList.contains(tempNode)) {
                        if (tempNode.getG() > currentNode.getG() + tempNode.getCost()) {
                            tempNode.setG(currentNode.getG() + tempNode.getCost()).setParent(currentNode);
                        }
                    } else {
                        openList.add(tempNode.setParent(currentNode).setG(currentNode.getG() + tempNode.getCost()).setH(Manhattan(tempNode, destinationNode)));
                    }
            }
            if (currentNode.getPosY() - 1 >= 0) {
                tempNode = passableMap[currentNode.getPosX()][currentNode.getPosY() - 1];
                if (tempNode.isPassable() && !closedList.contains(tempNode))
                    if (openList.contains(tempNode)) {
                        if (tempNode.getG() > currentNode.getG() + tempNode.getCost()) {
                            tempNode.setG(currentNode.getG() + tempNode.getCost()).setParent(currentNode);
                        }
                    } else {
                        openList.add(tempNode.setParent(currentNode).setG(currentNode.getG() + tempNode.getCost()).setH(Manhattan(tempNode, destinationNode)));
                    }
            }
            if (currentNode.getPosY() + 1 <= warehouse.getSizeY() - 1) {
                tempNode = passableMap[currentNode.getPosX()][currentNode.getPosY() + 1];
                if (tempNode.isPassable() && !closedList.contains(tempNode))
                    if (openList.contains(tempNode)) {
                        if (tempNode.getG() > currentNode.getG() + tempNode.getCost()) {
                            tempNode.setG(currentNode.getG() + tempNode.getCost()).setParent(currentNode);
                        }
                    } else {
                        openList.add(tempNode.setParent(currentNode).setG(currentNode.getG() + tempNode.getCost()).setH(Manhattan(tempNode, destinationNode)));
                    }
            }
            openList.remove(currentNode);
            closedList.add(currentNode);

            openList.sort(nodeComparator);

            currentNode = openList.get(0);

            System.out.println(closedList);
        }

        System.out.println(closedList);

        Node finish = closedList.get(closedList.size() - 1);
        while (finish.getParent() != null) {

            finalResult.add(finish);
            System.out.println("Path should look like this:");
            System.out.println(finish);
            finish = finish.getParent();
        }
        finalResult.add(finish);
        return finalResult;
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

                map[i][j] = new Node(i, j, warehouse.isPassable(i, j));
            }
        }

        return map;
    }
}




