package org.snbo.ssms.controller;

import org.snbo.ssms.bean.Course;
import org.snbo.ssms.mapper.impl.CourseMapperImpl;
import org.snbo.ssms.service.CourseService;
import org.snbo.ssms.service.impl.CourseServiceImpl;
import org.snbo.ssms.utils.Result;

import java.util.List;

/**
 * @author sunbo
 * @date 2022-06-13-0:59
 */
public class CourseController {
    private final CourseService courseService;

    {
        courseService = new CourseServiceImpl(new CourseMapperImpl());
    }

    public Result getAllCourse() {
        List<Course> list = courseService.getAllOfList();
        return Result.ok().data("courseList", list);
    }

    public Result getCoursePage(Integer current, Integer size) {
        List<Course> page = courseService.getPage(current, size, null);
        return Result.ok().data("coursePageList", page);
    }

    public Result saveCourse(Course course) {
        boolean save = courseService.save(course);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    public Result saveAllCourse(List<Course> list) {
        boolean b = courseService.saveAll(list);
        if (b) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    public Result removeCourse(Course course) {
        boolean remove = courseService.remove(course);
        if (remove) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    public Result removeAll(List<Course> list) {
        boolean b = courseService.removeAll(list);
        if (b) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    public Result updateCourse(Course course) {
        boolean update = courseService.update(course);
        if (update) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}
