package controller;

import com.googlecode.fannj.Fann;
import genetic.*;
import model.*;
import model.Package;
import neuron.Function;
import neuron.NeuronNetwork;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal on 2015-06-04.
 */
public class NeuronController {

    private Truck truck;
    private List<Place> placeMap = new ArrayList<>();
    private int[][] matrix;
    private Warehouse warehouse;
    private double[] warehouseMap;

    private List<PackageGA> listOfPackageToUnload = new ArrayList<>();

    public NeuronController(Warehouse _warehouse, Truck truck) throws Exception {

        testNetwork();

        warehouseMap = getWarehouseMap(_warehouse);

        //isWarehauseCleanUp();

    }

    public boolean isWarehauseCleanUp() throws Exception {

        double[] WyjscieSieci = new double[3];
        NeuronNetwork Siec = new NeuronNetwork(225, 175, 1, 1, new Function());
        Siec.RandomWeight(-1.0,1.0);
        double learningCoefficient = 0.1;
        double []DaneWy = new double[1];
        DaneWy[0] = 1.0;

        /*for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, uklad0, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci u1");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }*/

        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, uklad1, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci u1");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }

        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, uklad2, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci u2");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }

        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, uklad3, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci u3");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }


        WyjscieSieci = Siec.Calculate(warehouseMap);
        System.out.println("Wynik sieci: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }

        if(WyjscieSieci[0] > 0.5){
            return true;
        }else{
            return false;
        }

    }

    double []uklad0 = {
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,1.0,1.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,1.0,1.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,1.0,1.0,1.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,1.0,1.0,1.0,1.0,
    };

    double []uklad1 = {
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
            1.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,
            1.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
    };

    double []uklad2 = {
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
    };

    double []uklad3 = {
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,1.0,1.0,0.0,1.0,1.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,
    };


    private double[] getWarehouseMap(Warehouse _warehouse){
        double[] map = new double[_warehouse.getSizeY() * _warehouse.getSizeX()];

        WorldElement[][] worldElement = _warehouse.getWarehouseMap();
        int k = 0;
        for (int i = 0;i < _warehouse.getSizeX();i++) {
            for (int j = 0;j < _warehouse.getSizeX();j++) {
                if(worldElement[i][j].getType() == "Ground") {
                    Ground ground = (Ground)worldElement[i][j];
                    if(ground.getItemsSize() != "empty")
                    {
                        map[k]=1;
                    }else {
                        map[k]=0;
                    }
                }else if(worldElement[i][j].getType() == "StorageRack"){
                    StorageRack rack = (StorageRack)worldElement[i][j];
                    if(rack.getItemsSize() != "empty")
                    {
                        map[k]=1;
                    }else {
                        map[k]=0;
                    }
                }else {
                    map[k]=0;
                }
                k++;
            }
        }

        return map;
    }

    private void testNetwork() throws Exception {

        double[] WyjscieSieci = new double[3];
        NeuronNetwork Siec = new NeuronNetwork(35, 35, 3, 1, new Function());
        Siec.RandomWeight(-1.0,1.0);
        double learningCoefficient = 0.1;
        double []DaneWe;
        double []DaneWy;


        //////////////////////////////////////////////
        //NAUKA1
        //////////////////////////////////////////////

        DaneWy = new double[3];
        DaneWy[0] = 1.0;
        DaneWy[1] = 0.0;
        DaneWy[2] = 0.0;


        DaneWe = LiteraJ1;

        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci1a: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }


        DaneWe = LiteraJ2;

        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci1b: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }


