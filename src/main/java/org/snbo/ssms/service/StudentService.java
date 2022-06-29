package org.snbo.ssms.service;

import org.snbo.ssms.bean.Student;
import org.snbo.ssms.bean.queryvo.StudentQuery;

import java.util.List;
import java.util.Map;

/**
 * @author sunbo
 * @date 2022-06-07-13:14
 */
public interface StudentService extends BaseService<Student> {
    /**
     * description: 根据学生 id/学号 获取学生信息
     *
     * @param id 学生 id/学号
     * @return {@link Student} 返回学生信息
     * @author sunbo
     * @date 2022/6/6 16:37
     */
    Student getStudentById(Integer id);

    /**
     * description: 根据查询条件分页查询学生信息
     *
     * @param current      当前页
     * @param size         每页记录数
     * @param studentQuery 查询条件信息
     * @return {@link List <Student>} 返回学生信息集合
     * @author sunbo
     * @date 2022/6/6 16:48
     */
    Map<String,Object> getStudentPage(Integer current, Integer size, StudentQuery studentQuery);

    /**
     * description: 分页排序查询
     *
     * @param current      当前页
     * @param size         每页记录数
     * @param studentQuery 查询条件
     * @return {@link List<Student>}
     * @author sunbo
     * @date 2022/6/8 12:54
     */
    Map<String, Object> getStudentScorePage(Integer current, Integer size, StudentQuery studentQuery);

    /**
     * description:
     *
     * @param student 学生信息
     * @return {@link boolean}
     * @author sunbo
     * @date 2022/6/10 18:20
     */
    boolean updateScore(Student student);

    /**
     * description: 删除学生的成绩信息
     *
     * @param student 学生信息
     * @return {@link boolean} 删除成功返回 true
     * @author sunbo
     * @date 2022/6/12 22:54
     */
    boolean removeScoreInfo(Student student);

    /**
     * description: 添加学生成绩
     *
     * @param student 学生信息
     * @return {@link boolean}
     * @author sunbo
     * @date 2022/6/14 1:00
     */
    boolean saveStudentScore(Student student);
}
