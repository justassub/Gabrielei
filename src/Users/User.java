package Users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class so other type of uses can extend it
 */
public abstract class User {

    private String username;
    private String password;
    private List<Roles> authorities;


    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Roles> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Roles> authorities) {
        this.authorities = authorities;
    }


}
