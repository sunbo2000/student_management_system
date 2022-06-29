package org.snbo.ssms.view.studentview;


/**
 * @author sunbo
 * @date 2022-06-01-1:45
 */

public class StudentVo {

    private Integer id;
    private String major;
    private Integer classes;
    private String name;

    private String title1;
    private Double course1;

    private String title2;
    private Double course2;

    private String title3;
    private Double course3;

    private String title4;
    private Double course4;

    private String title5;
    private Double course5;

    private String title6;
    private Double course6;

    private String title7;
    private Double course7;

    private Double average;
    private Double total;

    public StudentVo() {
    }

    public StudentVo(Integer id, String major, Integer classes, String name) {
        this.id = id;
        this.major = major;
        this.classes = classes;
        this.name = name;
    }

    public StudentVo(Integer id, String major, Integer classes, String name, String title1, Double course1, String title2, Double course2, String title3, Double course3, String title4, Double course4, String title5, Double course5, String title6, Double course6, String title7, Double course7) {
        this.id = id;
        this.major = major;
        this.classes = classes;
        this.name = name;
        this.title1 = title1;
        this.course1 = course1;
        this.title2 = title2;
        this.course2 = course2;
        this.title3 = title3;
        this.course3 = course3;
        this.title4 = title4;
        this.course4 = course4;
        this.title5 = title5;
        this.course5 = course5;
        this.title6 = title6;
        this.course6 = course6;
        this.title7 = title7;
        this.course7 = course7;
    }

    public Integer getId() {
        return id;
    }

    public StudentVo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMajor() {
        return major;
    }

    public StudentVo setMajor(String major) {
        this.major = major;
        return this;
    }

    public Integer getClasses() {
        return classes;
    }

    public StudentVo setClasses(Integer classes) {
        this.classes = classes;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudentVo setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle1() {
        return title1;
    }

    public StudentVo setTitle1(String title1) {
        this.title1 = title1;
        return this;
    }

    public Double getCourse1() {
        return course1;
    }

    public StudentVo setCourse1(Double course1) {
        this.course1 = course1;
        return this;
    }

    public String getTitle2() {
        return title2;
    }

    public StudentVo setTitle2(String title2) {
        this.title2 = title2;
        return this;
    }

    public Double getCourse2() {
        return course2;
    }

    public StudentVo setCourse2(Double course2) {
        this.course2 = course2;
        return this;
    }

    public String getTitle3() {
        return title3;
    }

    public StudentVo setTitle3(String title3) {
        this.title3 = title3;
        return this;
    }

    public Double getCourse3() {
        return course3;
    }

    public StudentVo setCourse3(Double course3) {
        this.course3 = course3;
        return this;
    }

    public String getTitle4() {
        return title4;
    }

    public StudentVo setTitle4(String title4) {
        this.title4 = title4;
        return this;
    }

    public Double getCourse4() {
        return course4;
    }

    public StudentVo setCourse4(Double course4) {
        this.course4 = course4;
        return this;
    }

    public String getTitle5() {
        return title5;
    }

    public StudentVo setTitle5(String title5) {
        this.title5 = title5;
        return this;
    }

    public Double getCourse5() {
        return course5;
    }

    public StudentVo setCourse5(Double course5) {
        this.course5 = course5;
        return this;
    }

    public String getTitle6() {
        return title6;
    }

    public StudentVo setTitle6(String title6) {
        this.title6 = title6;
        return this;
    }

    public Double getCourse6() {
        return course6;
    }

    public StudentVo setCourse6(Double course6) {
        this.course6 = course6;
        return this;
    }

    public String getTitle7() {
        return title7;
    }

    public StudentVo setTitle7(String title7) {
        this.title7 = title7;
        return this;
    }

    public Double getCourse7() {
        return course7;
    }

    public StudentVo setCourse7(Double course7) {
        this.course7 = course7;
        return this;
    }

    public Double getAverage() {
        return average;
    }

    public StudentVo setAverage(Double average) {
        this.average = average;
        return this;
    }

    public Double getTotal() {
        return total;
    }

    public StudentVo setTotal(Double total) {
        this.total = total;
        return this;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "id=" + id +
                ", major='" + major + '\'' +
                ", classes=" + classes +
                ", name='" + name + '\'' +
                ", title1='" + title1 + '\'' +
                ", course1=" + course1 +
                ", title2='" + title2 + '\'' +
                ", course2=" + course2 +
                ", title3='" + title3 + '\'' +
                ", course3=" + course3 +
                ", title4='" + title4 + '\'' +
                ", course4=" + course4 +
                ", title5='" + title5 + '\'' +
                ", course5=" + course5 +
                ", title6='" + title6 + '\'' +
                ", course6=" + course6 +
                ", title7='" + title7 + '\'' +
                ", course7=" + course7 +
                ", average=" + average +
                ", total=" + total +
                '}';
    }
}
