package BackEnd.P_I.Exceptions;

public class ConflictEmailException extends Exception{

    public ConflictEmailException(String message){
        super(message);
    }
}
