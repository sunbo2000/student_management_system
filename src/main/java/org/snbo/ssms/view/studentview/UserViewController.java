package org.snbo.ssms.view.studentview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.snbo.ssms.bean.User;
import org.snbo.ssms.controller.UserController;
import org.snbo.ssms.utils.Result;
import org.snbo.ssms.utils.StringUtils;
import org.snbo.ssms.view.ChangeScene;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author sunbo
 * @date 2022-06-28-20:39
 */
public class UserViewController {
    @FXML
    public TableView<UserVo> tableView;


    public final UserController controller = new UserController();

    @FXML
    public TableColumn<UserVo, String> username;
    @FXML
    public TableColumn<UserVo, String> opera1;
    @FXML
    public Pane pane;
    @FXML
    public Pane pane1;
    @FXML
    public TextField account;
    @FXML
    public TextField password;
    @FXML
    public TextField password1;
    @FXML
    public Label tip3;

    private User user;

    @FXML
    void initialize() {
        getListAndSetTableView();
    }

    private void getListAndSetTableView() {
        Result users = controller.getUsers();
        List<User> userList = (List<User>) users.getData().get("userList");

        List<UserVo> list = new ArrayList<>();
        for (User user : userList) {
            list.add(new UserVo(user.getId(), user.getUsername()));
        }

        ObservableList<UserVo> tableList = FXCollections.observableList(list);
        opera1.setCellFactory((col) -> new TableCell<UserVo, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                Button button = new Button("删除");
                button.setOnAction(event -> {
                    Integer id = tableView.getItems().get(getIndex()).getId();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.titleProperty().set("提示");
                    alert.setHeaderText("用户将被永久删除,是否继续?");
                    alert.setContentText(null);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get() == ButtonType.OK) {
                        Result result = controller.remove(new User(id));
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
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        tableView.getItems().clear();
        tableView.getItems().addAll(list);

        pane1.setVisible(false);
        pane.setVisible(true);

    }

    @FXML
    public void searchStudent(ActionEvent actionEvent) throws Exception {
        new ChangeScene("studentscoreview/student.fxml");
    }

    @FXML
    public void searchCourse(ActionEvent actionEvent) throws Exception {
        new ChangeScene("studentscoreview/course.fxml");
    }


    @FXML
    public void searchScore(ActionEvent actionEvent) throws Exception {
        new ChangeScene("studentscoreview/manager.fxml");
    }

    @FXML
    private void flush() {
        getListAndSetTableView();
    }

    public void addUser(ActionEvent actionEvent) {
        account.clear();
        password.clear();
        password1.clear();
        pane.setVisible(false);
        pane1.setVisible(true);
    }

    public void add(ActionEvent actionEvent) {
        String acc = account.getText().trim();
        String pass = password.getText().trim();
        String pass1 = password1.getText().trim();

        if (StringUtils.isEmpty(pass) || StringUtils.isEmpty(pass1)) {
            tip3.setText("请输入密码");
            return;
        }

        if (!pass.equals(pass1)) {
            tip3.setText("两次输入密码不一致");
            return;
        }

        Result register = controller.register(acc, pass);
        if (register.getSuccess()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("提示");
            alert.setHeaderText("添加信息成功");
            alert.setContentText(null);
            alert.showAndWait();
            flush();
        } else {
            tip3.setText(register.getMessage());
        }
    }
}
