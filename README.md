系统应实现的主要功能：

登录功能：系统管理员登录学生管理系统成功后对学生信息,课程信息,学生成绩,管理员信息进行管理。登录时对管理员的身份进行验证，对账号信息，密码的验证。
管理员信息管理： 1） 对管理员账号的查询 2） 添加系统管理员 3） 删除管理员信息
学生信息管理功能： 1） 对学生信息的查询：按照学号，姓名，班级等多条件组合分页查询；对已登记成绩和未登记成绩的学生的筛选 2） 添加学生信息：添加学生的基本信息，专业，班级，姓名，性别，添加信息分为自己添加和选择添加 3） 添加学生成绩信息：单独添加学生成绩信息 4） 修改学生信息 5） 删除学生信息（同时要删除该学生对应的的成绩信息）
课程信息管理功能： 1） 添加课程信息 2） 删除课程信息
学生成绩信息管理 1） 学生成绩信息的查询：按学生学号，班级等信息进行多条件组合分页查询；按学生各门成绩进行排序，按学生成绩平均分和总分进行排序； 2） 学生成绩信息的修改：对已登记的学生的成绩信息进行修改 3） 学生成绩信息的删除  各模块的具体功能和简单算法：
项目技术架构 项目技术架构采用三层架构
表现层主要由JavaFx图形化界面和controller包下的controller类组成,用户操作图形化界面发送请求到controller类,controller类分析需求后调用service包下对应的service服务类完成相应服务
业务逻辑层由service包下的service接口和其实现类组成,service类中完成具体的业务逻辑调用,service类通过分别调用一组持久层的操作完成一组业务逻辑操作 3)持久层由mapper接口及其实现类和文件系统组成,mapper类实现的对文件中信息的增删改查等基本操作
View类和Controller类具体结构 1.View类 1） view 界面包括视图控制类和fxml文件，fxml文件构成了视图界面的基本信息，相应控制类里定义了对视图数据的绑定和点击事件，校验事件的绑定等。 2） view 包下有两个基本类MainApp 和 ChangeSence类
MainApp 下的start方法内定义了初始的登录窗口信息，main方法是程序的入口，会新建一个登录窗口。ChangeSence类专门用来做窗口的切换，即更改渲染的fxml文件。 resources 配置文件中定义了不同的fxml文件，表示各个窗口。view包下定义了与之相对应的视图控制类。

2.Controller类 声明: 所有的Controller类中的方法的返回值均表示为Result类,用作规范化 Result类结构如下: success 字段: 表示操作是否执行成功 message 字段: 表示操作返回的提示信息 data 字段: 表示操作返回的具体信息,类型为Map集合 1） CourseController类 结构如图

saveAllCourse(List) 方法用于将一个Course列表的所有课程信息保存到系统中 removeCourse(Course)方法用于将一个课程信息从系统中删除 getCoursePage(Integer,Integer) 用于分页查询课程信息 saveCourse(Course) 用于保存课程信息 getAllcourse() 用于获取所有课程信息 removeAll(List) 用于将List集合中的课程信息从系统中删除 updateCourse(Course) 用于更新指定课程信息

2） StudentController 类 结构如下

getStudentById(Integer) 根据id获取学生信息 getStudentPage(Integer,integer,StudentQuery) 按条件组合分页查询学生信息 getStudentScorePage(Integer,Integer,StudentQuery) 按条件组合分页查询学生成绩信息 updateStudentScore(Student) 更新学生成绩信息 其余方法同上述CourseController类,不在赘述 3） UserController 类 结构如下

Login(String,String) 管理员登录验证 Register(String,String)管理员账号注册

Service类具体结构 这里以 StudentServiceImpl实现类为例
1） BaseService和BaseServiceImpl是基础服务类,其中BaseServiceImpl实现了BaseService接口,BaseService接口内定义了一些常用的服务操作,对各实体类的增删改查等,通过泛型定义基础接口,实现类课根据具体泛型实现类型进行相应服务调用,BaseService接口结构如下

