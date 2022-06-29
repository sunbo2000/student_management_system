package org.snbo.ssms.mapper.impl;

import org.snbo.ssms.bean.CourseStudent;
import org.snbo.ssms.mapper.CourseStudentMapper;
import org.snbo.ssms.utils.FilenameUtils;

/**
 * @author sunbo
 * @date 2022-06-07-10:57
 */
public class CourseStudentMapperImpl extends BaseMapperImpl<CourseStudent> implements CourseStudentMapper {
    public CourseStudentMapperImpl() {
        super(FilenameUtils.getCourseStudentFilename());
    }
}
