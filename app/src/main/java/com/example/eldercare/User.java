package com.example.eldercare;

import org.litepal.crud.LitePalSupport;

// The user class helps the litepal technique to construct table user in database
public class User extends LitePalSupport {
    private String name;            //username
    private String password;        //password

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // convert user into string express
    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", password='" + password + '\'' + '}';
    }
}
