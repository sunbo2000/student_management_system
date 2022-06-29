package org.snbo.ssms.utils;

import java.util.ResourceBundle;

/**
 * @author sunbo
 * @date 2022-06-08-13:01
 */
public class FilenameUtils {
    private static final String PROPERTIES_FILE = "filename";
    private static final String STUDENT_FILENAME = "sName";
    private static final String COURSE_FILENAME = "cName";
    private static final String COURSE_STUDENT_FILENAME = "csName";
    private static final String USER_FILENAME = "user";
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(PROPERTIES_FILE);

    public static String getStudentFilename() {
        return BUNDLE.getString(STUDENT_FILENAME);
    }
    public static String getCourseFilename() {
        return BUNDLE.getString(COURSE_FILENAME);
    }

    public static String getCourseStudentFilename() {
        return BUNDLE.getString(COURSE_STUDENT_FILENAME);
    }

    public static String getUserFilename() {
        return BUNDLE.getString(USER_FILENAME);
    }
}
