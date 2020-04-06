package exception;

public class WrongInputException extends Exception {

    private int code;

    public WrongInputException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
