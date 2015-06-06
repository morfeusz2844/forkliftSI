package neuron;

/**
 * Created by Michal on 2015-06-05.
 */
public interface IActivationFunction {

    double Calculate(double In);
    double CalculateDerivative(double Out);

}
