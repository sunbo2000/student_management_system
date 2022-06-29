package org.snbo.ssms.service;

import org.snbo.ssms.bean.User;
import org.snbo.ssms.utils.Result;

/**
 * @author sunbo
 * @date 2022-06-28-19:28
 */
public interface UserService extends BaseService<User> {
    /**
     * description: 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link Result} 登录结果
     * @author sunbo
     * @date 2022/6/28 19:43
     */
    Result login(String username, String password);

    /**
     * description: 注册管理员
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link Result} 注册结果
     * @author sunbo
     * @date 2022/6/28 20:10
     */
    Result register(String username, String password);
}
