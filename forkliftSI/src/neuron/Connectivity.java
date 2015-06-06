package neuron;

/**
 * Created by Michal on 2015-06-06.
 */
public class Connectivity {

    public Neuron           N;
    public double           Weight;

    public Connectivity(Neuron N, double W) throws Exception {
        if (N == null) { throw (new Exception("Blad")); }
        this.N  = N;
        Weight  = W;
    }


}
