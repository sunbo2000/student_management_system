package org.snbo.ssms.mapper.impl;

import org.snbo.ssms.bean.BaseBean;
import org.snbo.ssms.mapper.BaseMapper;
import org.snbo.ssms.utils.ObjStreamUtils;

import java.util.*;

/**
 * @author sunbo
 * @date 2022-06-07-0:20
 */
public class BaseMapperImpl<T extends BaseBean> implements BaseMapper<T> {
    private final String filename;

    public BaseMapperImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean saveInfo(T t) {
        TreeSet<T> tSet = getInfoSet();
        if (tSet == null) {
            tSet = new TreeSet<>();
        }
        tSet.add(t);
        return toOutput(tSet);
    }

    @Override
    public boolean saveAllInfo(List<T> tList) {
        TreeSet<T> tSet = getInfoSet();
        if (tSet == null) {
            tSet = new TreeSet<>(tList);
        } else {
            tSet.addAll(tList);
        }
        return toOutput(tSet);
    }

    @Override
    public boolean removeInfo(T t) {
        TreeSet<T> tSet = getInfoSet();
        if (tSet == null) {
            return false;
        } else {
            boolean save = false;
            if (tSet.remove(t)) {
                save = toOutput(tSet);
            }
            return save;
        }
    }

    @Override
    public boolean removeAllInfo(List<T> t) {
        TreeSet<T> tSet = getInfoSet();
        if (tSet == null) {
            return false;
        } else {
            t.forEach(tSet::remove);
            return toOutput(tSet);
        }
    }

    @Override
    public boolean updateInfo(T t) {
        TreeSet<T> tSet = getInfoSet();
        if (!tSet.contains(t)) {
            return false;
        }
        boolean outPut = false;
        if (tSet.remove(t)) {
            if (tSet.add(t)) {
                outPut = toOutput(tSet);
            }
        }
        return outPut;
    }

    @Override
    public T getInfoById(Integer id) {
        if (id == null) {
            return null;
        }
        TreeSet<T> tSet = getInfoSet();
        if (tSet == null) {
            return null;
        }
        for (T t1 : tSet) {
            if (id.equals(t1.getId())) {
                return t1;
            }
        }
        return null;
    }

    @Override
    public List<T> getInfoPage(Integer current, Integer size, Comparator<T> comparator) {
        TreeSet<T> tSet = getInfoSet();
        if (tSet == null) {
            return null;
        }
        List<T> list = new ArrayList<>(tSet);

        // 排序
        if (comparator != null) {
            list.sort(comparator);
        }

        // 分页
        List<T> listPage = new ArrayList<>(size);
        transferPage(current, size, list, listPage);
        return listPage;
    }

    @Override
    public void transferPage(Integer current, Integer size, List<T> list, List<T> listPage) {
        int i = (current - 1) * size;
        while (i < list.size() && i < current * size) {
            listPage.add(list.get(i));
            i++;
        }
    }

    @Override
    public List<T> getAllInfoOfList() {
        TreeSet<T> infoSet = getInfoSet();
        if (infoSet == null) {
            return null;
        }
        return new ArrayList<>(infoSet);
    }

    @Override
    public Map<Integer, T> getAllInfoOfMap() {
        TreeSet<T> tSet = getInfoSet();
        if (tSet == null) {
            return null;
        }
        Map<Integer, T> map = new LinkedHashMap<>();
        tSet.forEach(t -> map.put(t.getId(), t));
        return map;
    }

    /**
     * description: 从文件中获取信息集合
     *
     * @return {@link Object} 反序列化出实例
     */
    public TreeSet<T> getInfoSet() {
        return (TreeSet<T>) ObjStreamUtils.toDeserialize(filename);
    }

    /**
     * description: 将信息集合输出到文件中
     *
     * @param set 信息集合
     * @return {@link boolean} 上传成功返回 true
     */
    public boolean toOutput(Set<T> set) {
        return ObjStreamUtils.toSerialization(set, filename);
    }

}