至此,StudentService服务类继承BaseServiceImpl并试下BaseService接口后,无需在内部重复定义增删改查等基础重复语句,代码简洁程度大大提高 2）StudentServiceImpl 类具体方法举例 1.添加学生成绩信息 由于学生信息和成绩信息涉及两个文件，无法在一次操作中完成，需要另写方法执行，在学生服务中调用课程学生服务，向课程学生文件中添加学生信息和课程信息。具体代码如下： public boolean saveStudentScore(Student student) { List list = new ArrayList<>(7); for (int i = 0; i < student.getCourses().size(); i++) { list.add(new CourseStudent(IdUtils.getStuCouId(), student.getId(), student.getCourses().get(i).getId(), student.getScores().get(i))); } boolean saveAll = csService.saveAll(list); if (saveAll) { return baseMapper.updateInfo(student); } else { return false; } }

2.按条件组合分页查询 BaseService 依赖于泛型，不能进行条件分页查询，需要另外写方法，在条件分页查询是先进行条件筛选，筛选出所有符合条件的项，然后根据排序条件进行排序，然后对排序后的列表进行分页，返回分页数据，具体代码如下： public Map<String, Object> getStudentScorePage(Integer current, Integer size, StudentQuery studentQuery) { Map<Integer, Student> studentMap = baseMapper.getAllInfoOfMap(); Map<Integer, CourseStudent> csMap = csService.getAllOfMap(); Map<Integer, Course> courseMap = courseService.getAllOfMap(); if (csMap == null || studentMap == null || courseMap == null) { Map<String, Object> map = new HashMap<>(2); map.put("studentScoreList", null); map.put("total", 0); return map; }

//筛选
csMap.forEach((key, cs) -> {
    //获取学生 id
    Student student = studentMap.get(cs.getStudentId());
    if (baseMapper.match(student, studentQuery)) {
        studentMap.put(student.getId(), 
                student.addCourse(courseMap.get(cs.getCourseId()))
                        .addScore(cs.getScore()));
    }
});
List<Student> list = new ArrayList<>(10);
for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
    Student stu = entry.getValue();
    if (!stu.getCourses().isEmpty()) {
        list.add(stu);
    }
}

//排序
if (studentQuery != null) {
    if (studentQuery.getSortName()) {
        list.sort(Comparator.comparing(Student::getName));
    } else if (studentQuery.getSortScore() != 0) {
        int index;
        if (studentQuery.getSortScore() == 100) {
            list.sort(Comparator.comparing(Student::getTotalScore));
        } else if (studentQuery.getSortScore() == -100) {
            list.sort(Comparator.comparing(stu -> -stu.getTotalScore()));
        } else if (studentQuery.getSortScore() > 0) {
            index = studentQuery.getSortScore() - 1;
            list.sort(Comparator.comparing(stu -> stu.getScores().get(index)));
        } else {
            index = (-studentQuery.getSortScore()) - 1;
            list.sort(Comparator.comparing(stu -> -stu.getScores().get(index)));
        }
    }
}

//分页
List<Student> listPage = new ArrayList<>(size);
baseMapper.transferPage(current, size, list, listPage);

Map<String, Object> map = new HashMap<>(2);
map.put("studentScoreList", listPage);
map.put("total", list.size());
return map;
}

Mapper持久层类具体结构 这里仍然以StudentMapperImpl实现类为例 1） 结构如下
在持久层依然定义了基础Mapper接口，即BaseMapper接口和BaseMapperImpl实现类；实际上Service层基础类调用的就是基础Mapper接口的方法，具体的对实现类的增删改查过程就是定义在BaseMapperImpl实现类里面，该实现类是核心的基础类。 BaseMapperImpl类的结构如下

除了主要的增删改查方法，其中主要方法还包括：

