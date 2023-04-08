package BackEnd.P_I.Exceptions;

public class BadRequestException extends Exception{
    public BadRequestException(String message){
        super(message);
    }
}
