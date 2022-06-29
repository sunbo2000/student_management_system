package org.snbo.ssms.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import org.snbo.ssms.controller.UserController;
import org.snbo.ssms.utils.Result;


/**
 * @author sunbo.2000
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Label prompt;

    @FXML
    private Label system;

    private UserController userController = new UserController();
    private static final String ADMIN = "admin";

    @FXML
    public void loadIn() throws Exception {
        // 设置了一个管理员
        Result login = userController.login(username.getText(), password.getText());
        if (login.getSuccess()) {
            new ChangeScene("studentscoreview/manager.fxml");
        } else {
            prompt.setText(login.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        system.setText("学生成绩管理系统");
    }
}
