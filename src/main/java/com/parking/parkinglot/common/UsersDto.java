package com.parking.parkinglot.common;

public class UsersDto {
    Long id;
    String username;
    String email;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public UsersDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
