package Address_Service.exception;

public class ResourceNotAvailableException extends RuntimeException{
    public ResourceNotAvailableException(String message){
        super(message);
    }
}
