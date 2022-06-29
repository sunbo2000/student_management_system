package org.snbo.ssms.view.studentview;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.snbo.ssms.bean.Course;
import org.snbo.ssms.bean.Student;
import org.snbo.ssms.bean.queryvo.StudentQuery;
import org.snbo.ssms.controller.CourseController;
import org.snbo.ssms.controller.StudentController;
import org.snbo.ssms.utils.IdUtils;
import org.snbo.ssms.utils.Result;
import org.snbo.ssms.utils.StringUtils;
import org.snbo.ssms.view.ChangeScene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author sunbo
 * @date 2022-06-13-1:49
 */
public class StudentViewController {


    @FXML
    public Pane pane;
    @FXML
    public TableView<StudentInfo> tableView;
    @FXML
    public TableColumn<StudentInfo, Integer> id;
    @FXML
    public TableColumn<StudentInfo, String> major;
    @FXML
    public TableColumn<StudentInfo, Integer> classes;

    @FXML
    public TableColumn<StudentInfo, String> name;
    @FXML
    public TableColumn<StudentInfo, String> sex;
    @FXML
    public TableColumn<StudentInfo, String> status;
    @FXML
    public TableColumn<StudentInfo, String> opera0;
    @FXML
    public TableColumn<StudentInfo, String> opera1;
    @FXML
    public TableColumn<StudentInfo, String> opera2;
    @FXML
    public TextField searchSid;
    @FXML
    public TextField searchCid;
    @FXML
    public TextField searchName;
    @FXML
    public Label tip1;
    @FXML
    public Label tip2;
    @FXML
    public Pane pane1;
    @FXML
    public TextField theName;
    @FXML
    public ChoiceBox<String> choiceBox;
    @FXML
    public ChoiceBox<Integer> theClass;
    @FXML
    public RadioButton man;
    @FXML
    public RadioButton woman;
    @FXML
    public Label tip5;

    @FXML
    public Label c1;
    @FXML
    public Label c2;
    @FXML
    public Label c3;
    @FXML
    public Label c4;

    @FXML
    public Label c5;
    @FXML
    public Label c6;
    @FXML
    public Label c7;
    @FXML
    public TextField s1;
    @FXML
    public TextField s2;
    @FXML
    public TextField s3;
    @FXML
    public TextField s4;
    @FXML
    public TextField s5;
    @FXML
    public TextField s6;
    @FXML
    public TextField s7;
    @FXML
    public Label addName;
    @FXML
    public Label addMajor;
    @FXML
    public Label addClass;
    @FXML
    public Label addId;
    @FXML
    public Label tip3;
    @FXML
    public Pane pane2;


    private final StudentController sController = new StudentController();
    private final CourseController cController = new CourseController();
    @FXML
    public HBox hBox;
    private Integer current = 1;
    private Integer size = 20;
    private Integer total;

    private Integer pages;
    private Student student;

    private final StudentQuery studentQuery = new StudentQuery();
    private List<Student> studentList;


    @FXML
    void initialize() {
        getListAndSetTableView(current, size, null);
    }

    public void getListAndSetTableView(Integer current, Integer size, StudentQuery studentQuery) {
        Result result = sController.getStudentPage(current, size, studentQuery);
        studentList = (List<Student>) result.getData().get("studentList");
        total = (Integer) result.getData().get("total");
        if (studentList == null) {
            return;
        }

        List<StudentInfo> voList = new ArrayList<>(size);

        studentList.forEach(stu -> {
            StudentInfo studentInfo = new StudentInfo(stu.getId(), stu.getMajor(), stu.getClassId(), stu.getName(), stu.getSex(), stu.getStatus());
            voList.add(studentInfo);
        });

        setTableView(voList);

        countPage();

        pane1.setVisible(false);
        pane2.setVisible(false);
        pane.setVisible(true);
    }

