package com.navi.splitwise.models;

import lombok.Data;

/**
 * @author Abhinav Tripathi 15/03/21
 */
@Data
public class User {
    private String name;
    private String email;

    public User(String name) {
        this.name = name;
    }
}
