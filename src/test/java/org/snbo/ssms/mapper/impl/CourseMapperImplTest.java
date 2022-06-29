package org.snbo.ssms.mapper.impl;

import org.junit.Test;
import org.snbo.ssms.bean.Course;
import org.snbo.ssms.mapper.CourseMapper;
import org.snbo.ssms.utils.IdUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunbo
 * @date 2022-06-07-10:22
 */
class CourseMapperImplTest {
    private final CourseMapper courseMapper = new CourseMapperImpl();

    @Test
    public void save() {
        Course aaa0 = new Course(IdUtils.getStudentId(), "AAA");
        Course aaa1 = new Course(IdUtils.getStudentId(), "BBB");
        Course aaa2 = new Course(IdUtils.getStudentId(), "CCC");
        Course aaa3 = new Course(IdUtils.getStudentId(), "DDD");
        Course aaa4 = new Course(IdUtils.getStudentId(), "EEE");
        Course aaa5 = new Course(IdUtils.getStudentId(), "FFF");
        Course aaa6 = new Course(IdUtils.getStudentId(), "GGG");
        Course aaa7 = new Course(IdUtils.getStudentId(), "HHH");
        Course aaa8 = new Course(IdUtils.getStudentId(), "III");
        Course aaa9 = new Course(IdUtils.getStudentId(), "JJJ");

        List<Course> list = new ArrayList<>();
        list.add(aaa0);
        list.add(aaa1);
        list.add(aaa2);
        list.add(aaa3);
        list.add(aaa4);
        list.add(aaa5);
        list.add(aaa6);
        list.add(aaa7);
        list.add(aaa8);
        list.add(aaa9);

        System.out.println(courseMapper.saveAllInfo(list));

    }

    @Test
    public void getPage() {
    }

    @Test
    public void getById() {
        System.out.println(courseMapper.getInfoById(116));
    }
}