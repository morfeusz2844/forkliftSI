package controller;

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

    private List<PackageGA> listOfPackageToUnload = new ArrayList<>();

    public NeuronController(Warehouse _warehouse, Truck truck) throws Exception {

        double[] WyjscieSieci = new double[3];
        NeuronNetwork Siec = new NeuronNetwork(16, 18, 3, 1, new Function());
        Siec.RandomWeight(-1.0,1.0);
        double learningCoefficient = 0.15;
        //////////////////////////////////////////////
        //NAUKA1
        //////////////////////////////////////////////

        double []DaneWy = new double[3];
        DaneWy[0] = 0.0;
        DaneWy[1] = 1.0;
        DaneWy[2] = 0.0;

        double []DaneWe = new double[16];
        DaneWe[0] = 1.0; DaneWe[4] = 0.0; DaneWe[8] = 0.0;  DaneWe[12] = 1.0;
        DaneWe[1] = 1.0; DaneWe[5] = 0.0; DaneWe[9] = 0.0;  DaneWe[13] = 1.0;
        DaneWe[2] = 1.0; DaneWe[6] = 0.0; DaneWe[10] = 0.0; DaneWe[14] = 1.0;
        DaneWe[3] = 1.0; DaneWe[7] = 1.0; DaneWe[11] = 1.0; DaneWe[15] = 1.0;

        for (int i=0; i<10000; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci1: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }

        //////////////////////////////////////////////
        //NAUKA2
        //////////////////////////////////////////////
        DaneWy = new double[3];
        DaneWy[0] = 0.0;
        DaneWy[1] = 0.0;
        DaneWy[2] = 1.0;

        DaneWe = new double[16];
        DaneWe[0] = 0.0; DaneWe[4] = 1.0; DaneWe[8] = 1.0;  DaneWe[12] = 0.0;
        DaneWe[1] = 0.0; DaneWe[5] = 1.0; DaneWe[9] = 1.0;  DaneWe[13] = 0.0;
        DaneWe[2] = 0.0; DaneWe[6] = 0.0; DaneWe[10] = 0.0; DaneWe[14] = 0.0;
        DaneWe[3] = 0.0; DaneWe[7] = 0.0; DaneWe[11] = 0.0; DaneWe[15] = 0.0;


        for (int i=0; i<10000; i++) {
            Siec.LearningNetwork(DaneWy, DaneWe, learningCoefficient);
            WyjscieSieci = Siec.lOut.ReturnOut();
        }

        System.out.println("Nauka sieci2: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }


        //////////////////////////////////////////////
        //TEST
        //////////////////////////////////////////////
        DaneWe = new double[16];
        DaneWe[0] = 1.0; DaneWe[4] = 0.0; DaneWe[8] = 0.0;  DaneWe[12] = 0.0;
        DaneWe[1] = 1.0; DaneWe[5] = 0.0; DaneWe[9] = 0.0;  DaneWe[13] = 1.0;
        DaneWe[2] = 0.0; DaneWe[6] = 0.0; DaneWe[10] = 0.0; DaneWe[14] = 1.0;
        DaneWe[3] = 0.0; DaneWe[7] = 1.0; DaneWe[11] = 1.0; DaneWe[15] = 1.0;

        WyjscieSieci = Siec.Calculate(DaneWe);

        System.out.println("Wynik sieci 2: ");
        for(double i : WyjscieSieci){
            System.out.println(i);
        }

    }

}
