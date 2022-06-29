package org.snbo.ssms.view.studentview;


import java.io.IOException;
import java.net.URL;
import java.util.*;

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
import org.snbo.ssms.bean.Student;
import org.snbo.ssms.bean.queryvo.StudentQuery;
import org.snbo.ssms.controller.StudentController;
import org.snbo.ssms.utils.Result;
import org.snbo.ssms.utils.StringUtils;
import org.snbo.ssms.view.ChangeScene;


/**
 * @author sunbo
 * @date 2022-05-31-17:46
 */
public class StudentManagerController {


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
    public HBox hBox;
    @FXML
    public TableColumn<StudentVo, String> opera1;
    @FXML
    public TableColumn<StudentVo, String> opera2;
    @FXML
    public Pane pane;
    @FXML
    public Pane pane1;
    @FXML
    public Label modifyName;
    @FXML
    public Label modifyMajor;
    @FXML
    public Label modifyClass;
    @FXML
    public Label modifyId;
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
    public Label tip3;
    @FXML
    public TableColumn<StudentVo, Double> column8;
    @FXML
    public TableColumn<StudentVo, Double> column9;
    @FXML
    public TableColumn<StudentVo,Double> course;
    @FXML
    private TableView<StudentVo> tableView;
    @FXML
    private TableColumn<StudentVo, Integer> id;
    @FXML
    private TableColumn<StudentVo, String> name;
    @FXML
    private TableColumn<StudentVo, Double> column1;
    @FXML
    private TableColumn<StudentVo, Double> column2;
    @FXML
    private TableColumn<StudentVo, Double> column3;
    @FXML
    private TableColumn<StudentVo, Double> column4;
    @FXML
    private TableColumn<StudentVo, Double> column5;
    @FXML
    private TableColumn<StudentVo, Double> column6;
    @FXML
    private TableColumn<StudentVo, Double> column7;

    private final StudentQuery studentQuery = new StudentQuery();

    private Integer current = 1;
    private Integer size = 20;
    private Integer total;
    private Integer pages;

    private List<Student> studentList;

    private Student student;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private final StudentController sController = new StudentController();

    @FXML
    void initialize() {
        getListAndSetTableView(current, size, null);
        pane1.setVisible(false);
    }

    public void getListAndSetTableView(Integer current, Integer size, StudentQuery studentQuery) {
        List<StudentVo> voList = new ArrayList<>(size);

        Result result = sController.getStudentScorePage(current, size, studentQuery);
        Map<String, Object> data = result.getData();
        studentList = (List<Student>) data.get("studentScoreList");
        total = (Integer) data.get("total");
        if (studentList == null) {
            return;
        }

        for (Student stu : studentList) {
            StudentVo vo = new StudentVo(stu.getId(), null, stu.getClassId(), stu.getName());
            vo.setTitle1(stu.getCourses().get(0).getTitle());
            vo.setCourse1(stu.getScores().get(0));

            vo.setTitle2(stu.getCourses().get(1).getTitle());
            vo.setCourse2(stu.getScores().get(1));

            vo.setTitle3(stu.getCourses().get(2).getTitle());
            vo.setCourse3(stu.getScores().get(2));

            vo.setTitle4(stu.getCourses().get(3).getTitle());
            vo.setCourse4(stu.getScores().get(3));

            vo.setTitle5(stu.getCourses().get(4).getTitle());
            vo.setCourse5(stu.getScores().get(4));

            vo.setTitle6(stu.getCourses().get(5).getTitle());
            vo.setCourse6(stu.getScores().get(5));

            vo.setTitle7(stu.getCourses().get(6).getTitle());
            vo.setCourse7(stu.getScores().get(6));

            vo.setAverage(stu.getAverageScore());
            vo.setTotal(stu.getTotalScore());

            voList.add(vo);
        }

        setTableView(voList);

        countPage();

        pane1.setVisible(false);
        pane.setVisible(true);
    }

