package org.snbo.ssms.controller;

import org.snbo.ssms.bean.Student;
import org.snbo.ssms.bean.queryvo.StudentQuery;
import org.snbo.ssms.mapper.impl.StudentMapperImpl;
import org.snbo.ssms.service.StudentService;
import org.snbo.ssms.service.impl.StudentServiceImpl;
import org.snbo.ssms.utils.Result;

import java.util.List;
import java.util.Map;

/**
 * @author sunbo
 * @date 2022-06-07-22:54
 */
public class StudentController {
    private final StudentService studentService;

    {
        studentService = new StudentServiceImpl(new StudentMapperImpl());
    }

    public Result getStudentById(Integer id) {
        Student studentById = studentService.getStudentById(id);
        return Result.ok().data("student", studentById);
    }

    public Result getStudentPage(Integer current, Integer size, StudentQuery studentQuery) {
        Map<String, Object> map = studentService.getStudentPage(current, size, studentQuery);
        return Result.ok().data(map);
    }

    public Result getStudentScorePage(Integer current, Integer size, StudentQuery studentQuery) {
        Map<String, Object> map = studentService.getStudentScorePage(current, size, studentQuery);
        return Result.ok().data(map);
    }

    public Result saveStudentScore(Student student) {
        boolean save = studentService.saveStudentScore(student);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    public Result saveStudent(Student student) {
        if (studentService.save(student)) {
            return Result.ok().message("添加成功");
        } else {
            return Result.error().message("添加学生信息失败");
        }
    }

    public Result saveAllStudent(List<Student> list) {
        if (studentService.saveAll(list)) {
            return Result.ok().message("添加成功");
        } else {
            return Result.error().message("添加学生信息失败");
        }
    }

    public Result removeStudent(Student student) {
        boolean remove = studentService.remove(student);
        boolean remove1 = studentService.removeScoreInfo(student);
        if (remove && remove1) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("删除失败");
        }
    }

    public Result removeAllStudent(List<Student> list) {
        if (studentService.removeAll(list)) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("删除失败");
        }
    }

    public Result updateStudent(Student student) {
        if (studentService.update(student)) {
            return Result.ok().message("更新信息成功");
        } else {
            return Result.error().message("更新信息失败");
        }
    }

    public Result updateStudentScore(Student student) {
        studentService.updateScore(student);
        return Result.ok();
    }

    public Result removeStudentScore(Student student) {
        boolean remove = studentService.removeScoreInfo(student);
        if (remove) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

}
