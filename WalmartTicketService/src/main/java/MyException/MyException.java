package MyException;


public class MyException extends Exception {

    private String myExceptionMessage;

    public MyException(String message){
        this.myExceptionMessage=message;
    }

    public String getMyExceptionMessage() {
        return myExceptionMessage;
    }

    public void setMyExceptionMessage(String myExceptionMessage) {
        this.myExceptionMessage = myExceptionMessage;
    }
}
