package neuron;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Michal on 2015-06-06.
 */
public class Layer {

    ArrayList<Neuron> Neurony;

    public Layer(int CountN) throws Exception {
        if (CountN < 1) { throw (new Exception("Blad"));}
        Neurony = new ArrayList();
        for (int i=0; i<CountN; i++)
        {
            AddNeuron(new Neuron());
        }
    }

    public Layer(int CountN, IActivationFunction f) throws Exception {
        if ((CountN < 1) || (f == null))
        { throw (new Exception("Blad"));  }
        Neurony = new ArrayList();
        for (int i=0; i<CountN; i++) { AddNeuron(new Neuron(f));}
    }

    public void AddNeuron(Neuron N) throws Exception {
        if (N == null) { throw (new Exception("Blad")); };
        Neurony.add(N);
    }

    public void ConnectWithLayer(Layer W) throws Exception {
        if (W == null) { throw (new Exception("Blad")); };
        for(Neuron N : Neurony)
        {
            N.AddIn(W);
        }
    }

    public void RandowWeight(double Min, double Max, Random R) throws Exception {
        if (R == null) { throw (new Exception("Blad")); };
        for(Neuron N : Neurony)
        {
            N.RandomWeight(Min, Max, R);
        }
    }

    public void LawsOut(double []Out) throws Exception {
        if ((Out == null) || (Out.length != Neurony.size()))
        { throw (new Exception("Blad")); };
        for (int i=0; i<Neurony.size(); i++)
        {
            Neurony.get(i).Out = Out[i];
        }
    }

    public double[] ReturnOut()
    {
        double []Wy = new double[Neurony.size()];
        for (int i=0; i<Neurony.size(); i++)
        {
            Wy[i] = Neurony.get(i).Out;
        }
        return Wy;
    }

    public void Calculate()
    {
        for(Neuron N : Neurony)
        {
            N.Calculate();
        }
    }

    public void ResetError()
    {
        for(Neuron N : Neurony)
        {
            N.Error = 0.0;
        }
    }

    public void CalculateErrors(double []CorrectOut) throws Exception {
        if (CorrectOut.length != Neurony.size())
        {  throw (new Exception("Blad"));  };
        for (int i=0; i<Neurony.size(); i++)
        {
            Neurony.get(i).CalculateError(CorrectOut[i]);
        }
    }

    public void CorrectWeights(double learningCoefficient) throws Exception {
        if (learningCoefficient < 0) { throw (new Exception("Blad"));  };
        for (Neuron N : Neurony)
        {
            N.CorrectWeight(learningCoefficient);
        }
    }

    public void RollUpErrorsBack()
    {
        for(Neuron N : Neurony)
        {
            N.RollUpErrorBack();
        }
    }

}
