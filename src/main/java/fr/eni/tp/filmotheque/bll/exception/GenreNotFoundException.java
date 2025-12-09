package fr.eni.tp.filmotheque.bll.exception;

public class GenreNotFoundException extends RuntimeException {

    public GenreNotFoundException(String message) {
        super(message);
    }
}