package org.snbo.ssms.bean;

import java.io.Serializable;

/**
 * @author sunbo
 * @date 2022-06-28-19:22
 */

public class User extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1905122041950251299L;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public User() {
    }

    public User(Integer id) {
        super(id);
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
