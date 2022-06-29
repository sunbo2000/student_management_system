package org.snbo.ssms.controller;

import org.junit.Test;
import org.snbo.ssms.bean.Course;
import org.snbo.ssms.bean.CourseStudent;
import org.snbo.ssms.bean.Student;
import org.snbo.ssms.bean.queryvo.StudentQuery;
import org.snbo.ssms.mapper.impl.CourseMapperImpl;
import org.snbo.ssms.mapper.impl.CourseStudentMapperImpl;
import org.snbo.ssms.mapper.impl.StudentMapperImpl;
import org.snbo.ssms.service.CourseService;
import org.snbo.ssms.service.CourseStudentService;
import org.snbo.ssms.service.StudentService;
import org.snbo.ssms.service.impl.CourseServiceImpl;
import org.snbo.ssms.service.impl.CourseStudentServiceImpl;
import org.snbo.ssms.service.impl.StudentServiceImpl;
import org.snbo.ssms.utils.IdUtils;
import org.snbo.ssms.utils.Result;
import org.snbo.ssms.utils.SexUtils;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @author sunbo
 * @date 2022-06-08-9:38
 */
class StudentControllerTest {

    StudentController controller = new StudentController();

    StudentService studentService = new StudentServiceImpl(new StudentMapperImpl());
    CourseService courseService = new CourseServiceImpl(new CourseMapperImpl());
    CourseStudentService cs = new CourseStudentServiceImpl(new CourseStudentMapperImpl());


    @Test
    void getStudentScorePage() {
        Result result = controller.getStudentScorePage(1, 5, new StudentQuery(null, null, null, false, -1));
        Map<String, Object> data = result.getData();
        List<Student> list = (List<Student>) data.get("studentScoreList");
        list.forEach(System.out::println);
    }

    @Test
    void ttt() {
        Result studentScorePage = controller.getStudentScorePage(1, 10, new StudentQuery(null, null, null, false, 4));
        List<Student> studentScoreList = (List<Student>) studentScorePage.getData().get("studentScoreList");
        studentScoreList.forEach(System.out::println);
    }


    @Test
    void addCourse() {
/*        Course course1 = new Course(IdUtils.getCourseId(), "AAA");
        Course course2 = new Course(IdUtils.getCourseId(), "BBB");XXXX
        Course course3 = new Course(IdUtils.getCourseId(), "CCC");
        Course course4 = new Course(IdUtils.getCourseId(), "DDD");
        Course course5 = new Course(IdUtils.getCourseId(), "EEE");
        Course course6 = new Course(IdUtils.getCourseId(), "FFF");
        Course course7 = new Course(IdUtils.getCourseId(), "GGG");
        Course course8 = new Course(IdUtils.getCourseId(), "HHH");
        Course course9 = new Course(IdUtils.getCourseId(), "III");
        Course course10 = new Course(IdUtils.getCourseId(), "JJJ");

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        courses.add(course7);
        courses.add(course8);
        courses.add(course9);
        courses.add(course10);

        courseService.saveAll(courses);*/

        courseService.getAllOfList().forEach(System.out::println);
    }

    @Test
    void addStudent() {
        Student student1 = new Student(IdUtils.getStudentId(), null, null, "张三", SexUtils.MAN, true);
        Student student2 = new Student(IdUtils.getStudentId(), null, null, "李四", SexUtils.WOMAN, true);
        Student student3 = new Student(IdUtils.getStudentId(), null, null, "王五", SexUtils.MAN, true);
        Student student4 = new Student(IdUtils.getStudentId(), null, null, "赵六", SexUtils.MAN, true);
        Student student5 = new Student(IdUtils.getStudentId(), null, null, "陈琦", SexUtils.WOMAN, true);
        Student student6 = new Student(IdUtils.getStudentId(), null, null, "王霸", SexUtils.MAN, true);
        Student student7 = new Student(IdUtils.getStudentId(), null, null, "蹇复", SexUtils.WOMAN, true);
        Student student8 = new Student(IdUtils.getStudentId(), null, null, "蹇杨", SexUtils.MAN, true);
        Student student9 = new Student(IdUtils.getStudentId(), null, null, "张青", SexUtils.WOMAN, true);
        Student student10 = new Student(IdUtils.getStudentId(), null, null, "邓周", SexUtils.MAN, true);

        studentService.save(student1);
        studentService.save(student2);
        studentService.save(student3);
        studentService.save(student4);
        studentService.save(student5);
        studentService.save(student6);
        studentService.save(student7);
        studentService.save(student8);
        studentService.save(student9);
        studentService.save(student10);

        studentService.getAllOfList().forEach(System.out::println);
    }

