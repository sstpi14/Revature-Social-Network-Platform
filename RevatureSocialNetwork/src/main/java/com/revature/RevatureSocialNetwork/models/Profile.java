package com.revature.RevatureSocialNetwork.models;


import lombok.*;

import java.sql.Blob;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Profile {
    private Integer id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private Blob img;
    private String email;

    public Profile(String username, String password, String first_name, String last_name, String email) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
}
