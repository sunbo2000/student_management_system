package org.snbo.ssms.bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author sunbo
 * @date 2022-06-09-17:41
 */
public abstract class BaseBean implements Serializable, Comparable<BaseBean> {
    private static final long serialVersionUID = 1905122041950251200L;
    protected Integer id;

    public BaseBean() {
    }

    public BaseBean(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public BaseBean setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseBean baseBean = (BaseBean) o;
        return id.equals(baseBean.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(BaseBean o) {
        return this.id.compareTo(o.getId());
    }
}
