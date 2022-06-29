package org.snbo.ssms.view.studentview;

/**
 * @author sunbo
 * @date 2022-06-13-2:00
 */
public class StudentInfo {
    private Integer id;
    private String major;
    private Integer classId;
    private String name;
    private Byte sex = 0;
    private Boolean status = false;

    public StudentInfo() {
    }

    public StudentInfo(Integer id) {
        this.id = id;
    }

    public StudentInfo(Integer id, String major, Integer classId, String name, Byte sex, Boolean status) {
        this.id = id;
        this.major = major;
        this.classId = classId;
        this.name = name;
        this.sex = sex;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public StudentInfo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMajor() {
        return major;
    }

    public StudentInfo setMajor(String major) {
        this.major = major;
        return this;
    }

    public Integer getClassId() {
        return classId;
    }

    public StudentInfo setClassId(Integer classId) {
        this.classId = classId;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentInfo setName(String name) {
        this.name = name;
        return this;
    }

    public Byte getSex() {
        return sex;
    }

    public StudentInfo setSex(Byte sex) {
        this.sex = sex;
        return this;
    }

    public Boolean getStatus() {
        return status;
    }

    public StudentInfo setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", major='" + major + '\'' +
                ", classId=" + classId +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", status=" + status +
                '}';
    }
}