与文件交互的toOutput（保存进文件）和getInfoSet（从文件读出数据）
对列表结果进行分页的transferPage方法
将查询的数据封装为List集合或Map集合的查询方法 2） 主要方法实例
添加数据到文件 具体过程是先从文件中反序列化出数据，反序列化结果是TreeSet集合（使用TreeSet集合是为了保证数据的唯一性）将数据添加到集合总，在将集合序列化到文件中，移除，修改数据也是同理。 public boolean saveInfo(T t) { TreeSet tSet = getInfoSet(); if (tSet == null) { tSet = new TreeSet<>(); } tSet.add(t); return toOutput(tSet); }
Bean 实体类以及工具类的实现 1） Bean实体类定义了基础的BaseBean 类，所有的实体类都集成了基础实体类，基础实体类中只有一个字段id，id作为每一个实体类的唯一标识，也方便将Set集合转换为Map 集合是作key用。BaseBean类实现了Serializable 和 Comparable接口，分别用作序列化操作和比较操作，所有的实体类之间的比较用id进行。 2） 为了更方便的处理程序，需要定义一些工具类，在项目中主要定义了一下工具类 1． FileNameUtils 工具类的主要功能是获取文件名，每一个文件的文件名在properties配置文件中定义，FileNameUtils工具类作用是从配置文件中读取相应文件名 2． IdUtils工具类 ，主要作用是定义每一个实体类的唯一id，每次启动程序时，IdUtils工具类会访问一遍文件，将每一个文件中id最大的值保存下来，在添加数据时在此最大值的基础上依次递增 3． Md5Utils 工具类，主要作用是用于密码的加密处理，Md5的特点是只能加密不能解密，可以加强密码的安全性 4． ObjStreamUtils 工具类，和文件交互的核心，序列化和反序列化工具类，根据文件名进行序列化和反序列化操作，项目中将所有的数据添加到Set集合中，将Set集合序列化到文件里 5． StringUtils 工具类，判断字符串是否为空的工具类
五、源程序调试过程 1、基础BaseBean实体类型的选择问题 最初想用接口作为一个基础的实体，让所有正式实体都实现BaseBean接口，随之出现的问题是接口中的成员变量只能是常量，需要被public static final 修饰，造成的问题是所有的实现类都共用一个id，无法保证唯一性。解决方法是将BaseBean接口换为抽象类，抽象类可以像普通类一样定义自己的属性，可以完成剩余操作。

