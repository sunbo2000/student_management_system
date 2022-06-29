package org.snbo.ssms.utils;


import org.junit.Test;
import org.snbo.ssms.bean.Student;

/**
 * @author sunbo
 * @date 2022-06-06-14:39
 */
public class ObjStreamUtilsTest {

    @Test
    public void toSerialization() {
        Student student = new Student(111, "113333333", 111, "ddd");
        ObjStreamUtils.toSerialization(student, "student.txt");
    }

    @Test
    public void toDeserialize() {
        Object o = ObjStreamUtils.toDeserialize("student.txt");
        System.out.println(o);
    }

}