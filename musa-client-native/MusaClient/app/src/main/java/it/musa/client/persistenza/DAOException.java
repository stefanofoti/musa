package it.musa.client.persistenza;

public class DAOException extends RuntimeException {

    public DAOException() {
        super();
    }

    public DAOException(String s) {
        super(s);
    }

    public DAOException(Exception e) {
        super(e);
    }

}

