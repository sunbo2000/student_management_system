package org.snbo.ssms.mapper.impl;

import org.junit.Test;
import org.snbo.ssms.bean.Student;
import org.snbo.ssms.bean.queryvo.StudentQuery;
import org.snbo.ssms.mapper.StudentMapper;
import org.snbo.ssms.utils.ObjStreamUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author sunbo
 * @date 2022-06-06-15:18
 */
class StudentMapperImplTest {

    private final StudentMapper studentMapper = new StudentMapperImpl();

    @Test
    void saveStudent() {
        Student student2 = new Student(333, "", 111, "a");
        studentMapper.saveInfo(student2);
        Object o = ObjStreamUtils.toDeserialize("studentSet.txt");
        System.out.println(o);
    }

    @Test
    void removeStudent() {
        Student student = new Student(222, "111", 222, "a");
        Boolean aBoolean = studentMapper.removeInfo(student);
        System.out.println(aBoolean);
        System.out.println(ObjStreamUtils.toDeserialize("studentSet.txt"));
    }


    @Test
    void updateStudent() {
        Student student = new Student(111, "999", 111, "b");
        Boolean aBoolean = studentMapper.updateInfo(student);
        System.out.println(aBoolean);
        System.out.println(ObjStreamUtils.toDeserialize("studentSet.txt"));
    }

    @Test
    void getStudentById() {
        Student studentById = studentMapper.getInfoById(222);
        System.out.println(studentById);
    }

    @Test
    void getStudentPage() {
        StudentQuery studentQuery = new StudentQuery();
        studentQuery.setName("www");
    }

    @Test
    void saveAllStudents() {
        Student student0 = new Student(111, "1000", 111, "www");
        Student student1 = new Student(111, "1001", 111, "eee");
        Student student2 = new Student(333, "1002", 111, "rrr");
        Student student3 = new Student(333, "1003", 111, "ttt");
        Student student4 = new Student(555, "1004", 111, "yyy");
        Student student5 = new Student(666, "1005", 222, "uuu");
        Student student6 = new Student(777, "1006", 222, "bbb");
        Student student7 = new Student(888, "1007", 222, "ccc");
        Student student8 = new Student(999, "1008", 444, "www");
        List<Student> list = new ArrayList<>();
        list.add(student0);
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        list.add(student7);
        list.add(student8);
        System.out.println(studentMapper.saveAllInfo(list));
    }

    @Test
    public void test() {
/*        CourseMapperImpl courseBaseMapper = new CourseMapperImpl("test1.txt");
        boolean aaaa = courseBaseMapper.saveInfo(new Course(1111, "aaaa"));
        System.out.println(aaaa);
        System.out.println(ObjStreamUtils.toDeserialize("test.txt"));*/
        StudentMapperImpl studentMapper1 = new StudentMapperImpl();
    }

    @Test
    public void Test2(){

    }
}
