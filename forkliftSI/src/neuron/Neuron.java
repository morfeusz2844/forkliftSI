package neuron;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Michal on 2015-06-05.
 */
public class Neuron {

    public IActivationFunction Function;
    public double  Out;
    public ArrayList<Connectivity> In;
    public double BiasWeight;
    public double Error;


    public Neuron()
    {
        Error	= 0.0;
        Out = 0.0;
        In = new ArrayList();
        Function = new Function();
        BiasWeight = 0.0;
    }

    public Neuron(IActivationFunction f) throws Exception {
        if (f == null) { throw (new Exception("Blad")); }
        Error        = 0.0;
        Out     = 0.0;
        In     = new ArrayList();
        Function     = f;
        BiasWeight   = 0.0;
    }

    public void AddIn(Neuron N) throws Exception {
        if (N == null) { throw (new Exception("Blad")); };
        In.add(new Connectivity(N, 1.0));
    }

    public void RandomWeight(double Min, double Max, Random R) throws Exception {
        if ((R == null) || (Max <= Min))
        { throw (new Exception("Blad"));  };
        for (Connectivity Pol : In)
        {
            Pol.Weight = (R.nextDouble() * (Max - Min)) + Min;
        }
        BiasWeight = (R.nextDouble() * (Max - Min)) + Min;
    }

    public void Calculate()
    {
        Out = 0.0;
        for (Connectivity Pol : In)
        {
            Out += Pol.Weight * Pol.N.Out;
        }
        Out += BiasWeight * 1.0;
        Out = Function.Calculate(Out);
    }

    public void CalculateError(double CorrectOut)
    {
        Error = CorrectOut - Out;
    }

    public void CorrectWeight(double WspUczenia) throws Exception {
        if (WspUczenia < 0) { throw (new Exception("Blad")); };
        for (Connectivity p : In)
        {
            p.Weight += WspUczenia * Error * Function.CalculateDerivative(Out) * p.N.Out;
        }
        BiasWeight += WspUczenia * Error * Function.CalculateDerivative(Out) * 1.0;
    }

    public void AddIn(Layer W) throws Exception {
        if (W == null) { throw (new Exception("Blad"));};
        for(Neuron N : W.Neurony)
        {
            AddIn(N);
        }
    }

    public void RollUpErrorBack()
    {
        for(Connectivity p : In)
        {
            p.N.Error += Error * p.Weight;
        }
    }

}
