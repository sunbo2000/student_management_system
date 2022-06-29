package org.snbo.ssms.service.impl;

import org.snbo.ssms.bean.Course;
import org.snbo.ssms.bean.CourseStudent;
import org.snbo.ssms.bean.Student;
import org.snbo.ssms.bean.queryvo.StudentQuery;
import org.snbo.ssms.mapper.StudentMapper;
import org.snbo.ssms.mapper.impl.CourseMapperImpl;
import org.snbo.ssms.mapper.impl.CourseStudentMapperImpl;
import org.snbo.ssms.service.CourseService;
import org.snbo.ssms.service.CourseStudentService;
import org.snbo.ssms.service.StudentService;
import org.snbo.ssms.utils.IdUtils;

import java.util.*;


/**
 * @author sunbo
 * @date 2022-06-07-22:32
 */
public class StudentServiceImpl extends BaseServiceImpl<StudentMapper, Student> implements StudentService {

    private final CourseStudentService csService;
    private final CourseService courseService;

    {
        csService = new CourseStudentServiceImpl(new CourseStudentMapperImpl());
        courseService = new CourseServiceImpl(new CourseMapperImpl());
    }

    public StudentServiceImpl(StudentMapper baseMapper) {
        super(baseMapper);
    }

    @Override
    public Student getStudentById(Integer id) {
        return baseMapper.getInfoById(id);
    }

    @Override
    public Map<String, Object> getStudentPage(Integer current, Integer size, StudentQuery studentQuery) {
        return baseMapper.getStudentPage(current, size, studentQuery);
    }

    @Override
    public Map<String, Object> getStudentScorePage(Integer current,
                                                   Integer size,
                                                   StudentQuery studentQuery) {
        Map<Integer, Student> studentMap = baseMapper.getAllInfoOfMap();
        Map<Integer, CourseStudent> csMap = csService.getAllOfMap();
        Map<Integer, Course> courseMap = courseService.getAllOfMap();
        if (csMap == null || studentMap == null || courseMap == null) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("studentScoreList", null);
            map.put("total", 0);
            return map;
        }

        //筛选
        csMap.forEach((key, cs) -> {
            //获取学生 id
            Student student = studentMap.get(cs.getStudentId());
            if (baseMapper.match(student, studentQuery)) {
                studentMap.put(student.getId(),
                        student.addCourse(courseMap.get(cs.getCourseId()))
                                .addScore(cs.getScore()));
            }
        });
        List<Student> list = new ArrayList<>(10);
        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            Student stu = entry.getValue();
            if (!stu.getCourses().isEmpty()) {
                list.add(stu);
            }
        }

        //排序
        if (studentQuery != null) {
            if (studentQuery.getSortName()) {
                list.sort(Comparator.comparing(Student::getName));
            } else if (studentQuery.getSortScore() != 0) {
                int index;
                if (studentQuery.getSortScore() == 100) {
                    list.sort(Comparator.comparing(Student::getTotalScore));
                } else if (studentQuery.getSortScore() == -100) {
                    list.sort(Comparator.comparing(stu -> -stu.getTotalScore()));
                } else if (studentQuery.getSortScore() > 0) {
                    index = studentQuery.getSortScore() - 1;
                    list.sort(Comparator.comparing(stu -> stu.getScores().get(index)));
                } else {
                    index = (-studentQuery.getSortScore()) - 1;
                    list.sort(Comparator.comparing(stu -> -stu.getScores().get(index)));
                }
            }
        }

        //分页
        List<Student> listPage = new ArrayList<>(size);
        baseMapper.transferPage(current, size, list, listPage);

        Map<String, Object> map = new HashMap<>(2);
        map.put("studentScoreList", listPage);
        map.put("total", list.size());
        return map;
    }

    @Override
    public boolean updateScore(Student student) {
        List<CourseStudent> list = csService.getAllOfList();
        list.forEach(cs -> {
            if (cs.getStudentId().equals(student.getId())) {
                List<Course> courses = student.getCourses();
                int i = 0;
                for (Course cou : courses) {
                    if (cou.getId().equals(cs.getCourseId())) {
                        cs.setScore(student.getScores().get(i));
                        csService.update(cs);
                    }
                    i++;
                }
            }
        });
        return true;
    }

    @Override
    public boolean removeScoreInfo(Student student) {
        List<CourseStudent> list = csService.getAllOfList();
        List<CourseStudent> removeList = new ArrayList<>(5);
        list.forEach(cs -> {
            if (student.getId().equals(cs.getStudentId())) {
                removeList.add(cs);
            }
        });
        return csService.removeAll(removeList);
    }

    @Override
    public boolean saveStudentScore(Student student) {
        List<CourseStudent> list = new ArrayList<>(7);
        for (int i = 0; i < student.getCourses().size(); i++) {
            list.add(new CourseStudent(IdUtils.getStuCouId(),
                    student.getId(),
                    student.getCourses().get(i).getId(),
                    student.getScores().get(i)));
        }
        boolean saveAll = csService.saveAll(list);
        if (saveAll) {
            return baseMapper.updateInfo(student);
        } else {
            return false;
        }
    }
}
