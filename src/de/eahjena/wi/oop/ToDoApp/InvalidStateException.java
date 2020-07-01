package de.eahjena.wi.oop.ToDoApp;

public class InvalidStateException extends Exception {

    // Leider kann Java keine Default-Parameter, also ist die Lösung Overloads....

    public InvalidStateException() {
    }

    public InvalidStateException(String message) {
        super(message);
    }

    public InvalidStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidStateException(Throwable cause) {
        super(cause);
    }

    public InvalidStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }



}
