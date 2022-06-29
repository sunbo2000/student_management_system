package org.snbo.ssms.utils;

/**
 * @author sunbo
 * @date 2022-06-06-17:27
 */
public class StringUtils {
    public static Boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}
