package org.snbo.ssms.service.impl;

import org.snbo.ssms.bean.Course;
import org.snbo.ssms.mapper.CourseMapper;
import org.snbo.ssms.service.CourseService;

/**
 * @author sunbo
 * @date 2022-06-07-22:50
 */
public class CourseServiceImpl extends BaseServiceImpl<CourseMapper, Course> implements CourseService {
    public CourseServiceImpl(CourseMapper baseMapper) {
        super(baseMapper);
    }
}
