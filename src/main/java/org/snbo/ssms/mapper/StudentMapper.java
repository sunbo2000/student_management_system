package org.snbo.ssms.mapper;

import org.snbo.ssms.bean.Student;
import org.snbo.ssms.bean.queryvo.StudentQuery;

import java.util.List;
import java.util.Map;


/**
 * @author sunbo
 * @date 2022-06-06-10:56
 */
public interface StudentMapper extends BaseMapper<Student> {


    /**
     * description: 根据查询条件分页查询学生信息
     *
     * @param current      当前页
     * @param size         每页记录数
     * @param studentQuery 查询条件信息
     * @return {@link List<Student>} 返回学生信息集合
     * @author sunbo
     * @date 2022/6/6 16:48
     */
    Map<String, Object> getStudentPage(Integer current, Integer size, StudentQuery studentQuery);

    /**
     * description: 根据查询信息比对学生信息,
     *
     * @param student      学生信息
     * @param studentQuery 查询条件
     * @return {@link boolean} 如果没有查询信息默认为true
     * @author sunbo
     * @date 2022/6/8 21:57
     */
    boolean match(Student student, StudentQuery studentQuery);
}
