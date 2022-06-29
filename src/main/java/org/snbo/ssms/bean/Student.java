package org.snbo.ssms.bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunbo
 * @date 2022-06-06-10:52
 */
public class Student extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;
    private String major;
    private Integer classId;
    private String name;

    private Byte sex = 0;
    private Boolean status = false;
    private transient List<Course> courses = new ArrayList<>();

    private transient List<Double> scores = new ArrayList<>();

    public Student() {
    }

    public Student(Integer id) {
        super(id);
    }

    public Student(Integer id, String major, Integer classId, String name) {
        super(id);
        this.major = major;
        this.classId = classId;
        this.name = name;
    }

    public Student(Integer id, String major, Integer classId, String name, Byte sex, Boolean status) {
        super(id);
        this.major = major;
        this.classId = classId;
        this.name = name;
        this.sex = sex;
        this.status = status;
    }

    public Student(Integer id, String major, Integer classId, String name, Byte sex, Boolean status, List<Course> courses, List<Double> scores) {
        super(id);
        this.major = major;
        this.classId = classId;
        this.name = name;
        this.sex = sex;
        this.status = status;
        this.courses = courses;
        this.scores = scores;
    }

    public String getMajor() {
        return major;
    }

    public Student setMajor(String major) {
        this.major = major;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public Student setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public Byte getSex() {
        return sex;
    }

    public Student setSex(Byte sex) {
        this.sex = sex;
        return this;
    }

    public Integer getClassId() {
        return classId;
    }

    public Student setClassId(Integer classId) {
        this.classId = classId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public List<Course> getCourses() {
        if (this.courses == null) {
            this.courses = new ArrayList<>();
        }
        return courses;
    }

    public Student setCourses(List<Course> courses) {
        this.courses = courses;
        return this;
    }

    public Student addCourse(Course course) {
        if (this.courses == null) {
            this.courses = new ArrayList<>();
        }
        this.courses.add(course);
        return this;
    }

    public List<Double> getScores() {
        if (this.scores == null) {
            this.scores = new ArrayList<>();
        }
        return scores;
    }

    public Student setScores(List<Double> scores) {
        this.scores = scores;
        return this;
    }

    public Student addScore(Double score) {
        if (this.scores == null) {
            this.scores = new ArrayList<>();
        }
        this.scores.add(score);
        return this;
    }

    public Double getTotalScore() {
        if (this.scores == null) {
            return 0.0;
        } else {
            double sum = 0;
            for (Double score : this.scores) {
                sum += score;
            }
            return sum;
        }
    }

    public Double getAverageScore() {
        if (this.scores == null) {
            return 0.0;
        } else {
            DecimalFormat df = new DecimalFormat("#.0");
            return Double.valueOf(df.format(getTotalScore() / this.scores.size()));
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "major='" + major + '\'' +
                ", classId=" + classId +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                ", courses=" + courses +
                ", scores=" + scores +
                ", id=" + id +
                '}';
    }
}
