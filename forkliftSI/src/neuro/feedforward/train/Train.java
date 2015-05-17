package neuro.feedforward.train;

import neuro.feedforward.FeedforwardNetwork;

/**
 * Train: Interface for all feedforward neural network training
 * methods.  There are currently three training methods define:
 *
 * Backpropagation
 * Genetic Algorithms
 * Simulated Annealing
 *
 * Created by Patryk on 2015-05-17.
 */
public interface Train {

    /**
     * Get the current error percent from the training.
     * @return The current error.
     */
    public double getError();

    /**
     * Get the current best network from the training.
     * @return The best network.
     */
    public FeedforwardNetwork getNetwork();

    /**
     * Perform one iteration of training.
     */
    public void iteration();
}
