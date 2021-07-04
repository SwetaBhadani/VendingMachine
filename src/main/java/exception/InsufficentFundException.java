package exception;

public class InsufficentFundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InsufficentFundException() {
	super();
    }

    public InsufficentFundException(String message) {
	super(message);
    }

}
