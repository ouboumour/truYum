package com.cognizant.truyum.dao;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(Class entityClass, long entityId) {
        super(entityClass.getName()+" entity with id "+ entityId+ " does not exist");
    }
}
