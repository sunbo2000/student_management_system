package org.snbo.ssms.mapper.impl;

import org.snbo.ssms.bean.Student;
import org.snbo.ssms.bean.queryvo.StudentQuery;
import org.snbo.ssms.mapper.StudentMapper;
import org.snbo.ssms.utils.FilenameUtils;
import org.snbo.ssms.utils.StringUtils;

import java.util.*;

/**
 * @author sunbo
 * @date 2022-06-06-10:59
 */

public class StudentMapperImpl extends BaseMapperImpl<Student> implements StudentMapper {

    public StudentMapperImpl() {
        super(FilenameUtils.getStudentFilename());
    }

    @Override
    public Map<String, Object> getStudentPage(Integer current, Integer size, StudentQuery studentQuery) {
        HashMap<String, Object> map = new HashMap<>(2);
        TreeSet<Student> studentSet = getInfoSet();
        if (studentSet == null) {
            map.put("studentList",null);
            map.put("total",0);
            return map;
        }

        //筛选
        List<Student> list = new ArrayList<>();
        studentSet.forEach(student -> {
            if (matchStu(student, studentQuery)) {
                list.add(student);
            }
        });

        //排序
        if (studentQuery != null) {
            if (studentQuery.getSortName()) {
                list.sort(Comparator.comparing(Student::getName));
            }
        }

        //分页
        List<Student> listPage = new ArrayList<>(size);
        transferPage(current, size, list, listPage);


        map.put("studentList", listPage);
        map.put("total", list.size());
        return map;
    }

    @Override
    public boolean match(Student student, StudentQuery studentQuery) {
        if (studentQuery == null) {
            return true;
        }
        if (!StringUtils.isEmpty(studentQuery.getStudentId())) {
            if (!studentQuery.getStudentId().equals(student.getId())) {
                return false;
            }
        }
        if (!StringUtils.isEmpty(studentQuery.getName())) {
            if (!studentQuery.getName().equals(student.getName())) {
                return false;
            }
        }
        if (!StringUtils.isEmpty(studentQuery.getClasses())) {
            if (!studentQuery.getClasses().equals(student.getClassId())) {
                return false;
            }
        }
        return true;
    }

    public boolean matchStu(Student student, StudentQuery studentQuery) {
        if (studentQuery == null){
            return true;
        }
        boolean match = match(student, studentQuery);
        if (!match) {
            return false;
        }
        if (!StringUtils.isEmpty(studentQuery.getMajor())) {
            if (!studentQuery.getMajor().equals(student.getMajor())) {
                return false;
            }
        }
        if (!StringUtils.isEmpty(studentQuery.getStatus())) {
            if (!studentQuery.getStatus().equals(student.getStatus())) {
                return false;
            }
        }
        return true;
    }
}
