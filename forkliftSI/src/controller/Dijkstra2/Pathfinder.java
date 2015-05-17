package controller.Dijkstra2;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import model.Warehouse;
import model.WorldElement;

import java.util.LinkedList;
import java.util.List;

public class Pathfinder {

    private Warehouse warehouse;

    public Pathfinder(Warehouse warehouse){
        this.warehouse = warehouse;
    }

    public String findPath(){

        List<Graph.Edge> edges = new LinkedList<>();

        WorldElement currentlyProcessed;

        SetMultimap<String, String> adjacencyMap = HashMultimap.create();

        for (int i = 0; i < warehouse.getSizeX(); i++) {
            for (int j = 0; j < warehouse.getSizeY(); j++) {
                currentlyProcessed = warehouse.getWorldElement(i, j);
                if(currentlyProcessed.isPassable()){
                    for(String s : warehouse.getPassableAdjacencies(i,j)){
                        if(s!=null) {
                            adjacencyMap.put(currentlyProcessed.getType() + " " + i + " " + j, s);
                        }
                    }
                }
            }
        }

        for(String key : adjacencyMap.keySet()){
            for(String value : adjacencyMap.get(key)){
                edges.add(new Graph.Edge(key,value));
            }
        }


        for(Graph.Edge gr : edges){
            System.out.println(gr.toString());
        }
        System.out.println("Edges in Pathfinder: " + edges.size());

        StringBuilder pathString = Dijkstra2.runDijkstra(edges.toArray(new Graph.Edge[edges.size()]), "Road 14 4", "Road 1 6");
        //Dijkstra2.runDijkstra(edges.toArray(new Graph.Edge[edges.size()]), "Road 1 14","Road 1 11");
        //Dijkstra2.runDijkstra(edges.toArray(new Graph.Edge[edges.size()]), "Road 14 4","Road 1 5");
        //Dijkstra2.runDijkstra(edges.toArray(new Graph.Edge[edges.size()]), "Road 14 7","Road 13 8");

        return pathString.toString();
    }
}