package controller;

import genetic.*;
import model.*;
import model.Package;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Patryk on 2015-05-19.
 */
public class GeneticController {
    private Truck truck;
    private List<Place> placeMap = new ArrayList<>();


    private List<PackageGA> listOfPackageToUnload = new ArrayList<>();

    public GeneticController(Warehouse _warehouse, Truck truck) {
        this.truck = truck;
        this.placeMap = generatePlaceMap(_warehouse);
        getProductsFromTruck();
        showProductToUnload();
        Population pop = new Population(50,true,this.placeMap,listOfPackageToUnload,truck);
        for (int i=0;i<100;i++){
            pop = Algorithm.evolvePopulation(pop);
            System.out.println("NAJLEPSZY W POP LEVEL "+i+": "+pop.getFittest().getFitness());
        }
        System.out.println("NAJLEPSZY PO WSZYSTKIM: "+pop.getFittest().getFitness());
    }

    private List<Place> generatePlaceMap(Warehouse _warehouse) {
        List<Place> placeList = new ArrayList<>();
        for (int i = 0; i < _warehouse.getSizeX(); i++) {
            for (int j = 0; j < _warehouse.getSizeY(); j++) {
                if (_warehouse.getWarehouseMap()[i][j].getType() == "StorageRack") {
                    placeList.add(new Place(_warehouse.getWarehouseMap()[i][j].getType(),
                            ((StorageRack) _warehouse.getWarehouseMap()[i][j]).getItemsType(),
                            ((StorageRack) _warehouse.getWarehouseMap()[i][j]).getItemsSize(),
                            ((StorageRack) _warehouse.getWarehouseMap()[i][j]).getLeftSpace(),
                            i, j));
                }
                if (_warehouse.getWarehouseMap()[i][j].getType() == "Ground") {
                    placeList.add(new Place(_warehouse.getWarehouseMap()[i][j].getType(),
                            ((Ground) _warehouse.getWarehouseMap()[i][j]).getItemsType().toString(),
                            ((Ground) _warehouse.getWarehouseMap()[i][j]).getItemsSize().toString(),
                            ((Ground) _warehouse.getWarehouseMap()[i][j]).getLeftSpace(),
                            i, j));
                }
                if (_warehouse.getWarehouseMap()[i][j].getType() == "Ramp" ||
                        _warehouse.getWarehouseMap()[i][j].getType() == "Road" ||
                        _warehouse.getWarehouseMap()[i][j].getType() == "Service" ||
                        _warehouse.getWarehouseMap()[i][j].getType() == "Truck" ||
                        _warehouse.getWarehouseMap()[i][j].getType() == "Blank"){
                    placeList.add(new Place(_warehouse.getWarehouseMap()[i][j].getType(),
                            i, j));
                }
            }
        }

        return placeList;
    }

    public void getProductsFromTruck() {
        List<Package> temp = this.truck.getListOfProducts();
        for (int i = 0; i < temp.size(); i++) {
            this.listOfPackageToUnload.add(new PackageGA(temp.get(i), i));
        }
    }

    public void showProductToUnload() {
        for (PackageGA temp : this.listOfPackageToUnload) {
            System.out.println(temp.toString());
        }
    }
}