    public void setTableView(List<StudentInfo> list) {
        ObservableList<StudentInfo> tableList = FXCollections.observableList(list);

        if (list.isEmpty()) {
            tableView.getItems().clear();
            return;
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        major.setCellValueFactory(new PropertyValueFactory<>("major"));
        classes.setCellValueFactory(new PropertyValueFactory<>("classId"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sex.setCellFactory((col) -> new TableCell<StudentInfo, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    //如果此列为空默认不添加元素
                    setText(null);
                } else {
                    if (getIndex() >= 0 && getIndex() < size) {
                        StudentInfo studentInfo = tableView.getItems().get(getIndex());
                        if (studentInfo.getSex() == 0) {
                            setText("男");
                        } else {
                            setText("女");
                        }
                    }
                }
                setGraphic(null);
            }
        });
        status.setCellFactory((col) -> new TableCell<StudentInfo, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    //如果此列为空默认不添加元素
                    setText(null);
                } else {
                    if (getIndex() >= 0 && getIndex() < size) {
                        StudentInfo studentInfo = tableView.getItems().get(getIndex());
                        if (studentInfo.getStatus().equals(true)) {
                            setText("已登记");
                        } else {
                            setText("未登记");
                        }
                    }
                }
                setGraphic(null);
            }
        });
        opera0.setCellFactory((col) -> new TableCell<StudentInfo, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    //如果此列为空默认不添加元素
                    setText(null);
                    setGraphic(null);
                } else {
                    if (getIndex() >= 0 && getIndex() < size) {
                        StudentInfo studentInfo = tableView.getItems().get(getIndex());
                        if (studentInfo.getStatus().equals(false)) {
                            Button button = new Button("添加成绩");
                            button.setOnAction(actionEvent -> {
                                StudentInfo info = tableView.getItems().get(getIndex());
                                addScoreInfo(info);
                            });
                            this.setGraphic(button);
                        }
                    }
                }
            }
        });

        opera1.setCellFactory((col) -> new TableCell<StudentInfo, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button button = new Button("修改学生信息");
                button.setOnAction(event -> {
                    Integer id = tableView.getItems().get(getIndex()).getId();
                    modify(id);
                });
                if (empty) {
                    //如果此列为空默认不添加元素
                    setText(null);
                    setGraphic(null);
                } else {
                    this.setGraphic(button);
                }
            }
        });

        opera2.setCellFactory((col) -> new TableCell<StudentInfo, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button button = new Button("删除学生信息");
                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.titleProperty().set("提示");
                    alert.setHeaderText("成绩将被永久删除,是否继续?");
                    alert.setContentText(null);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.OK) {

                        Integer studentId = tableView.getItems().get(getIndex()).getId();
                        Result result = sController.removeStudent(new Student(studentId));
                        if (result.getSuccess()) {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.titleProperty().set("提示");
                            alert1.setHeaderText("删除成功");
                            alert1.setContentText(null);
                            alert1.showAndWait();
                            flush();
                        }
                    }
                });
                if (empty) {
                    //如果此列为空默认不添加元素
                    setText(null);
                    setGraphic(null);
                } else {
                    this.setGraphic(button);
                }
            }
        });

        tableView.setCursor(Cursor.CLOSED_HAND);
        tableView.getItems().clear();
        tableView.getItems().addAll(tableList);
    }

    void countPage() {
        hBox.getChildren().clear();
        pages = total / size + (total % size != 0 ? 1 : 0);
        System.out.println(pages);
        if (pages > 1) {
            // 首页
            Button button1 = new Button("首页");
            button1.setOnAction(event -> {
                current = 1;
                getListAndSetTableView(1, size, studentQuery);
            });
            hBox.getChildren().add(button1);

            //上一页
            Button pre = new Button("上一页");
            pre.setOnAction(event -> {
                if (current > 1) {
                    getListAndSetTableView(--current, size, studentQuery);
                }
            });

            hBox.getChildren().add(pre);

            for (int i = 1; i <= pages; i++) {
                Button trans = new Button();
                trans.setText("" + i);
                int finalI = i;
                trans.setOnAction(actionEvent -> {
                    current = finalI;
                    getListAndSetTableView(finalI, size, studentQuery);
                });
                if (current == i) {
                    trans.setStyle("-fx-background-color: rgb(133,204,229)");
                }
                hBox.getChildren().add(trans);
            }

            //下一页
            Button next = new Button("下一页");
            next.setOnAction(event -> {
                if (current < pages) {
                    getListAndSetTableView(++current, size, studentQuery);
                }
            });
            hBox.getChildren().add(next);

            //尾页
            Button last = new Button("尾页");
            last.setOnAction(event -> {
                current = pages;
                getListAndSetTableView(pages, size, studentQuery);
            });
            hBox.getChildren().add(last);

        }
    }

    private void addScoreInfo(StudentInfo info) {
        student = new Student(info.getId(), info.getMajor(), info.getClassId(), info.getName(), info.getSex(), true);

        addName.setText(info.getName());
        addMajor.setText(info.getMajor());
        addClass.setText(info.getClassId().toString());
        addId.setText(info.getId().toString());

        Result result = cController.getCoursePage(1, 7);
        List<Course> list = (List<Course>) result.getData().get("coursePageList");

        c1.setText(list.get(0).getTitle());
        student.addCourse(list.get(0));
        c2.setText(list.get(1).getTitle());
        student.addCourse(list.get(1));
        c3.setText(list.get(2).getTitle());
        student.addCourse(list.get(2));
        c4.setText(list.get(3).getTitle());
        student.addCourse(list.get(3));
        c5.setText(list.get(4).getTitle());
        student.addCourse(list.get(4));
        c6.setText(list.get(5).getTitle());
        student.addCourse(list.get(5));
        c7.setText(list.get(6).getTitle());
        student.addCourse(list.get(6));

        pane.setVisible(false);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }

    @FXML
    private void addStudent() {
        student = new Student();

        theName.setText(null);
        setMajor();
        theClass.getItems().clear();
        man.setSelected(false);
        woman.setSelected(false);

        pane.setVisible(false);
        pane2.setVisible(false);
        pane1.setVisible(true);
    }

    void modify(Integer id) {
        for (Student stu : studentList) {
            if (id.equals(stu.getId())) {
                student = stu;
            }
        }
        man.setSelected(student.getSex() == 0);
        woman.setSelected(student.getSex() == 1);

        theName.setText(student.getName());

        setMajor();

        choiceBox.setValue(student.getMajor());
        theClass.setValue(student.getClassId());


        pane.setVisible(false);
        pane2.setVisible(false);
        pane1.setVisible(true);
    }

    @FXML
    public void searchScore(ActionEvent actionEvent) throws Exception {
        new ChangeScene("studentscoreview/manager.fxml");
    }

    public void Sign(ActionEvent actionEvent) {
        studentQuery.setStatus(true);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    public void unSign(ActionEvent actionEvent) {
        studentQuery.setStatus(false);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void search() {
        boolean sid = checkSid();
        boolean cid = checkCid();
        boolean name = checkName();
        if (sid || cid || name) {
            current = 1;
            getListAndSetTableView(current, size, studentQuery);
        }
    }

    @FXML
    public void enter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            search();
        }
    }

    @FXML
    public boolean checkName() {
        if (!StringUtils.isEmpty(searchName.getText().trim())) {
            studentQuery.setName(searchName.getText().trim());
            return true;
        } else {
            studentQuery.setName(null);
            studentQuery.setName("");
            return false;
        }
    }

    @FXML
    public boolean checkSid() {
        if (searchSid.getLength() > 0) {
            String sid = searchSid.getText().trim();
            String regex = "2022\\d{3}";
            if (sid.matches(regex)) {
                studentQuery.setStudentId(Integer.valueOf(sid));
                tip1.setText("");
                return true;
            } else {
                studentQuery.setStudentId(null);
                tip1.setText("学号格式错误");
                return false;
            }
        } else {
            studentQuery.setStudentId(null);
            tip1.setText("");
            return false;
        }
    }

    @FXML
    public boolean checkCid() {
        if (searchCid.getLength() > 0) {
            String cid = searchCid.getText().trim();
            String regex = "[1-4]020\\d{3}";
            if (cid.matches(regex)) {
                studentQuery.setClasses(Integer.valueOf(cid));
                tip2.setText("");
                return true;
            } else {
                studentQuery.setClasses(null);
                tip2.setText("班级格式错误");
                return false;
            }
        } else {
            studentQuery.setClasses(null);
            tip2.setText("");
            return false;
        }
    }

    @FXML
    public void flush() {
        current = 1;
        getListAndSetTableView(current, size, null);
    }

    void setMajor() {
        choiceBox.getItems().clear();
        String[] major = {"计算机科学与技术", "大数据", "人工智能", "软件工程"};
        choiceBox.getItems().addAll(major);
        //设置监听
        choiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (newValue.equals(major[0])) {
                        student.setMajor(major[0]);
                        setClass(1020100, 1020101, 1020102, 1020103, 1020104, 1020105);
                    } else if (newValue.equals(major[1])) {
                        student.setMajor(major[1]);
                        setClass(2020100, 2020101, 2020102, 2020103, 2020104, 2020105);
                    } else if (newValue.equals(major[2])) {
                        student.setMajor(major[2]);
                        setClass(3020100, 3020101, 3020102, 3020103, 3020104, 3020105);
                    } else if (newValue.equals(major[3])) {
                        student.setMajor(major[3]);
                        setClass(4020100, 4020101, 4020102, 4020103, 4020104, 4020105);
                    } else {
                        student.setMajor(null);
                        theClass.getItems().clear();
                    }
                }

            }
        });

        theClass.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer oldValue, Integer newValue) {
                if (newValue != null) {
                    student.setClassId(newValue);
                }

            }
        });
    }

    void setClass(Integer... t) {
        theClass.getItems().clear();
        theClass.getItems().addAll(t);
    }

    @FXML
    public void selectMan() {
        student.setSex((byte) 0);
    }

    @FXML
    public void selectWoman() {
        student.setSex((byte) 1);
    }

    @FXML
    public void updateInfo(ActionEvent actionEvent) {
        if (updateCheck()) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.titleProperty().set("提示");
            alert1.setHeaderText("确认信息?");
            alert1.setContentText(null);
            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK) {
                student.setName(theName.getText());
                student.setMajor(choiceBox.getValue());
                student.setClassId(theClass.getValue());
                Result result1;
                if (StringUtils.isEmpty(student.getId())) {
                    student.setId(IdUtils.getStudentId());
                    result1 = sController.saveStudent(student);
                } else {
                    result1 = sController.updateStudent(student);
                }
                if (result1.getSuccess()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("提示");
                    alert.setHeaderText("保存信息成功");
                    alert.setContentText(null);
                    alert.showAndWait();
                    flush();
                }
            }
        }
    }

    @FXML
    public boolean updateCheck() {
        if (StringUtils.isEmpty(theName.getText())) {
            tip5.setText("请输入姓名");
            return false;
        } else if (StringUtils.isEmpty(choiceBox.getValue())) {
            tip5.setText("请选择专业");
            return false;
        } else if (StringUtils.isEmpty(theClass.getValue())) {
            tip5.setText("请选择班级");
            return false;
        } else if (!man.isSelected() && !woman.isSelected()) {
            tip5.setText("请选择性别");
            return false;
        }
        tip5.setText("");
        return true;
    }

    @FXML
    public boolean checkScore() {
        String regex = "(100|100.0|(\\d{1,2})|(\\d{1,2}\\.\\d))";
        if (s1.getText().trim().matches(regex)
                && s2.getText().trim().matches(regex)
                && s3.getText().trim().matches(regex)
                && s4.getText().trim().matches(regex)
                && s5.getText().trim().matches(regex)
                && s6.getText().trim().matches(regex)
                && s7.getText().trim().matches(regex)) {
            tip3.setText("");
            return true;
        } else {
            tip3.setText("格式错误");
            return false;
        }
    }

    @FXML
    public void addStudentInfo(ActionEvent actionEvent) {
        if (checkScore()) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.titleProperty().set("提示");
            alert1.setHeaderText("确认添加?");
            alert1.setContentText(null);
            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK) {
                student.addScore(Double.valueOf(s1.getText()));
                student.addScore(Double.valueOf(s2.getText()));
                student.addScore(Double.valueOf(s3.getText()));
                student.addScore(Double.valueOf(s4.getText()));
                student.addScore(Double.valueOf(s5.getText()));
                student.addScore(Double.valueOf(s6.getText()));
                student.addScore(Double.valueOf(s7.getText()));

                Result result1 = sController.saveStudentScore(student);
                if (result1.getSuccess()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("提示");
                    alert.setHeaderText("添加成绩成功");
                    alert.setContentText(null);
                    alert.showAndWait();
                    flush();
                }
            }
        }
    }

    @FXML
    public void searchCourse(ActionEvent actionEvent) throws Exception {
        new ChangeScene("studentscoreview/course.fxml");
    }

    @FXML
    public void searchManager(ActionEvent actionEvent) throws IOException {
        new ChangeScene("studentscoreview/user.fxml");
    }
}
