package org.snbo.ssms.service.impl;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.snbo.ssms.bean.Student;
import org.snbo.ssms.bean.User;
import org.snbo.ssms.mapper.impl.StudentMapperImpl;
import org.snbo.ssms.utils.IdUtils;

import java.util.List;
import java.util.Random;

/**
 * StudentServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>06/28/2022</pre>
 */
public class StudentServiceImplTest {
    private StudentServiceImpl studentService = new StudentServiceImpl(new StudentMapperImpl());

    private final String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
            "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
            "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
            "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
            "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
            "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
    private final String[] girlName = {"梦琪", "忆柳", "之桃", "慕青", "问兰", "尔岚", "元香", "初夏",
            "沛菡", "傲珊", "曼文", "乐菱", "痴珊", "恨玉", "惜文", "香寒", "新柔", "语蓉",
            "海安", "夜蓉", "涵柏", "水桃", "醉蓝", "春儿", "语琴", "从彤", "傲晴", "语兰",
            "又菱", "碧彤", "元霜", "怜梦", "紫寒", "妙彤", "曼易", "南莲", "紫翠", "雨寒",
            "若南", "寻真", "晓亦", "向珊", "慕灵", "以蕊", "寻雁", "映易", "雪柳", "孤岚",
            "笑霜", "海云", "凝天", "沛珊", "寒云", "冰旋", "宛儿", "绿真", "盼儿", "晓霜",
            "碧凡", "夏菡", "曼香", "若烟", "半梦", "雅绿", "冰蓝", "灵槐", "平安", "书翠",
            "翠风", "香巧", "代云", "梦曼", "幼翠", "友巧", "听寒", "梦柏", "醉易", "访旋",
            "亦玉", "凌萱", "访卉", "怀亦", "笑蓝", "春翠", "靖柏", "夜蕾", "冰夏", "梦松",
            "书雪", "乐枫", "念薇", "靖雁", "寻春", "恨山", "从寒", "忆香", "觅波", "静曼",
            "易烟", "如萱"};
    private final String[] boyName = {"晓博", "文博", "宇文", "辰锟", "智晖", "宜春", "和雅", "明杰", "华采", "兴安",
            "文柏", "君之", "晗日", "懿轩", "睿德", "昊嘉", "宏伟", "嘉慕", "元德", "康德", "阳泽", "昆颉", "良骏", "理群",
            "佑运", "永元", "高懿", "良策", "英华", "周成", "嘉纳", "曦之", "兴旺", "飞翮", "玉轩", "俊楚", "鹏池", "朋兴",
            "意智", "阳舒", "运盛", "勇男", "心水", "文山", "嘉许", "涵润", "敏学", "建同", "伟茂", "泰然", "建安", "铭晨",
            "涵涤", "骞北", "璞瑜", "康泰", "光熙", "建义", "凯泽", "阳伯", "锐逸", "宏邈", "良工", "浦和", "智刚", "哲茂",
            "鹏运", "雅昶", "经国", "海阳", "嘉茂", "德义", "飞沉", "英耀", "宏远", "弘量", "和悦", "鸿文", "烨磊", "和宜",};

    private final String[] major = {"计算机科学与技术", "大数据", "人工智能", "软件工程"};
    private Integer[] classs;

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getStudentById(Integer id)
     */
    @Test
    public void testGetStudentById() throws Exception {
        for (int i = 0; i <= 40; i++) {
            Student student = createStudent();
            student.setId(IdUtils.getStudentId());
            studentService.save(student);
        }
    }

    private Student createStudent() {

        Student student = new Student();
        Random random = new Random();
        String firstname = Surname[random.nextInt(Surname.length)];
        if (random.nextInt(2) == 0) {
            student.setSex((byte) 0);
            student.setName(firstname + boyName[random.nextInt(boyName.length)]);
        } else {
            student.setSex((byte) 1);
            student.setName(firstname + girlName[random.nextInt(girlName.length)]);
        }

        String major1 = major[random.nextInt(major.length)];
        //设置年级
        student.setMajor(major1);

        if (major1.equals(major[0])) {
            classs = new Integer[]{1020100, 1020101, 1020102, 1020103, 1020104, 1020105};
        } else if (major1.equals(major[1])) {
            classs = new Integer[]{2020100, 2020101, 2020102, 2020103, 2020104, 2020105};
        } else if (major1.equals(major[2])) {
            classs = new Integer[]{3020100, 3020101, 3020102, 3020103, 3020104, 3020105};
        } else if (major1.equals(major[3])) {
            classs = new Integer[]{4020100, 4020101, 4020102, 4020103, 4020104, 4020105};
        }
        //设置专业
        student.setClassId(classs[random.nextInt(classs.length)]);

        return student;
    }

    /**
     * Method: getStudentPage(Integer current, Integer size, StudentQuery studentQuery)
     */
    @Test
    public void testGetStudentPage() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getStudentScorePage(Integer current, Integer size, StudentQuery studentQuery)
     */
    @Test
    public void testGetStudentScorePage() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: updateScore(Student student)
     */
    @Test
    public void testUpdateScore() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: removeScoreInfo(Student student)
     */
    @Test
    public void testRemoveScoreInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: saveStudentScore(Student student)
     */
    @Test
    public void testSaveStudentScore() throws Exception {
//TODO: Test goes here... 
    }


}
