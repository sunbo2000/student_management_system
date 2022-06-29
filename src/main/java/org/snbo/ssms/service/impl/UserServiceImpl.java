package org.snbo.ssms.service.impl;

import org.snbo.ssms.bean.User;
import org.snbo.ssms.mapper.UserMapper;
import org.snbo.ssms.service.UserService;
import org.snbo.ssms.utils.IdUtils;
import org.snbo.ssms.utils.MD5Utils;
import org.snbo.ssms.utils.Result;
import org.snbo.ssms.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunbo
 * @date 2022-06-28-19:30
 */
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    public UserServiceImpl(UserMapper baseMapper) {
        super(baseMapper);
    }

    @Override
    public Result login(String username, String password) {

        if (StringUtils.isEmpty(username)) {
            return Result.error().message("请输入用户名");
        }
        if (StringUtils.isEmpty(password)) {
            return Result.error().message("请输入密码");
        }
        List<User> users = baseMapper.getAllInfoOfList();
        if (users == null) {
            users = new ArrayList<>();
        }
        User user = null;
        for (User u : users) {
            if (username.equals(u.getUsername())) {
                user = u;
                break;
            }
        }
        if (user == null) {
            return Result.error().message("用户不存在");
        }

        if (MD5Utils.encrypt(password).equals(user.getPassword())) {
            return Result.ok();
        }

        return Result.error().message("密码错误");
    }

    @Override
    public Result register(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            return Result.error().message("请输入用户名");
        }

        if (StringUtils.isEmpty(password)) {
            return Result.error().message("请输入密码");
        }

        List<User> list = baseMapper.getAllInfoOfList();
        if (list == null) {
            list = new ArrayList<>();
        }
        for (User u : list) {
            if (username.equals(u.getUsername())) {
                return Result.error().message("用户已存在");
            }
        }

        User user = new User();
        user.setId(IdUtils.getUserId());
        user.setUsername(username);
        user.setPassword(MD5Utils.encrypt(password));
        baseMapper.saveInfo(user);

        return Result.ok();
    }
}
