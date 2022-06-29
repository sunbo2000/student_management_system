package org.snbo.ssms.view.studentview;

/**
 * @author sunbo
 * @date 2022-06-14-13:08
 */
public class CourseVo {
    Integer id;
    String title;

    public CourseVo(Integer id) {
        this.id = id;
    }

    public CourseVo(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public CourseVo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseVo setTitle(String title) {
        this.title = title;
        return this;
    }
}
