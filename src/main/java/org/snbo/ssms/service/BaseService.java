package org.snbo.ssms.service;

import org.snbo.ssms.bean.BaseBean;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author sunbo
 * @date 2022-06-07-21:51
 */
public interface BaseService<T extends BaseBean> {
    /**
     * description: 保存信息到文件中
     *
     * @param t 课程信息
     * @return {@link boolean} 添加成功返回 true
     * @author sunbo
     * @date 2022/6/6 22:16
     */
    boolean save(T t);

    /**
     * description: 批量添加课程信息
     *
     * @param tList 信息集合
     * @return {@link boolean} 添加成功返回 true
     * @author sunbo
     * @date 2022/6/6 18:19
     */
    boolean saveAll(List<T> tList);

    /**
     * description: 根据 id 从文件中删除信息
     *
     * @param t 信息
     * @return {@link boolean} 删除成功返回 true
     * @author sunbo
     * @date 2022/6/6 15:20
     */
    boolean remove(T t);

    /**
     * description: 从文件中删除信息集合中包含的数据
     *
     * @param tList 信息集合
     * @return {@link boolean} 删除成功返回 true
     * @author sunbo
     * @date 2022/6/7 12:38
     */
    boolean removeAll(List<T> tList);

    /**
     * description: 根据 id 修改信息
     *
     * @param t 信息
     * @return {@link boolean} 修改成功返回 true
     * @author sunbo
     * @date 2022/6/6 16:01
     */
    boolean update(T t);

    /**
     * description: 根据课程 id 获取课程信息
     *
     * @param id 课程 id
     * @return {@link T} 返回信息
     * @author sunbo
     * @date 2022/6/6 16:37
     */
    T getById(Integer id);

    /**
     * description: 根据查询条件分页查询信息
     *
     * @param current    当前页
     * @param size       每页记录数
     * @param comparator 排序条件
     * @return {@link List<T>} 返回信息集合
     * @author sunbo
     * @date 2022/6/6 16:48
     */
    List<T> getPage(Integer current, Integer size, Comparator<T> comparator);

    /**
     * description:获取所有信息集合
     *
     * @return {@link List<T>} 返回 List 集合
     * @author sunbo
     * @date 2022/6/9 20:12
     */
    List<T> getAllOfList();

    /**
     * description: 获取所有信息集合
     *
     * @return {@link Map< Integer,T>} 返回 Map 集合
     * @author sunbo
     * @date 2022/6/8 19:41
     */
    Map<Integer, T> getAllOfMap();
}
