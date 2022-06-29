package org.snbo.ssms.controller;

import org.snbo.ssms.bean.User;
import org.snbo.ssms.mapper.impl.UserMapperImpl;
import org.snbo.ssms.service.UserService;
import org.snbo.ssms.service.impl.UserServiceImpl;
import org.snbo.ssms.utils.Result;

import java.util.List;

/**
 * @author sunbo
 * @date 2022-06-28-19:34
 */
public class UserController {
    private final UserService userService;

    {
        userService = new UserServiceImpl(new UserMapperImpl());
    }

    public Result login(String username, String password) {
        return userService.login(username, password);
    }

    public Result register(String username, String password) {
        return userService.register(username, password);
    }

    public Result getUsers() {
        List<User> list = userService.getAllOfList();
        return Result.ok().data("userList", list);
    }

    public Result remove(User user) {
        boolean remove = userService.remove(user);
        if (remove) {
            return Result.ok();
        }
        return Result.error();
    }
}
