package neuro;

import neuro.feedforward.FeedforwardLayer;
import neuro.feedforward.FeedforwardNetwork;
import neuro.feedforward.train.Train;
import neuro.feedforward.train.backpropagation.Backpropagation;

/**
 * XOR: Learn the XOR pattern with a feedforward neural network that
 * uses backpropagation.
 * Created by Patryk on 2015-05-17.
 */
public class XOR {
    public static double XOR_INPUT[][] = { { 7.0, 7.0 }, { 1.0, 0.0 },
            { 0.0, 1.0 }, { 1.0, 1.0 } };

    public static double XOR_IDEAL[][] = { { 1.0 }, { 1.0 }, { 1.0 }, { 0.0 } };

    public static void main(final String args[]) {
        final FeedforwardNetwork network = new FeedforwardNetwork();
        network.addLayer(new FeedforwardLayer(2));
        network.addLayer(new FeedforwardLayer(3));
        network.addLayer(new FeedforwardLayer(1));
        network.reset();

        // train the neural network
        final Train train = new Backpropagation(network, XOR_INPUT, XOR_IDEAL,
                0.7, 0.9);

        int epoch = 1;

        do {
            train.iteration();
            System.out
                    .println("Epoch #" + epoch + " Error:" + train.getError());
            epoch++;
        } while ((epoch < 5000) && (train.getError() > 0.001));

        // test the neural network
        System.out.println("Neural Network Results:");
        for (int i = 0; i < XOR_IDEAL.length; i++) {
            final double actual[] = network.computeOutputs(XOR_INPUT[i]);
            System.out.println(XOR_INPUT[i][0] + "," + XOR_INPUT[i][1]
                    + ", actual=" + actual[0] + ",ideal=" + XOR_IDEAL[i][0]);
        }
    }
}