    @Test
    void conjunction() {
        Random random = new Random();
        CourseStudent cs1 = new CourseStudent(IdUtils.getStuCouId(), 2022100, 1010100, (double) random.nextInt(100));
        CourseStudent cs2 = new CourseStudent(IdUtils.getStuCouId(), 2022100, 1010101, (double) random.nextInt(100));
        CourseStudent cs3 = new CourseStudent(IdUtils.getStuCouId(), 2022100, 1010102, (double) random.nextInt(100));
        CourseStudent cs4 = new CourseStudent(IdUtils.getStuCouId(), 2022100, 1010103, (double) random.nextInt(100));
        CourseStudent cs5 = new CourseStudent(IdUtils.getStuCouId(), 2022100, 1010104, (double) random.nextInt(100));
        CourseStudent cs6 = new CourseStudent(IdUtils.getStuCouId(), 2022100, 1010105, (double) random.nextInt(100));
        CourseStudent cs7 = new CourseStudent(IdUtils.getStuCouId(), 2022100, 1010106, (double) random.nextInt(100));

        CourseStudent acs1 = new CourseStudent(IdUtils.getStuCouId(), 2022101, 1010100, (double) random.nextInt(100));
        CourseStudent acs2 = new CourseStudent(IdUtils.getStuCouId(), 2022101, 1010101, (double) random.nextInt(100));
        CourseStudent acs3 = new CourseStudent(IdUtils.getStuCouId(), 2022101, 1010102, (double) random.nextInt(100));
        CourseStudent acs4 = new CourseStudent(IdUtils.getStuCouId(), 2022101, 1010103, (double) random.nextInt(100));
        CourseStudent acs5 = new CourseStudent(IdUtils.getStuCouId(), 2022101, 1010104, (double) random.nextInt(100));
        CourseStudent acs6 = new CourseStudent(IdUtils.getStuCouId(), 2022101, 1010105, (double) random.nextInt(100));
        CourseStudent acs7 = new CourseStudent(IdUtils.getStuCouId(), 2022101, 1010106, (double) random.nextInt(100));

        CourseStudent bcs1 = new CourseStudent(IdUtils.getStuCouId(), 2022102, 1010100, (double) random.nextInt(100));
        CourseStudent bcs2 = new CourseStudent(IdUtils.getStuCouId(), 2022102, 1010101, (double) random.nextInt(100));
        CourseStudent bcs3 = new CourseStudent(IdUtils.getStuCouId(), 2022102, 1010102, (double) random.nextInt(100));
        CourseStudent bcs4 = new CourseStudent(IdUtils.getStuCouId(), 2022102, 1010103, (double) random.nextInt(100));
        CourseStudent bcs5 = new CourseStudent(IdUtils.getStuCouId(), 2022102, 1010104, (double) random.nextInt(100));
        CourseStudent bcs6 = new CourseStudent(IdUtils.getStuCouId(), 2022102, 1010105, (double) random.nextInt(100));
        CourseStudent bcs7 = new CourseStudent(IdUtils.getStuCouId(), 2022102, 1010106, (double) random.nextInt(100));

        CourseStudent accs1 = new CourseStudent(IdUtils.getStuCouId(), 2022103, 1010100, (double) random.nextInt(100));
        CourseStudent accs2 = new CourseStudent(IdUtils.getStuCouId(), 2022103, 1010101, (double) random.nextInt(100));
        CourseStudent accs3 = new CourseStudent(IdUtils.getStuCouId(), 2022103, 1010102, (double) random.nextInt(100));
        CourseStudent accs4 = new CourseStudent(IdUtils.getStuCouId(), 2022103, 1010103, (double) random.nextInt(100));
        CourseStudent accs5 = new CourseStudent(IdUtils.getStuCouId(), 2022103, 1010104, (double) random.nextInt(100));
        CourseStudent accs6 = new CourseStudent(IdUtils.getStuCouId(), 2022103, 1010105, (double) random.nextInt(100));
        CourseStudent accs7 = new CourseStudent(IdUtils.getStuCouId(), 2022103, 1010106, (double) random.nextInt(100));

        CourseStudent cs1d = new CourseStudent(IdUtils.getStuCouId(), 2022104, 1010100, (double) random.nextInt(100));
        CourseStudent cs2d = new CourseStudent(IdUtils.getStuCouId(), 2022104, 1010101, (double) random.nextInt(100));
        CourseStudent cs3d = new CourseStudent(IdUtils.getStuCouId(), 2022104, 1010102, (double) random.nextInt(100));
        CourseStudent cs4d = new CourseStudent(IdUtils.getStuCouId(), 2022104, 1010103, (double) random.nextInt(100));
        CourseStudent cs5d = new CourseStudent(IdUtils.getStuCouId(), 2022104, 1010104, (double) random.nextInt(100));
        CourseStudent cs6d = new CourseStudent(IdUtils.getStuCouId(), 2022104, 1010105, (double) random.nextInt(100));
        CourseStudent cs7d = new CourseStudent(IdUtils.getStuCouId(), 2022104, 1010106, (double) random.nextInt(100));

        CourseStudent acs1e = new CourseStudent(IdUtils.getStuCouId(), 2022105, 1010100, (double) random.nextInt(100));
        CourseStudent acs2e = new CourseStudent(IdUtils.getStuCouId(), 2022105, 1010101, (double) random.nextInt(100));
        CourseStudent acs3e = new CourseStudent(IdUtils.getStuCouId(), 2022105, 1010102, (double) random.nextInt(100));
        CourseStudent acs4e = new CourseStudent(IdUtils.getStuCouId(), 2022105, 1010103, (double) random.nextInt(100));
        CourseStudent acs5e = new CourseStudent(IdUtils.getStuCouId(), 2022105, 1010104, (double) random.nextInt(100));
        CourseStudent acs6e = new CourseStudent(IdUtils.getStuCouId(), 2022105, 1010105, (double) random.nextInt(100));
        CourseStudent acs7e = new CourseStudent(IdUtils.getStuCouId(), 2022105, 1010106, (double) random.nextInt(100));

        CourseStudent acs1r = new CourseStudent(IdUtils.getStuCouId(), 2022106, 1010100, (double) random.nextInt(100));
        CourseStudent acs2r = new CourseStudent(IdUtils.getStuCouId(), 2022106, 1010101, (double) random.nextInt(100));
        CourseStudent acs3r = new CourseStudent(IdUtils.getStuCouId(), 2022106, 1010102, (double) random.nextInt(100));
        CourseStudent acs4r = new CourseStudent(IdUtils.getStuCouId(), 2022106, 1010103, (double) random.nextInt(100));
        CourseStudent acs5r = new CourseStudent(IdUtils.getStuCouId(), 2022106, 1010104, (double) random.nextInt(100));
        CourseStudent acs6r = new CourseStudent(IdUtils.getStuCouId(), 2022106, 1010105, (double) random.nextInt(100));
        CourseStudent acs7r = new CourseStudent(IdUtils.getStuCouId(), 2022106, 1010106, (double) random.nextInt(100));

        cs.save(cs1);
        cs.save(cs2);
        cs.save(cs3);
        cs.save(cs4);
        cs.save(cs5);
        cs.save(cs6);
        cs.save(cs7);

        cs.save(acs1);
        cs.save(acs2);
        cs.save(acs3);
        cs.save(acs4);
        cs.save(acs5);
        cs.save(acs6);
        cs.save(acs7);

        cs.save(bcs1);
        cs.save(bcs2);
        cs.save(bcs3);
        cs.save(bcs4);
        cs.save(bcs5);
        cs.save(bcs6);
        cs.save(bcs7);

        cs.save(accs1);
        cs.save(accs2);
        cs.save(accs3);
        cs.save(accs4);
        cs.save(accs5);
        cs.save(accs6);
        cs.save(accs7);

        cs.save(cs1d);
        cs.save(cs2d);
        cs.save(cs3d);
        cs.save(cs4d);
        cs.save(cs5d);
        cs.save(cs6d);
        cs.save(cs7d);

        cs.save(acs1e);
        cs.save(acs2e);
        cs.save(acs3e);
        cs.save(acs4e);
        cs.save(acs5e);
        cs.save(acs6e);
        cs.save(acs7e);


        cs.save(acs1r);
        cs.save(acs2r);
        cs.save(acs3r);
        cs.save(acs4r);
        cs.save(acs5r);
        cs.save(acs6r);
        cs.save(acs7r);

        cs.getAllOfList().forEach(System.out::println);
    }

    @Test
    void updateStudentScore() {
        Student q = new Student(2022100, null, null, "张三");
        q.addCourse(new Course(1010100));
        q.addCourse(new Course(1010101));
        q.addCourse(new Course(1010102));
        q.addCourse(new Course(1010103));
        q.addCourse(new Course(1010104));
        q.addCourse(new Course(1010105));
        q.addCourse(new Course(1010106));
        q.addScore(34.0);
        q.addScore(34.0);
        q.addScore(34.0);
        q.addScore(34.0);
        q.addScore(34.0);
        q.addScore(34.0);
        q.addScore(34.0);
        controller.updateStudentScore(q);

    }
}