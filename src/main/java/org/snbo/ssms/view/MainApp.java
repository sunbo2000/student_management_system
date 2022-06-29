package org.snbo.ssms.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * @author sunbo.2000
 */
public class MainApp extends Application {

    public static Stage stage;


    @Override
    public void start(Stage myStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainApp.class.getResource("login.fxml")));
        myStage.setTitle("学生成绩管理系统");
        stage = myStage;
        myStage.getIcons().add(new Image(
                Objects.requireNonNull(MainApp.class.getResourceAsStream("ico/sign.png"))));

        myStage.setScene(new Scene(root));
        myStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}