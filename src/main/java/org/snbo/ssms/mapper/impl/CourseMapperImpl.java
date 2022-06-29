package org.snbo.ssms.mapper.impl;

import org.snbo.ssms.bean.Course;
import org.snbo.ssms.mapper.CourseMapper;
import org.snbo.ssms.utils.FilenameUtils;



/**
 * @author sunbo
 * @date 2022-06-06-22:17
 */
public class CourseMapperImpl extends BaseMapperImpl<Course> implements CourseMapper {
    public CourseMapperImpl() {
        super(FilenameUtils.getCourseFilename());
    }
}
