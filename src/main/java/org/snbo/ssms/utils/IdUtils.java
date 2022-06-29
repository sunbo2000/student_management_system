package org.snbo.ssms.utils;

import org.snbo.ssms.bean.*;

import java.util.TreeSet;

/**
 * @author sunbo
 * @date 2022-06-07-11:16
 */
public class IdUtils {
    private static Integer sid = 2022000;
    private static Integer cid = 1010000;
    private static Integer csId = 1000000;
    private static Integer uId = 1212000;


    static {
        sid = getNowMaxId(Student.class, FilenameUtils.getStudentFilename());
        cid = getNowMaxId(Course.class, FilenameUtils.getCourseFilename());
        csId = getNowMaxId(CourseStudent.class, FilenameUtils.getCourseStudentFilename());
        uId = getNowMaxId(User.class, FilenameUtils.getUserFilename());
    }

    private IdUtils() {
    }

    public static Integer getStudentId() {
        return 2022000 + sid++;
    }

    public static Integer getCourseId() {
        return 1010000 + cid++;
    }

    public static Integer getStuCouId() {
        return 1000000 + csId++;
    }

    public static Integer getUserId() {
        return 1212000 + uId++;
    }

    private static <T extends BaseBean> Integer getNowMaxId(Class<T> tClass, String filename) {
        TreeSet<T> tSet = (TreeSet<T>) ObjStreamUtils.toDeserialize(filename);
        if (tSet == null) {
            return 100;
        }
        T last = tSet.last();
        return last.getId() % 1000 + 1;
    }
}
