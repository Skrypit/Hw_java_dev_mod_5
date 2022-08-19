package models;

import lombok.Data;

@Data
public class User {

    int id;
    String userName;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    int userStatus;
}

