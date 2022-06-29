package org.snbo.ssms.service.impl;

import org.snbo.ssms.bean.CourseStudent;
import org.snbo.ssms.mapper.CourseStudentMapper;
import org.snbo.ssms.service.CourseStudentService;

/**
 * @author sunbo
 * @date 2022-06-07-22:52
 */
public class CourseStudentServiceImpl extends BaseServiceImpl<CourseStudentMapper, CourseStudent> implements CourseStudentService {
    public CourseStudentServiceImpl(CourseStudentMapper baseMapper) {
        super(baseMapper);
    }
}
