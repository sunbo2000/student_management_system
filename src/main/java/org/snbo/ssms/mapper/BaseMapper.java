package org.snbo.ssms.mapper;


import org.snbo.ssms.bean.BaseBean;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author sunbo
 * @date 2022-06-07-0:20
 */
public interface BaseMapper<T extends BaseBean> {
    /**
     * description: 保存信息到文件中
     *
     * @param t 课程信息
     * @return {@link boolean} 添加成功返回 true
     * @author sunbo
     * @date 2022/6/6 22:16
     */
    boolean saveInfo(T t);

    /**
     * description: 批量添加课程信息
     *
     * @param infoList 信息集合
     * @return {@link boolean} 添加成功返回 true
     * @author sunbo
     * @date 2022/6/6 18:19
     */
    boolean saveAllInfo(List<T> infoList);

    /**
     * description: 根据 id 从文件中删除信息
     *
     * @param t 信息
     * @return {@link boolean} 删除成功返回 true
     * @author sunbo
     * @date 2022/6/6 15:20
     */
    boolean removeInfo(T t);

    /**
     * description: 从文件中删除信息集合中包含的数据
     *
     * @param t 信息集合
     * @return {@link boolean} 删除成功返回 true
     * @author sunbo
     * @date 2022/6/7 12:38
     */
    boolean removeAllInfo(List<T> t);

    /**
     * description: 根据 id 修改信息
     *
     * @param t 信息
     * @return {@link boolean} 修改成功返回 true
     * @author sunbo
     * @date 2022/6/6 16:01
     */

    boolean updateInfo(T t);

    /**
     * description: 根据课程 id 获取课程信息
     *
     * @param id 课程信息包含 id 即可
     * @return {@link T} 返回信息
     * @author sunbo
     * @date 2022/6/6 16:37
     */
    T getInfoById(Integer id);

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
    List<T> getInfoPage(Integer current, Integer size, Comparator<T> comparator);

    /**
     * description: 分页
     *
     * @param current  当前页
     * @param size     每页记录数
     * @param list     原集合
     * @param listPage 分页集合
     * @author sunbo
     * @date 2022/6/9 19:36
     */
    void transferPage(Integer current, Integer size, List<T> list, List<T> listPage);

    /**
     * description: 获取所有信息
     *
     * @return {@link List<T>} 返回信息集合
     * @author sunbo
     * @date 2022/6/8 19:02
     */
    List<T> getAllInfoOfList();

    /**
     * description:
     *
     * @return {@link Map<Integer,T>}
     * @author sunbo
     * @date 2022/6/9 17:34
     */
    Map<Integer, T> getAllInfoOfMap();
}