        DaneWe = LiteraJ3;

        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci1c: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }

        //////////////////////////////////////////////
        //NAUKA2
        //////////////////////////////////////////////
        DaneWy = new double[3];
        DaneWy[0] = 0.0;
        DaneWy[1] = 1.0;
        DaneWy[2] = 0.0;

        DaneWe = LiteraA1;


        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci2a: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }


        DaneWe = LiteraA2;


        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci2b: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }


        DaneWe = LiteraA3;


        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci3c: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }

        //////////////////////////////////////////////
        //NAUKA3
        //////////////////////////////////////////////
        DaneWy = new double[3];
        DaneWy[0] = 0.0;
        DaneWy[1] = 0.0;
        DaneWy[2] = 1.0;

        DaneWe = LiteraY1;


        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci3a: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }


        DaneWe = LiteraY2;


        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci3b: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }


        DaneWe = LiteraY3;


        for (int i=0; i<100; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci3c: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }



        //////////////////////////////////////////////
        //TEST
        //////////////////////////////////////////////

        DaneWe = LiteraJTEST;
        WyjscieSieci = Siec.Calculate(DaneWe);
        System.out.println("Wynik sieci J: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }


        DaneWe = LiteraATEST;
        WyjscieSieci = Siec.Calculate(DaneWe);
        System.out.println("Wynik sieci A: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }

        DaneWe = LiteraYTEST;
        WyjscieSieci = Siec.Calculate(DaneWe);
        System.out.println("Wynik sieci Y: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }

    }



    double []LiteraJ1 = {
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,1.0,1.0,0.0,1.0,
            0.0,1.0,0.0,0.0,1.0,
            0.0,1.0,1.0,1.0,1.0,
    };

    double []LiteraJ2 = {
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,1.0,0.0,0.0,1.0,
            0.0,1.0,0.0,0.0,1.0,
            0.0,0.0,1.0,1.0,0.0,
    };

    double []LiteraJ3 = {
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,1.0,0.0,1.0,
            0.0,0.0,1.0,0.0,1.0,
            0.0,0.0,0.0,1.0,0.0,
    };


    double []LiteraA1 = {
            1.0,1.0,1.0,1.0,0.0,
            1.0,0.0,0.0,1.0,0.0,
            1.0,1.0,1.0,1.0,0.0,
            1.0,0.0,0.0,1.0,0.0,
            1.0,0.0,0.0,1.0,0.0,
            1.0,0.0,0.0,1.0,0.0,
            1.0,0.0,0.0,1.0,0.0,
    };

    double []LiteraA2 = {
            1.0,1.0,1.0,1.0,1.0,
            1.0,0.0,0.0,1.0,1.0,
            1.0,0.0,0.0,0.0,1.0,
            1.0,0.0,0.0,0.0,1.0,
            1.0,1.0,1.0,1.0,1.0,
            1.0,0.0,0.0,0.0,1.0,
            1.0,0.0,0.0,0.0,1.0,
    };

    double []LiteraA3 = {
            0.0,1.0,1.0,1.0,0.0,
            1.0,0.0,0.0,0.0,1.0,
            1.0,0.0,0.0,0.0,1.0,
            1.0,1.0,1.0,1.0,1.0,
            1.0,0.0,0.0,0.0,1.0,
            1.0,0.0,0.0,0.0,1.0,
            1.0,0.0,0.0,0.0,1.0,
    };


    double []LiteraY1 = {
            1.0,0.0,0.0,0.0,1.0,
            0.0,1.0,0.0,1.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
    };

    double []LiteraY2 = {
            0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,0.0,0.0,1.0,
            0.0,1.0,0.0,1.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
    };

    double []LiteraY3 = {
            1.0,0.0,0.0,0.0,1.0,
            1.0,0.0,0.0,0.0,1.0,
            0.0,1.0,0.0,1.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
    };

    double []LiteraJTEST = {
            0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,1.0,0.0,0.0,1.0,
            0.0,1.0,1.0,1.0,1.0,
    };

    double []LiteraJTEST1 = {
            0.0,0.0,0.0,1.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,1.0,1.0,1.0,
    };

    double []LiteraJTEST2 = {
            0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,0.0,1.0,
            0.0,1.0,1.0,0.0,1.0,
            0.0,1.0,0.0,0.0,1.0,
            0.0,1.0,1.0,1.0,0.0,
    };

    double []LiteraATEST = {
            0.0,1.0,1.0,1.0,0.0,
            1.0,0.0,0.0,0.0,1.0,
            1.0,0.0,0.0,0.0,1.0,
            1.0,1.0,0.0,1.0,1.0,
            1.0,0.0,0.0,0.0,0.0,
            1.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
    };

    double []LiteraATEST1 = {
            0.0,1.0,1.0,1.0,0.0,
            0.0,0.0,0.0,1.0,0.0,
            1.0,0.0,0.0,1.0,0.0,
            1.0,1.0,1.0,1.0,0.0,
            1.0,0.0,0.0,0.0,0.0,
            1.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
    };

    double []LiteraATEST2 = {
            0.0,1.0,1.0,0.0,0.0,
            0.0,1.0,0.0,1.0,0.0,
            0.0,1.0,0.0,1.0,0.0,
            0.0,1.0,1.0,1.0,0.0,
            0.0,1.0,0.0,1.0,0.0,
            0.0,1.0,0.0,0.0,0.0,
            0.0,1.0,0.0,0.0,0.0,
    };

    double []LiteraYTEST = {
            0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
            0.0,1.0,0.0,1.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
    };

    double []LiteraYTEST1 = {
            0.0,0.0,0.0,0.0,0.0,
            1.0,0.0,0.0,0.0,1.0,
            0.0,1.0,1.0,1.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
    };

    double []LiteraYTEST2 = {
            0.0,0.0,0.0,0.0,1.0,
            0.0,0.0,0.0,1.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,1.0,0.0,0.0,
            0.0,0.0,0.0,0.0,0.0,
    };


}
