package neuro.exception;

/**
 * NeuralNetworkError: Used by the neural network classes to
 * indicate an error.
 * Created by Patryk on 2015-05-17.
 */
public class NeuralNetworkError extends RuntimeException {
    /**
     * Serial id for this class.
     */
    private static final long serialVersionUID = 7167228729133120101L;

    /**
     * Construct a message exception.
     *
     * @param msg
     *            The exception message.
     */
    public NeuralNetworkError(final String msg) {
        super(msg);
    }

    /**
     * Construct an exception that holds another exception.
     *
     * @param t
     *            The other exception.
     */
    public NeuralNetworkError(final Throwable t) {
        super(t);
    }
}
