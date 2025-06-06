package com.tgid.exceptions;

public class AddressNotFoundException extends RuntimeException {
    private String message;

    public AddressNotFoundException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
