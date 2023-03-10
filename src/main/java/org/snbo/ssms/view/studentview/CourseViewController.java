package org.snbo.ssms.view.studentview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.snbo.ssms.bean.Course;
import org.snbo.ssms.controller.CourseController;
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
 * @date 2022-06-14-12:40
 */
public class CourseViewController {

    @FXML
    public TableView<CourseVo> tableView;
    @FXML
    public TableColumn<CourseVo, Integer> id;

    public TableColumn<CourseVo, String> opera1;
    @FXML
    public TableColumn<CourseVo, String> opera2;

    private final CourseController controller = new CourseController();
    @FXML
    public TableColumn<CourseVo, String> title;
    @FXML
    public Pane pane1;
    @FXML
    public Pane pane;
    @FXML
    public TextField courseName;
    @FXML
    public Label tip5;
    @FXML
    public Pane pane2;
    @FXML
    public TextField addCourseName;
    @FXML
    public Label tip6;

    private Integer total;
    private Integer current = 1;
    private Integer size = 20;
    private List<Course> list;
    private Course course = new Course();

    @FXML
    void initialize() {
        getListAndSetTableView();
    }

    void getListAndSetTableView() {
        Result result = controller.getCoursePage(current, size);
        list = (List<Course>) result.getData().get("coursePageList");

        if (list == null) {
            return;
        }
        List<CourseVo> voList = new ArrayList<>(7);
        list.forEach(cou -> {
            voList.add(new CourseVo(cou.getId(), cou.getTitle()));
        });
        setTableView(voList);

        pane.setVisible(true);
        pane1.setVisible(false);
        pane2.setVisible(false);
    }

    void setTableView(List<CourseVo> list) {
        ObservableList<CourseVo> tableList = FXCollections.observableList(list);

        if (list.isEmpty()) {
            tableView.getItems().clear();
            return;
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));

        opera1.setCellFactory((col) -> new TableCell<CourseVo, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button button = new Button("??????????????????");
                button.setOnAction(event -> {
                    Integer id = tableView.getItems().get(getIndex()).getId();
                    modify(id);
                });
                if (empty) {
                    //???????????????????????????????????????
                    setText(null);
                    setGraphic(null);
                } else {
                    this.setGraphic(button);
                }
            }
        });

        opera2.setCellFactory((col) -> new TableCell<CourseVo, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button button = new Button("??????????????????");
                button.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.titleProperty().set("??????");
                    alert.setHeaderText("????????????????????????,?????????????");
                    alert.setContentText(null);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.OK) {
                        Integer courseId = tableView.getItems().get(getIndex()).getId();
                        Result result = controller.removeCourse(new Course(courseId));
                        if (result.getSuccess()) {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.titleProperty().set("??????");
                            alert1.setHeaderText("????????????");
                            alert1.setContentText(null);
                            alert1.showAndWait();
                            flush();
                        }
                    }
                });
                if (empty) {
                    //???????????????????????????????????????
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

    private void modify(Integer id) {
        for (Course cou : list) {
            if (id.equals(cou.getId())) {
                course = cou;
            }
        }
        courseName.setText(course.getTitle());

        pane.setVisible(false);
        pane1.setVisible(true);
    }

    @FXML
    public void searchStudent(ActionEvent actionEvent) throws Exception {
        new ChangeScene("studentscoreview/student.fxml");
    }

    @FXML
    public void searchScore(ActionEvent actionEvent) throws Exception {
        new ChangeScene("studentscoreview/manager.fxml");
    }

    public void flush() {
        getListAndSetTableView();
    }

    public void update() {
        if (checkName()) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.titleProperty().set("??????");
            alert1.setHeaderText("?????????????????????????");
            alert1.setContentText(null);
            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK) {
                Result result1 = controller.updateCourse(course);
                if (result1.getSuccess()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("??????");
                    alert.setHeaderText("??????????????????");
                    alert.setContentText(null);
                    alert.showAndWait();
                    flush();
                }
            }
        }
    }

    @FXML
    public boolean checkName() {
        if (!StringUtils.isEmpty(courseName.getText().trim())) {
            course.setTitle(courseName.getText());
            tip5.setText("");
            return true;
        } else {
            course.setTitle(courseName.getText());
            tip5.setText("?????????????????????");
            return false;
        }
    }

    @FXML
    public boolean checkName1() {
        System.out.println(addCourseName);
        if (!StringUtils.isEmpty(addCourseName.getText().trim())) {
            course.setTitle(addCourseName.getText());
            tip6.setText("");
            return true;
        } else {
            course.setTitle(addCourseName.getText());
            tip6.setText("?????????????????????");
            return false;
        }
    }

    public void addCourse(ActionEvent actionEvent) {
        pane.setVisible(false);
        pane1.setVisible(false);
        pane2.setVisible(true);
    }

    @FXML
    public void add(ActionEvent actionEvent) {
        if (checkName1()) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.titleProperty().set("??????");
            alert1.setHeaderText("?????????????????????????");
            alert1.setContentText(null);
            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK) {
                course.setId(IdUtils.getCourseId());
                Result result1 = controller.saveCourse(course);
                if (result1.getSuccess()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("??????");
                    alert.setHeaderText("??????????????????");
                    alert.setContentText(null);
                    alert.showAndWait();
                    flush();
                }
            }
        }
    }

    @FXML
    public void searchManager(ActionEvent actionEvent) throws IOException {
        new ChangeScene("studentscoreview/user.fxml");
    }
}
