package org.snbo.ssms.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;


/**
 * @author sunbo.2000
 */
public class ChangeScene {

    public  ChangeScene(String fxml) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));

        MainApp.stage.setTitle("学生成绩管理系统");

        MainApp.stage.setScene(new Scene(root));
        MainApp.stage.setMinWidth(250);
        MainApp.stage.setX(20.0);
        MainApp.stage.setY(20.0);
        MainApp.stage.show();
    }
}
