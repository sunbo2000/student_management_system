package org.snbo.ssms.bean;

import java.io.Serializable;


/**
 * @author sunbo
 * @date 2022-06-07-10:43
 */
public class CourseStudent extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1905122041950251209L;
    private Integer studentId;
    private Integer courseId;
    private Double score;

    public CourseStudent(Integer id) {
        super(id);
    }

    public CourseStudent(Integer id, Integer studentId, Integer courseId, Double score) {
        super(id);
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }


    public Integer getStudentId() {
        return studentId;
    }

    public CourseStudent setStudentId(Integer studentId) {
        this.studentId = studentId;
        return this;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public CourseStudent setCourseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }

    public Double getScore() {
        return score;
    }

    public CourseStudent setScore(Double score) {
        this.score = score;
        return this;
    }

    @Override
    public String toString() {
        return "CourseStudent{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", score=" + score +
                '}';
    }
}
