package org.snbo.ssms.bean;

import java.io.Serializable;

/**
 * @author sunbo
 * @date 2022-06-06-22:00
 */
public class Course extends BaseBean implements Serializable {
    private static final long serialVersionUID = 1905122041950251208L;
    private String title;

    public Course() {
    }

    public Course(Integer id) {
        super(id);
    }

    public Course(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Course setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
