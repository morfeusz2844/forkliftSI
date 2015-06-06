package neuron;

import java.util.Random;

/**
 * Created by Michal on 2015-06-06.
 */
public class NeuronNetwork {

    public Layer lIn, lOut;
    public Layer []lHidden;

    public NeuronNetwork(int SizeIn,
                         int SizeHidden,
                         int SizeOut,
                         int CountHidden,
                         IActivationFunction f) throws Exception {
        if ((SizeIn < 1) || (SizeHidden < 1) ||
                (SizeOut < 1) || (CountHidden < 1) || (f == null))
        { throw (new Exception("Blad"));};

        // tworzenie odpowiednich warstw...
        lIn = new Layer(SizeIn);
        lOut = new Layer(SizeOut, f);
        lHidden = new Layer[CountHidden];
        for (int i=0; i<CountHidden; i++)
        { lHidden[i] = new Layer(SizeHidden, f); }

        // ...i laczenie ich ze soba.
        lOut.ConnectWithLayer(lHidden[CountHidden - 1]);
        for (int i=(CountHidden-1); i>0; i--)
        {
            lHidden[i].ConnectWithLayer(lHidden[i - 1]);
        }
        lHidden[0].ConnectWithLayer(lIn);
    }

    public void RandomWeight(double Min, double Max) throws Exception
    {
        Random Rand = new Random();

        lOut.RandowWeight(Min, Max, Rand);
        for (int i=0; i<lHidden.length; i++)
        {
            lHidden[i].RandowWeight(Min, Max, Rand);
        }
    }

    public double[] Calculate(double []In)throws Exception
    {
        if ((In == null) ||
                (In.length != lIn.Neurony.size()))
        { throw(new Exception("Blad")); };

        lIn.LawsOut(In);
        for (int i=0; i<lHidden.length; i++)
        {
            lHidden[i].Calculate();
        }
        lOut.Calculate();
        return lOut.ReturnOut();
    }

    public void LearningNetwork(double []CorrectOut,
                        double []In, double learningCoefficient) throws Exception {
        if ((CorrectOut.length != lOut.Neurony.size()) ||
                (In.length != lIn.Neurony.size()))
        { throw(new Exception("Blad")); };

        lOut.ResetError();
        for (int i=0; i<lHidden.length; i++)
        { lHidden[i].ResetError(); }

        Calculate(In);

        lOut.CalculateErrors(CorrectOut);
        lOut.RollUpErrorsBack();
        for (int i=(lHidden.length-1); i>0; i--)
        { lHidden[i].RollUpErrorsBack(); }

        lOut.CorrectWeights(learningCoefficient);
        for (int i=0; i<lHidden.length; i++)
        { lHidden[i].CorrectWeights(learningCoefficient);
        }
    }


}
