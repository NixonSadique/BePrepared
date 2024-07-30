package com.nixon.BePrepared.Exception;

public class EntityNotFoundException extends BadRequestException{

    public EntityNotFoundException(String message){
        super(message);
    }
}
