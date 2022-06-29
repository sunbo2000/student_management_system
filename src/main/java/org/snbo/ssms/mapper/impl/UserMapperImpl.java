package org.snbo.ssms.mapper.impl;

import org.snbo.ssms.bean.User;
import org.snbo.ssms.mapper.UserMapper;
import org.snbo.ssms.utils.FilenameUtils;


/**
 * @author sunbo
 * @date 2022-06-28-19:24
 */
public class UserMapperImpl extends BaseMapperImpl<User> implements UserMapper {

    public UserMapperImpl() {
        super(FilenameUtils.getUserFilename());
    }

}
