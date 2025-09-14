package exceptions;

public class AssemblyFailedException extends RuntimeException {
    public AssemblyFailedException(String message) {
        super(message);
    }
}
