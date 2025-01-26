package edu.uph.ii.ppproject.exceptions;

public class BuildingNotFoundException extends RuntimeException {
    public BuildingNotFoundException(String message) {
        super(message);
    }

    public BuildingNotFoundException(Long id) {
        super("building with this id not found");
    }
}