2、文件操作反序化的问题 在进行序列化的过程中发现程序无法进行序列化，操作对象是TreeSet集合，TreeSet集合已经实现了Serializable 接口是可以进行序列化操作的，可以推测是实体类的原因，实体类都继承与BaseBean 类，BaseBean类实现了Serializable接口，定义了序列号，但是在序列化过程中会报出序列号错误，最终发现原因是因为实现了BaseBean类的实体类也需要继承Serializable接口定义序列号才可以正常运行。 3、初始时文件不存在问题 在初始时系统中是没有文件的，但是初始时会调用输入流从文件中获取信息，就会产生IOException。解决方法是在读文件之前加上语句if (!file.exists()) return null;判断文件是否存在，如果不存在就返回null。 4、关闭资源问题 在使用完IO流之后要关闭相应资源,并且要确保资源的关闭,这里我使用了try/catch/finally语句,将关闭资源的语句放在finally块中,无论程序是否出错最终都会关闭资源。 5、实体类唯一id的设置问题 唯一id要保证其是唯一的，初始时用日期时间的毫秒值+3位随机数组成实体类的唯一id，但是发现这样做的缺点是生成的id很长，且相隔时间较长的话生成的数值也没有规律，我想要的效果是id具有一定的规律并且保证唯一性。最终的解决方法是定义一个固定的四位数字前缀作为开头，初始后三位为零，依次递增，每次启动程序时都会从文件中读取出当前id的最大值，并保存起来，在添加数据时即可实现唯一且规整的效果。 6、配置文件的读取问题 程序中将文件名全部存放在properties配置文件中，读取配置文件使用的是ResourceBundle类，调用getBundle(文件名)生成对象ResourceBundle对象。但在调用getString 方法获取文件内容是出现文件不存在的错误。经检查发现getBundle方法读取配置文件时是不需要文件后缀名的，它直接读取properties文件。解决方法：更改读取时的文件名即可。 7、密码安全性问题 管理员的账号密码不应该透明的存储在文件中，所以程序采用了MD5加密的方式存储密码，用户登录时将登录时请求的密码也进行MD5加密，将加密后的密码和文件中密码对比，避免了密码的安全性问题。 8、基础增删改查代码臃肿问题 刚开始编写程序时直接定义了实体类和mapper类和service类。直接在对应mapper类和service类中对实体类及文件进行增删改查等操作，但是编写第二个模块时发现很多基础的操作存在重复性，仅仅是实体类不同,其余操作大部分一致，代变得很臃肿。解决方法是使用泛型，首先定义一个基础的泛型接口BaseMapper接口,在其中定义基础方法。随后定义实现类BaseMapperImpl泛型实现类，在实现类里利用泛型完成一系列操作语句,真正的实体类的mapper类只需要继承BaseMapperImpl就可以实现简单操作,无需在重复写臃肿的代码,代码变得更加简洁,可扩展程度也大大提高。 9、增强for循环的增删问题 在删除学生信息时，使用增强for循环遍历学生列表对一些数据进行删除。发现在删除数据时会出错，检查语句未发现问题，随后查阅资料发现在增强for循环里面不能进行增加和删除操作，进一步查询发现增强for循环内部调用的是iterator迭代器，它不允许被迭代的对象随意改变。解决方法是直接调用迭代器遍历列表，使用迭代器的remove方法进行删除或者使用普通for循环遍历列表进行增删操作。 10、修改学生信息后，再次查询列表空指针问题 在进行查询学生成绩的操作时，查询结果返回的是学生实体类列表，学生实体类包含了分数列表属性。每次查询到学生信息后再从学生成绩信息表中将学生的成绩信息添加到学生分数列表中，再做返回。测试发现在对学生的基础信息进行更改后再次查询学生成绩信息会报出空指针错误。检查发现在对学生基础信息进行修改后，学生的成绩信息被序列化到了文件中，再次反序列化时得到的学生信息表立面包含了分数信息，此时再向分数表中添加学生成绩，学生的分数数量就多于了课程数量，在进行课程和分数的匹配是就会造成课程表已经遍历完但分数表还存在信息，且分数信息时初次存入的信息，后面进行的分数修改信息都未被录入。解决方法是在学生实体类的分数表字段前加上transient 关键字，加上关键字后分数表字段在序列化时就不会被序列化进文件中，再次读取信息时分数表为空字段，就可以直接添加分数信息不会出现错误。 11、视图界面修改，添加信息的回显问题 再界面中进行信息的添加或修改，填入信息完成添加或修改后再次进入视图界面时显示的数据仍然是上次填入的信息。解决方法是在每次进入修改或添加界面前对数据进行一次清空即可。 12、表格信息绑定问题 JavaFx表格需要绑定一个实体类列表来显示对应信息。在绑定实体类后发现显示时会报找不到实体类的错误，经查阅资料发现JavaFx表格绑定实体类信息时要求实体类与当前表格所在类处于同一个包下，我的控制类定义在view包下，而我的实体类是定义在bean包下的，因此控制类里的表格找不到实体类。解决方法是在view包下单独定义实体类，用来与表格数据向对应，这样也易于独对表格进行操作，不和真正的文件实体类相耦合。

七、总结： 通过设计本项目，使我更深刻的了解到了一个好的技术架构的重要性，好的技术架构可以使代码结构十分清晰，每个模块的功能明确，可以更加快速的完成一组业务逻辑，使任务有序快速的完成。好的架构更易于实现高聚合低耦合的思想，模块之间分工明确，耦合度低，使后期添加新的模块和功能变得更加容易便捷。同时在使用文件系统保存信息时，让我意识到数据保存需要规范的重要性，之前使用数据库进行数据的保存十分便捷和快速，当自己实现一套工具去保存数据时，发现要考虑的方面很多，例如数据的完整性，数据的唯一性，不同数据之间的联系等等，同时也加深了我对序列化和反序列的理解。在编写业务逻辑操作部分时，用了泛型类去进行一些操作，在之前接触泛型大多数是使用已经提供好的API，当自己去编写一组泛型接口和类时才发现它的强大之处，让很多重复性的工作变得十分便捷，使原本复杂冗余的代码变得十分简洁。 项目最后的结果离最初的预期还有一些差距，还有一部分起初想添加的功能最终因为时间和精力的原因没有添加进去，比如初期设想的给管理员分配权限，不同管理员拥有不同管理权限去管理信息，还设想加入学生和教师的登录界面，学生可以查看自己的成绩，教师可以查看和修改所带班级的学生成绩，由于时间问题还没有实现该功能。还有一个不足之处就是前台界面显示效果用的是最原始的样子，没有进行相应的美化，整体看起来不是很美观。对于这些问题，后期我会考虑去把它们进行修复和完善。总而言之，这次项目经历给我带来了很大的收获。