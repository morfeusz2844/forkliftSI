package genetic;

import model.Truck;
import model.Warehouse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patryk on 2015-05-19.
 */
public class Population {

    private WarehouseMapGA[] warehouseMapGAs;

    public Population(int _populationSize, boolean initialise,List<Place> _placeMap,List<PackageGA> _packageGAList, Truck _truck){
        this.warehouseMapGAs = new WarehouseMapGA[_populationSize];

        if (initialise){
            for (int i=0;i<_populationSize;i++){
                WarehouseMapGA temp = new WarehouseMapGA(_placeMap,_truck);
                temp.generateIndividual(_packageGAList);
                this.saveWorldMapGA(i,temp);

            }
        }
        getCountItems();
    }
    public Population(int _populationSize){
        this.warehouseMapGAs = new WarehouseMapGA[_populationSize];
    }
    public void saveWorldMapGA(int _index, WarehouseMapGA _waWarehouseMapGA) {
        this.warehouseMapGAs[_index] = _waWarehouseMapGA;
    }
    private void getCountItems(){
        int counter=0;
        for (int i=0;i<this.warehouseMapGAs.length;i++){
            counter = counter+this.warehouseMapGAs[i].getPlaceMap().size();
        }
        System.out.println(counter);
    }
    public int populationSize(){
        return this.warehouseMapGAs.length;
    }
    public WarehouseMapGA getWarehouseMapGA(int _index){
        return this.warehouseMapGAs[_index];
    }
    public WarehouseMapGA getFittest(){
        WarehouseMapGA fittest= this.warehouseMapGAs[0];
        for (int i=1;i<this.warehouseMapGAs.length;i++){
            int test;
            if ((test=this.warehouseMapGAs[i].getFitness())>0){
                System.out.println("Jakosc pojedynczego osobnika: "+test);
            }

            if (fittest.getFitness()<=this.warehouseMapGAs[i].getFitness()){
                fittest = getWarehouseMapGA(i);
            }
        }
        return fittest;
    }
}
