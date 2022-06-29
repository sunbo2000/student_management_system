package org.snbo.ssms.view.studentview;

/**
 * @author sunbo
 * @date 2022-06-28-20:39
 */
public class UserVo {
    private Integer id;
    private String username;


    public UserVo() {
    }

    public UserVo(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public UserVo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserVo setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
