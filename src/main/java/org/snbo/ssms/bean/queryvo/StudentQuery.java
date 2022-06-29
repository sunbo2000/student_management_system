package org.snbo.ssms.bean.queryvo;

/**
 * 查询学生信息条件类
 *
 * @author sunbo
 * @date 2022-06-06-17:05
 */
public class StudentQuery {
    private Integer studentId;

    private String major;
    private Integer classes;
    private String name;

    private Boolean status;

    private boolean sortName;

    private Integer sortScore = 0;

    public StudentQuery() {
    }

    public StudentQuery(Integer studentId, Integer classes, String name) {
        this.studentId = studentId;
        this.classes = classes;
        this.name = name;
    }

    public StudentQuery(Integer studentId, Integer classes, String name, boolean sortName, Integer sortScore) {
        this.studentId = studentId;
        this.classes = classes;
        this.name = name;
        this.sortName = sortName;
        this.sortScore = sortScore;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public StudentQuery setStudentId(Integer studentId) {
        this.studentId = studentId;
        return this;
    }

    public Integer getClasses() {
        return classes;
    }

    public StudentQuery setClasses(Integer classes) {
        this.classes = classes;
        return this;
    }

    public boolean isSortName() {
        return sortName;
    }

    public String getName() {
        return name;
    }

    public StudentQuery setName(String name) {
        this.name = name;
        return this;
    }

    public String getMajor() {
        return major;
    }

    public StudentQuery setMajor(String major) {
        this.major = major;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public StudentQuery setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public boolean getSortName() {
        return sortName;
    }

    public StudentQuery setSortName(boolean sortName) {
        this.sortName = sortName;
        return this;
    }

    public Integer getSortScore() {
        return sortScore;
    }

    public StudentQuery setSortScore(Integer sortScore) {
        this.sortScore = sortScore;
        return this;
    }
}
