package neuron;

/**
 * Created by Michal on 2015-06-05.
 */
public class Function implements IActivationFunction{

    public double Beta;

    public Function() { Beta = 1.5; }
    public Function(double B) { Beta = B; }
    public double Calculate(double In)
    {
        return (1.0 / (1.0 + Math.pow(Math.E, -(Beta * In))) ) ;
    }
    public double CalculateDerivative(double Out)
    {
        return Out * (1.0 - Out);
    }

}