    public void setTableView(List<StudentVo> list) {
        //定义表格
        ObservableList<StudentVo> tableList = FXCollections.observableList(list);
        if (list.isEmpty()) {
            tableView.setCursor(Cursor.CLOSED_HAND);
            tableView.getItems().clear();
            return;
        }

        StudentVo studentVo = list.get(0);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        course.setCellValueFactory(new PropertyValueFactory<>("classes"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        if (!StringUtils.isEmpty(studentVo.getTitle1())) {
            column1.setText(studentVo.getTitle1());
            column1.setCellValueFactory(new PropertyValueFactory<>("course1"));
        }

        if (!StringUtils.isEmpty(studentVo.getTitle2())) {
            column2.setText(studentVo.getTitle2());
            column2.setCellValueFactory(new PropertyValueFactory<>("course2"));
        }

        if (!StringUtils.isEmpty(studentVo.getTitle3())) {
            column3.setText(studentVo.getTitle3());
            column3.setCellValueFactory(new PropertyValueFactory<>("course3"));
        }

        if (!StringUtils.isEmpty(studentVo.getTitle4())) {
            column4.setText(studentVo.getTitle4());
            column4.setCellValueFactory(new PropertyValueFactory<>("course4"));
        }

        if (!StringUtils.isEmpty(studentVo.getTitle5())) {
            column5.setText(studentVo.getTitle5());
            column5.setCellValueFactory(new PropertyValueFactory<>("course5"));
        }

        if (!StringUtils.isEmpty(studentVo.getTitle6())) {
            column6.setText(studentVo.getTitle6());
            column6.setCellValueFactory(new PropertyValueFactory<>("course6"));
        }

        if (!StringUtils.isEmpty(studentVo.getTitle7())) {
            column7.setText(studentVo.getTitle7());
            column7.setCellValueFactory(new PropertyValueFactory<>("course7"));
        }

        column8.setText("平均分");
        column8.setCellValueFactory(new PropertyValueFactory<>("average"));

        column9.setText("总分");
        column9.setCellValueFactory(new PropertyValueFactory<>("total"));

        opera1.setCellFactory((col) -> new TableCell<StudentVo, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button button = new Button("修改");
                button.setOnAction(event -> {
                    Integer id = tableView.getItems().get(getIndex()).getId();
                    modifyInfo(id);
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

        opera2.setCellFactory((col) -> new TableCell<StudentVo, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button button = new Button("删除");
                button.setOnAction(event -> {
                    Integer studentId = tableView.getItems().get(getIndex()).getId();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.titleProperty().set("提示");
                    alert.setHeaderText("成绩将被永久删除,是否继续?");
                    alert.setContentText(null);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.OK) {
                        Result result = sController.removeStudentScore(new Student(studentId));
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

    void modifyInfo(Integer id) {
        for (Student stu : studentList) {
            if (id.equals(stu.getId())) {
                student = stu;
                break;
            }
        }
        pane.setVisible(false);
        assert student != null;
        modifyName.setText(student.getName());
        modifyId.setText(student.getId().toString());
        modifyClass.setText(student.getClassId() + "");
        modifyMajor.setText(student.getMajor());
        c1.setText(student.getCourses().get(0).getTitle());
        c2.setText(student.getCourses().get(1).getTitle());
        c3.setText(student.getCourses().get(2).getTitle());
        c4.setText(student.getCourses().get(3).getTitle());
        c5.setText(student.getCourses().get(4).getTitle());
        c6.setText(student.getCourses().get(5).getTitle());
        c7.setText(student.getCourses().get(6).getTitle());

        s1.setText(student.getScores().get(0).toString());
        s2.setText(student.getScores().get(1).toString());
        s3.setText(student.getScores().get(2).toString());
        s4.setText(student.getScores().get(3).toString());
        s5.setText(student.getScores().get(4).toString());
        s6.setText(student.getScores().get(5).toString());
        s7.setText(student.getScores().get(6).toString());
        pane1.setVisible(true);
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

    boolean checkName() {
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
            String regex = "1020\\d{3}";
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


    void countPage() {
        hBox.getChildren().clear();
        pages = total / size + (total % size != 0 ? 1 : 0);
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
    public void sort1(ActionEvent actionEvent) {
        studentQuery.setSortScore(1);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void sort2(ActionEvent actionEvent) {
        studentQuery.setSortScore(2);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void sort3(ActionEvent actionEvent) {
        studentQuery.setSortScore(3);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void sort4(ActionEvent actionEvent) {
        studentQuery.setSortScore(4);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void sort5(ActionEvent actionEvent) {
        studentQuery.setSortScore(5);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void sort6(ActionEvent actionEvent) {
        studentQuery.setSortScore(6);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void sort7(ActionEvent actionEvent) {
        studentQuery.setSortScore(7);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void reverseSort1(ActionEvent actionEvent) {
        studentQuery.setSortScore(-1);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void reverseSort2(ActionEvent actionEvent) {
        studentQuery.setSortScore(-2);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void reverseSort3(ActionEvent actionEvent) {
        studentQuery.setSortScore(-3);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void reverseSort4(ActionEvent actionEvent) {
        studentQuery.setSortScore(-4);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void reverseSort5(ActionEvent actionEvent) {
        studentQuery.setSortScore(-5);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void reverseSort6(ActionEvent actionEvent) {
        studentQuery.setSortScore(-6);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void reverseSort7(ActionEvent actionEvent) {
        studentQuery.setSortScore(-7);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void update(ActionEvent actionEvent) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.titleProperty().set("提示");
        alert1.setHeaderText("确定更新学生成绩信息?");
        alert1.setContentText(null);
        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (checkScore()) {
                student.getScores().clear();
                student.addScore(Double.valueOf(s1.getText()));
                student.addScore(Double.valueOf(s2.getText()));
                student.addScore(Double.valueOf(s3.getText()));
                student.addScore(Double.valueOf(s4.getText()));
                student.addScore(Double.valueOf(s5.getText()));
                student.addScore(Double.valueOf(s6.getText()));
                student.addScore(Double.valueOf(s7.getText()));

                Result result1 = sController.updateStudentScore(student);
                if (result1.getSuccess()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("提示");
                    alert.setHeaderText("更新信息成功");
                    alert.setContentText(null);
                    alert.showAndWait();
                    flush();
                }
            }
        }
    }

    @FXML
    public void searchStudent(ActionEvent actionEvent) throws Exception {
        new ChangeScene("studentscoreview/student.fxml");
    }

    @FXML
    public void searchCourse(ActionEvent actionEvent) throws Exception {
        new ChangeScene("studentscoreview/course.fxml");
    }

    public void sort8(ActionEvent actionEvent) {
        studentQuery.setSortScore(100);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    public void reverseSort8(ActionEvent actionEvent) {
        studentQuery.setSortScore(-100);
        getListAndSetTableView(current = 1, size, studentQuery);
    }

    @FXML
    public void searchManager(ActionEvent actionEvent) throws IOException {
        new ChangeScene("studentscoreview/user.fxml");
    }
}