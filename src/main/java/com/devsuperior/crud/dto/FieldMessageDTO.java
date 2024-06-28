package com.devsuperior.crud.dto;

public class FieldMessageDTO {

    private String name;
    private String message;

    public FieldMessageDTO(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
